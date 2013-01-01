'use strict';

n3rdStackJavaApp.controller('{{model_name}}sCtrl', function($scope, $resource, {{_model_name}}s) {
  $scope.{{_model_name}}s = {{_model_name}}s;
  $scope.selected{{model_name}} = {};

  $scope.{{model_name}} = $resource('/{{_model_name}}s' + '/:id', {id: '@_id'}, {'update': {method: 'PUT' }});

  $scope.add{{model_name}} = function({{fields_args}}) {
    $scope.selected{{model_name}} = new $scope.{{model_name}}();

    {{#properties}}
    $scope.selected{{model_name}}.{{private_name}} = {{private_name}};
    {{/properties}}

    $scope.selected{{model_name}}.$save(function(data) {
      console.log("{{model_name}}.save() success");
      $scope.{{_model_name}}s.push(data);
    });
  };
  //called when link is clicked
  $scope.select = function({{_model_name}}) {
    $scope.{{model_name}}.get({'id': {{_model_name}}._id}, function(data) {
      console.log("{{model_name}}.get() success");
      $scope.selected{{model_name}} = data;
    }, function(err) {
      console.log("{{model_name}}.get() error : "+err);
    });
  };
  //called when delete link is clicked
  $scope.selectAndDelete = function({{_model_name}}) {
    var i = $scope.{{_model_name}}s.indexOf({{_model_name}});
    $scope.{{model_name}}.remove({'id': {{_model_name}}._id}, function(data) {
      console.log("{{model_name}}.remove() success");
      $scope.selected{{model_name}} = {};
      $scope.{{_model_name}}s.splice(i, 1);
    }, function(err) {
      console.log("{{model_name}}.remove() error : "+err);
    });
  };
});

