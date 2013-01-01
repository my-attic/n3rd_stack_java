'use strict';

n3rdStackJavaApp.controller('TasksCtrl', function($scope, $resource, tasks) {
  $scope.tasks = tasks;
  $scope.selectedTask = {};

  $scope.Task = $resource('/tasks' + '/:id', {id: '@_id'}, {'update': {method: 'PUT' }});

  $scope.addTask = function(label,who,done) {
    $scope.selectedTask = new $scope.Task();

    $scope.selectedTask.label = label;
    $scope.selectedTask.who = who;
    $scope.selectedTask.done = done;

    $scope.selectedTask.$save(function(data) {
      console.log("Task.save() success");
      $scope.tasks.push(data);
    });
  };
  //called when link is clicked
  $scope.select = function(task) {
    $scope.Task.get({'id': task._id}, function(data) {
      console.log("Task.get() success");
      $scope.selectedTask = data;
    }, function(err) {
      console.log("Task.get() error : "+err);
    });
  };
  //called when delete link is clicked
  $scope.selectAndDelete = function(task) {
    var i = $scope.tasks.indexOf(task);
    $scope.Task.remove({'id': task._id}, function(data) {
      console.log("Task.remove() success");
      $scope.selectedTask = {};
      $scope.tasks.splice(i, 1);
    }, function(err) {
      console.log("Task.remove() error : "+err);
    });
  };
});

