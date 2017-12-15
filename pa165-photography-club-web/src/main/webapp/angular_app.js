'use strict';

/* Defines application and its dependencies */

var pa165photoclubApp = angular.module('pa165photoclubApp', ['ngRoute', 'photoclubControllers']);
var photoclubControllers = angular.module('photoclubControllers', []);

/* Configures URL fragment routing  */
pa165photoclubApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
        when('/home', {templateUrl: 'partials/home.html', controller: 'HomeCtrl'}).
        // TODO: add pages
        otherwise({redirectTo: '/home'});
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
photoclubControllers.controller('HomeCtrl', function ($scope, $http) {
    $http.get('/pa165/rest/members').then(function (response) {
        console.log(response.data);
        $scope.members = response.data;
        // TODO: finish
    });
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
