'use strict';

var n3rdStackJavaApp = angular.module('n3rdStackJavaApp', ['ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.humans.html',
        controller: 'HumansCtrl',
        resolve: {
          humans: function($resource) {
            var Human = $resource('/humans' + '/:id', {id: '@_id'}, {'update': {method: 'PUT' }});
            return Human.query();
          }
        }
      })
      .otherwise({
        redirectTo: '/'
      });
  }]);



