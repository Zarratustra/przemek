'use strict';

app.controller("mainCtrl", ['$scope', '$location', 'AuthenticationService', function($scope, $location, AuthenticationService) {

    $scope.logout = function(){
        AuthenticationService.ClearCredentials();
        $location.path('/login');
    };

}]);