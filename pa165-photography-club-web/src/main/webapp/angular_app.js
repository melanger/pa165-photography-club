'use strict';

/* Defines application and its dependencies */

var pa165photoclubApp = angular.module('pa165photoclubApp', ['ngRoute', 'photoclubControllers']);
var photoclubControllers = angular.module('photoclubControllers', []);

/* Configures URL fragment routing  */
pa165photoclubApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
        when('/members', {templateUrl: 'partials/members.html', controller: 'MembersCtrl'}).
        when('/members/:memberId', {templateUrl: 'partials/member_detail.html', controller: 'MemberDetailCtrl'}).
        when('/tours', {templateUrl: 'partials/tours.html', controller: 'ToursCtrl'}).
        when('/tours/:tourId', {templateUrl: 'partials/tour_detail.html', controller: 'TourDetailCtrl'}).
        when('/admin/tours', {templateUrl: 'partials/admin_tours.html', controller: 'AdminToursCtrl'}).
        when('/admin/tours/new', {templateUrl: 'partials/admin_create_tour.html', controller: 'AdminCreateTourCtrl'}).
        otherwise({redirectTo: '/members'});
    }]);

/*
 * alert closing functions defined in root scope to be available in every template
 */
pa165photoclubApp.run(function ($rootScope) {
    $rootScope.hideSuccessAlert = function () {
        $rootScope.successAlert = undefined;
    };
    $rootScope.hideWarningAlert = function () {
        $rootScope.warningAlert = undefined;
    };
    $rootScope.hideErrorAlert = function () {
        $rootScope.errorAlert = undefined;
    };
});


/* Controllers */



/*
 * Public photo club interface
 */

/*
 * Home page
 */
photoclubControllers.controller('MembersCtrl', function ($scope, $http) {
    $http.get('/pa165/rest/members').then(function (response) {
        $scope.members = response.data;
    });
});

photoclubControllers.controller('MemberDetailCtrl', function ($scope, $rootScope, $routeParams, $http) {
    var memberId = $routeParams.memberId;
    $http.get('/pa165/rest/members/' + memberId).then(function success(response) {
        $scope.member = response.data;
        $http.get('/pa165/rest/members/' + memberId + '/equipment').then(function (response){
          $scope.member.equipment = response.data;
        });
    }, function error(response) {
        console.log(response);
        $rootScope.warningAlert = 'Member not found';
    });
});

photoclubControllers.controller('ToursCtrl', function ($scope, $http) {
    $http.get('/pa165/rest/tours').then(function (response) {
        $scope.tours = response.data;
    });
});

photoclubControllers.controller('TourDetailCtrl', function ($scope, $rootScope, $routeParams, $http) {
    var tourId = $routeParams.tourId;
    $http.get('/pa165/rest/tours/' + tourId).then(function success(response) {
        $scope.tour = response.data;
        $http.get('/pa165/rest/tours/' + tourId + '/members').then(function (response) {
            $scope.tour.member = response.data;
        });
        $http.get('/pa165/rest/tours/' + tourId + '/reviews').then(function (response) {
            $scope.tour.review = response.data;
        })
    }, function error(response) {
        console.log(response);
        $rootScope.warningAlert = 'Tour not found';
    });
});


function loadAdminTours($http, $scope) {
    $http.get('/pa165/rest/tours').then(function (response) {
        $scope.tours = response.data;
    });
}

photoclubControllers.controller('AdminToursCtrl', function ($scope, $http, $rootScope) {
    loadAdminTours($http, $scope);
    $scope.deleteTour = function (tour) {           
        $http.delete('/pa165/rest/tours/' + tour.id).then(
            function success(response) {
                $rootScope.successAlert = 'Deleted tour "' + tour.name + '"';
                loadAdminTours($http, $scope);
            },
            function error(response) {
                console.log(response);
                switch (response.data.code) {
                    case 'ResourceNotFoundException':
                        $rootScope.errorAlert = 'Cannot delete non-existent tour ! ';
                        break;
                    default:
                        $rootScope.errorAlert = 'Cannot delete tour ! Reason given by the server: '+response.data.message;
                        break;
                }
            }
        );
    };
});



photoclubControllers.controller('AdminCreateTourCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        
        $scope.themes = ['PORTRAITS', 'LANDSCAPE'];
        $scope.tour = {
            'name': '',
            'theme': $scope.themes[0],
            'date': ''
        };
        
        $scope.create = function (tour) {
            $http({
                method: 'POST',
                url: '/pa165/rest/tours',
                data: tour
            }).then(function success(response) {
                var createdTour = response.data;
                $rootScope.successAlert = 'A new tour "' + createdTour.name + '" was created';
                $location.path("/admin/tours");
            }, function error(response) {
                console.log("error when creating tour");
                console.log(response);
                switch (response.data.code) {
                    case 'ResourceAlreadyExistingException':
                        $rootScope.errorAlert = 'Tour with the same name already exists ! ';
                        break;
                    default:
                        $rootScope.errorAlert = 'Cannot create tour ! Reason given by the server: '+response.data.message;
                        break;
                }
            });
        };
    });

photoclubControllers.directive('convertToInt', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function (val) {
                return parseInt(val, 10);
            });
            ngModel.$formatters.push(function (val) {
                return '' + val;
            });
        }
    };
});
