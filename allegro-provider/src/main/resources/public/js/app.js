/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('root', ["ngResource", "ui.bootstrap", 'ui.grid', 'ui.grid.edit'])
        .controller("index", ["$scope", "$resource", function ($scope, $resource) {
                var login = $resource("http://localhost:8080/loginAllegro?key=:key&login=:login&password=:password");
                var soldItems = $resource("http://localhost:8080/soldItems?session=:session");
//                var allegro = $resource("http://localhost:9000/hello-world?name=:login");
                $scope.ui = "btn btn-default";
                $scope.cardinals = {};
                $scope.items = {};
                $scope.doLogin = function (cardinals) {
                    var token = login.get({key: cardinals.key, login: cardinals.login, password: cardinals.password});
//                    var token = allegro.get({login: $scope.login});
                    token.$promise.then(function (data) {
                        $scope.cardinals.session = data['token'];
                        $scope.ui = "btn btn-success";
                    });
                };
                $scope.showSoldItems = function () {
                    var result = soldItems.get({session: $scope.cardinals.session});
                    result.$promise.then(function (data) {
                        $scope.grid_options.data = data.soldItemsList.item;
                    });
                };
                $scope.grid_options = {
                    columnDefs: [
                        {name: 'Id', field: 'itemId'},
                        {name: 'Tytuł', field: 'itemTitle'}
                    ],
                    data: []
                };


                $scope.showClients = function () {
                    
                };
            }]);