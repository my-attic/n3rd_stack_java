'use strict';

var n3rdStackJavaApp = angular.module('n3rdStackJavaApp', ['ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.tasks.html',
        controller: 'TasksCtrl',
        resolve: {
          tasks: function($resource) {
            var Task = $resource('/tasks' + '/:id', {id: '@_id'}, {'update': {method: 'PUT' }});
            return Task.query();
          }
        }
      })
      .otherwise({
        redirectTo: '/'
      });
  }]);



