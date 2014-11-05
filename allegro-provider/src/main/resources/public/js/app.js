/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var LoginCtrl = function($scope, $http) {
    $scope.credentials = {
        username: '',
        password: ''
    };
    $scope.doLogin = function(credentials) {
        $http.get("http://localhost:8080/loginAllegro?user=" + credentials.username + "&password=" + credentials.password)
                .success(function(response) {
                    $scope.sessionHandler = response;
                });
    };

};



