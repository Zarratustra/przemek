'use strict';


app.controller('RegisterController',
    ['$scope', '$rootScope', '$location','$http', function ($scope,$rootScope,$location,$http) {
    $scope.vm = this;

        $scope.register = function () {
            $scope.vm.dataLoading = true;

            $http.post('rest/register', $scope.vm.user )
                .success(function (r) {
                    $location.path('/login');
                }).error(function(r){
                    alert("Rejestracja nie powiodła się");
                    $scope.vm.dataLoading = false;
                });
    }

}]);