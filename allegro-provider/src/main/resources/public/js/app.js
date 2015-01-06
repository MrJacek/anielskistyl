/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('root', ["ngResource"])
        .controller("index", ["$scope", "$resource", function ($scope, $resource) {
                var allegro = $resource("http://localhost:9000/loginAllegro?user=:login&password=:password");
//                var allegro = $resource("http://localhost:9000/hello-world?name=:login");

                $scope.message = "Hello World!";
                $scope.doLogin = function () {
                    var token = allegro.get({login: $scope.login, password: $scope.password});
//                    var token = allegro.get({login: $scope.login});
                    token.$promise.then(function (data) {
                        $scope.message=data["token"];
                    });
                };
            }]);