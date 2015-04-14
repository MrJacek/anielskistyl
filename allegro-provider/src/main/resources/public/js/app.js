/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('root', ["ngResource", "ui.bootstrap", 'ui.grid', 'ui.grid.edit'])
    .controller("index", ["$scope", "$resource", function ($scope, $resource) {
        var login = $resource("http://localhost:8080/loginAllegro?key=:key&login=:login&password=:password");
        var soldItems = $resource("http://localhost:8080/soldItems?session=:session");
        var dbItems = $resource("http://localhost:8090/item/");
        var journalDeals = $resource("http://localhost:8080/journalDeals?session=:session");
        $scope.ui = "btn btn-default";
        $scope.cardinals = {};
        $scope.db = {
            itemsToDB: []
        };
        $scope.transaction_type = ["utworzenie aktu zakupowego (deala)", "utworzenie formularza pozakupowego (transakcji)"
            , "anulowanie formularza pozakupowego (transakcji),", "zakończenie (opłacenie) transakcji"];

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
                $scope.db.itemsToDB = data.soldItemsList.item;
            });
        };
        $scope.grid_options = {
            columnDefs: [
                {name: 'Id', field: 'itemId'},
                {name: 'Tytuł', field: 'itemTitle'}
            ],
            data: []
        };
        $scope.grid_options_journal_deals = {
            columnDefs: [
                {name: 'Id', field: 'time'},
                {name: 'kupujacy', field: 'buyerLogin'},
                {name: 'TypZdarzenia', field: 'transactionType'},
                {name: 'Ilosc', field: 'count'}
            ],
            data: []
        };
        $scope.saveItems = function () {
            $scope.db.itemsToDB.forEach(function (entry) {
                var item = {};
                item.allegroId = entry['itemId'];
                item.title = entry['itemTitle'];
                item.imgLink = entry['itemThumbnailUrl'];
                dbItems.save(item);
            });
        };
        $scope.showJournalDeals = function () {
            var result = journalDeals.get({session: $scope.cardinals.session});
            result.$promise.then(function (data) {
                $scope.grid_options_journal_deals.data = data.transactions;
            });
        };
    }]);