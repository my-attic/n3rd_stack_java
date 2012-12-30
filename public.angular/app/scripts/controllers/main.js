'use strict';

n3rdStackJavaApp.controller('MainCtrl', function($scope, $resource, humans) {
  $scope.humans = humans;
  $scope.selectedHuman = {};

  $scope.Human = $resource('/humans' + '/:id', {id: '@_id'}, {'update': {method: 'PUT' }});

  $scope.addHuman = function(firstName, lastName) {
    $scope.selectedHuman = new $scope.Human();
    $scope.selectedHuman.firstName = firstName;
    $scope.selectedHuman.lastName = lastName;
    $scope.selectedHuman.$save(function(data) {
      console.log("Human.save() success");
      $scope.humans.push(data);
    });
  };
  //called when link is clicked
  $scope.select = function(human) {
    $scope.Human.get({'id': human._id}, function(data) {
      console.log("Human.get() success");
      $scope.selectedHuman = data;
    }, function(err) {
      console.log("Human.get() error : "+err);
    });
  };
  //called when delete link is clicked
  $scope.selectAndDelete = function(human) {
    var i = $scope.humans.indexOf(human);
    $scope.Human.remove({'id': human._id}, function(data) {
      console.log("Human.remove() success");
      $scope.selectedHuman = {};
      $scope.humans.splice(i, 1);
    }, function(err) {
      console.log("Human.remove() error : "+err);
    });
  };
});
