app.controller("doctorsCtrl", function($scope,$http) {
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
});