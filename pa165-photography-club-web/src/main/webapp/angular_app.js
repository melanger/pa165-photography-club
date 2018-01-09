'use strict';

/* Defines application and its dependencies */

var pa165photoclubApp = angular.module('pa165photoclubApp', ['ngRoute']);

/* Configures URL fragment routing  */
pa165photoclubApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
        when('/members', {templateUrl: 'partials/members.html', controller: 'MembersCtrl'}).
        when('/members/:memberId', {templateUrl: 'partials/member_detail.html', controller: 'MemberDetailCtrl'}).
        when('/tours', {templateUrl: 'partials/tours.html', controller: 'ToursCtrl'}).
        when('/tours/:tourId', {templateUrl: 'partials/tour_detail.html', controller: 'TourDetailCtrl'}).
        when('/admin/tours', {templateUrl: 'partials/admin_tours.html', controller: 'AdminToursCtrl', roles:['MANAGER']}).
        when('/admin/tours/new', {templateUrl: 'partials/admin_create_tour.html', controller: 'AdminCreateTourCtrl', roles:['MANAGER']}).
        when('/admin/profile', {templateUrl: 'partials/admin_member_profile.html', controller: 'AdminMemberProfileCtrl', roles:['MEMBER','MANAGER']}).
        when('/login', {templateUrl: 'partials/login.html', controller: 'LoginController'}).
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

pa165photoclubApp.controller('LoginController', function ($scope, $rootScope, AUTH_EVENTS, AuthService) {
  $scope.credentials = {
    email: '',
    password: ''
  };
  $scope.login = function (credentials) {
    AuthService.login(credentials).then(function (user) {
      $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
      $rootScope.currentUser = user;
    }, function () {
      $rootScope.$broadcast(AUTH_EVENTS.loginFailed);
    });
  };
});

pa165photoclubApp.constant('AUTH_EVENTS', {
  loginSuccess: 'auth-login-success',
  loginFailed: 'auth-login-failed',
  notAuthenticated: 'auth-not-authenticated',
  notAuthorized: 'auth-not-authorized'
});

pa165photoclubApp.constant('USER_ROLES', {
  all: '*',
  manager: 'MANAGER',
  member: 'MEMBER'
});

pa165photoclubApp.factory('AuthService', function ($http, Session) {
  var authService = {};
 
  authService.login = function (credentials) {
    return $http
      .post('/pa165/rest/members/login', credentials)
      .then(function (res) {
        Session.create(res.data);
        return res.data;
      });
  };
 
  authService.isAuthenticated = function () {
    return !!Session.userId;
  };
 
  authService.isAuthorized = function (authorizedRoles) {
    if (!angular.isArray(authorizedRoles)) {
      authorizedRoles = [authorizedRoles];
    }
    return (authService.isAuthenticated() &&
      authorizedRoles.indexOf(Session.userRole) !== -1);
  };
 
  return authService;
});

pa165photoclubApp.service('Session', function () {
  this.create = function (user) {
    this.user = user;
    this.userId = user.id;
    this.userRole = user.userRole;
  };
  this.destroy = function () {
    this.user = null;
    this.userId = null;
    this.userRole = null;
  };
});

pa165photoclubApp.controller('ApplicationController', function ($scope,
                                               USER_ROLES,
                                               AuthService) {
  $rootScope.currentUser = null;
  $scope.userRoles = USER_ROLES;
  $scope.isAuthorized = AuthService.isAuthorized;
});

pa165photoclubApp.run(function ($rootScope, AUTH_EVENTS, AuthService) {
  $rootScope.$on('$routeChangeStart', function (event, next) {
    var authorizedRoles = next.roles || [];
    if (authorizedRoles && authorizedRoles.length > 0 && !AuthService.isAuthorized(authorizedRoles)) {
      event.preventDefault();
      if (AuthService.isAuthenticated()) {
        $rootScope.errorAlert = "You do not have sufficient permissions (user role).";
      } else {
        $rootScope.errorAlert = "You have to be logged in.";
      }
    } else {
      $rootScope.hideSuccessAlert();
      $rootScope.hideWarningAlert();
      $rootScope.hideErrorAlert();
    }
  });
});

pa165photoclubApp.controller('AdminMemberProfileCtrl', function ($scope, $routeParams, $http, $location, $rootScope) {
    if (!$rootScope.currentUser) {
      $rootScope.errorAlert = "You have to be logged in.";
    }
    $scope.equipment = {
            'name': '',
            'type': 'OTHER',
            'ownerId': $rootScope.currentUser.id
    };
    $scope.ownerId = $rootScope.currentUser.id;
  
    $scope.create = function (equipment) {
      $http({
          method: 'POST',
          url: '/pa165/rest/equipment',
          data: equipment
      }).then(function success(response) {
          var equipment = response.data;
          $rootScope.successAlert = 'A new equipment "' + equipment.name + '" was added';
          $location.path("/admin/profile");
      }, function error(response) {
          switch (response.data.code) {
              case 'InvalidRequestException':
                  $rootScope.errorAlert = 'Sent data were found to be invalid by server ! ';
                  break;
              default:
                  $rootScope.errorAlert = 'Cannot create equipment ! Reason given by the server: '+response.data.message;
                  break;
          }
      });
    };
});

pa165photoclubApp.controller('MembersCtrl', function ($scope, $http) {
    $http.get('/pa165/rest/members').then(function (response) {
        $scope.members = response.data;
    });
});

pa165photoclubApp.controller('MemberDetailCtrl', function ($scope, $rootScope, $routeParams, $http) {
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

pa165photoclubApp.controller('ToursCtrl', function ($scope, $http) {
    $http.get('/pa165/rest/tours').then(function (response) {
        $scope.tours = response.data;
    });
});

pa165photoclubApp.controller('TourDetailCtrl', function ($scope, $rootScope, $routeParams, $http) {
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

pa165photoclubApp.controller('AdminToursCtrl', function ($scope, $http, $rootScope) {
    if (!$rootScope.currentUser) {
      $rootScope.errorAlert = "You have to be logged in.";
    }
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



pa165photoclubApp.controller('AdminCreateTourCtrl',
    function ($scope, $routeParams, $http, $location, $rootScope) {
        if (!$rootScope.currentUser) {
          $rootScope.errorAlert = "You have to be logged in.";
        }
        
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
            }).then(function success() {
                $rootScope.successAlert = 'A new tour was created';
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

pa165photoclubApp.directive('convertToInt', function () {
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
