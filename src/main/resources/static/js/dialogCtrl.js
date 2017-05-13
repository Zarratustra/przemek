app.controller("dialogCtrl", function($scope,$http, $modalInstance,doctor,date,slot) {
    console.log(doctor);
    console.log(date);

    var monthNames = ["stycznia","lutego","marca","kwietnia","maja","czerwca","lipca",
        "sierpnia","września","października","listopada","grudnia"];

    $scope.patient = {firstName:"",lastName:""};
    $scope.patient.uid = ""+Math.random();
    if(slot.takenBy){
        $scope.patient = slot.takenBy;
        $scope.title = "Umówiona wizyta";
    }else{
        $scope.title = "Rejestracja wizyty";
    }

    $scope.slot = slot;

    $scope.doctor = doctor;
    $scope.date  = date.date() + " "+monthNames[date.month()]+" "+date.year()+" "+ date.hours()+":"+date.minutes();

    $scope.acceptModal = function(){
        $http.put("rest/doctors/"+doctor.id+"/slots/"+slot.id+"/taken_by",$scope.patient)
            .then(function (response) {
                $modalInstance.close();
            });

    };

    $scope.cancelModal = function(){
        $modalInstance.dismiss();
    };

});