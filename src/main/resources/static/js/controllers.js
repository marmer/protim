
angular.module('myApp')
  .controller('TodoController', ['$scope', '$http', function($scope, $http) {

    $scope.newTodo = {};

    $scope.loadTodos = function() {
      $http.get('todos')
        .then(function(data, status, headers, config) {
          $scope.todos = data;
        }, function(data, status, headers, config) {
          alert('Error loading Todos');
        });
    };

    $scope.addTodo = function() {
      $http.post('todos', $scope.newTodo)
        .then(function(data, status, headers, config) {
          $scope.newTodo = {};
          $scope.loadTodos();
        }, function(data, status, headers, config) {
          alert('Error saving Todo');
        });
    };

    $scope.deleteTodo = function(todo) {
      $http.delete('todos/' + todo.id)
        .then(function(data, status, headers, config) {
          $scope.loadTodos();
        }, function(data, status, headers, config) {
          alert('Error deleting Todo');
        });
    };

    $scope.loadTodos();
  }]);