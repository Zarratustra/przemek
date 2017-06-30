app.controller("doctorsCtrl", ['$scope', '$http', '$location', 'AuthenticationService',
    function($scope,$http, $location, AuthenticationService) {
    $http.get("/rest/doctors")
        .then(function(response) {
            $scope.doctors = response.data;
            angular.forEach($scope.doctors,function(doctor){
                doctor.nameWithSpec = doctor.firstName + ' '+doctor.lastName + ' - ';
                angular.forEach(doctor.specializations,function (spec) {
                    doctor.nameWithSpec += spec.name;
                })
            });
            console.log($scope.doctors);
        });

    $scope.logout = function(){
        AuthenticationService.ClearCredentials();
        $location.path('/login');
    };
}]);