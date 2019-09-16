var app = angular.module("UserManagement", []);
 
// Controller Part
app.controller("UserController", function($scope, $http) {
 
 
    $scope.users = [];
    $scope.userForm = {
        id: 1,
        email: "",
        name: ""
    };
 
    // Now load the data from server
    _refreshUserData();
 
    // HTTP POST/PUT methods for add/edit user  
    // Call: http://localhost:8080/user
    $scope.submitUser = function() {
 
        var method = "";
        var url = "";
 
        if ($scope.userForm.id == -1) {
            method = "POST";
            url = '/user';
        } else {
            method = "PUT";
            url = '/user';
        }
 
        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.userForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };
 
    $scope.createUser = function() {
        _clearFormData();
    }
 
    // HTTP DELETE- delete employee by Id
    // Call: http://localhost:8080/user/{id}
    $scope.deleteUser = function(user) {
        $http({
            method: 'DELETE',
            url: '/user/' + user.id
        }).then(_success, _error);
    };
 
    // In case of edit
    $scope.editUser = function(user) {
        $scope.userForm.id = user.id;
        $scope.userForm.email = user.email;
        $scope.userForm.name = user.name;
    };
 
    // Private Method  
    // HTTP GET- get all employees collection
    // Call: http://localhost:8080/users
    function _refreshUserData() {
        $http({
            method: 'GET',
            url: '/users'
        }).then(
            function(res) { // success
                $scope.users = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }
 
    function _success(res) {
        _refreshUserData();
        _clearFormData();
    }
 
    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }
 
    // Clear the form
    function _clearFormData() {
        $scope.userForm.id = -1;
        $scope.usereForm.email = "";
        $scope.userForm.name = ""
    };
});