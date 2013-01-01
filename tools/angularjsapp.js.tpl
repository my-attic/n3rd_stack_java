'use strict';

var n3rdStackJavaApp = angular.module('n3rdStackJavaApp', ['ngResource'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.{{_model_name}}s.html',
        controller: '{{model_name}}sCtrl',
        resolve: {
          {{_model_name}}s: function($resource) {
            var {{model_name}} = $resource('/{{_model_name}}s' + '/:id', {id: '@_id'}, {'update': {method: 'PUT' }});
            return {{model_name}}.query();
          }
        }
      })
      .otherwise({
        redirectTo: '/'
      });
  }]);



