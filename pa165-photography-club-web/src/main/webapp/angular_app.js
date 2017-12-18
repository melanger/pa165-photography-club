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
        when('/admin/members/me', {templateUrl: 'partials/admin_member_profile.html', controller: 'AdminMemberProfileCtrl', roles:['MEMBER','MANAGER']}).
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
  $rootScope.$on('$stateChangeStart', function (event, next) {
    var authorizedRoles = next.roles || [];
    if (authorizedRoles && !AuthService.isAuthorized(authorizedRoles)) {
      event.preventDefault();
      if (AuthService.isAuthenticated()) {
        // user is not allowed
        $rootScope.$broadcast(AUTH_EVENTS.notAuthorized);
      } else {
        // user is not logged in
        $rootScope.$broadcast(AUTH_EVENTS.notAuthenticated);
      }
    }
  });
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
