/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('root', ["ngResource", "ui.bootstrap", 'ui.grid', 'ui.grid.edit'])
        .controller("index", ["$scope", "$resource", function ($scope, $resource) {
                var login = $resource("http://192.168.0.12:8080/loginAllegro?key=:key&user=:login&password=:password");
                var soldItems = $resource("http://192.168.0.12:8080/getSoldItems?session=:session");
//                var allegro = $resource("http://localhost:9000/hello-world?name=:login");
                $scope.cardinals = {};
                $scope.items = {};
                $scope.doLogin = function (cardinals) {
                    var token = login.get({key: cardinals.key ,login: cardinals.login, password: cardinals.password});
//                    var token = allegro.get({login: $scope.login});
                    token.$promise.then(function (data) {
                        $scope.cardinals.session = data['token'];
                    });
                };
                $scope.showSoldItems = function () {
                    var result = soldItems.get({session: $scope.cardinals.session});
                    result.$promise.then(function (data) {
                        $scope.items.sold= data.soldItemsList.item;
                    });
                };

               
                $scope.showClients = function () {

                };
            }]);