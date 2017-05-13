app.controller("doctorCtrl", function($scope,$http,$routeParams,$modal,uiCalendarConfig) {

    $scope.doctorId = $routeParams.doctorId;


    $http.get("/rest/doctors/"+$scope.doctorId)
        .then(function(response) {
            $scope.doctor = response.data;
            $scope.doctor.fullName = response.data.firstName+' '+response.data.lastName+' ';

            $scope.doctor.nameWithSpec = $scope.doctor.fullName;
            angular.forEach(response.data.specializations,function (spec) {
                $scope.doctor.nameWithSpec += spec.name;
            })
        });
    
    $scope.eventSources = [];

    $scope.slotToEvent = function(slot){
       slot.start = new Date(slot.startTime.year, slot.startTime.monthValue-1, slot.startTime.dayOfMonth,
           slot.startTime.hour,slot.startTime.minute);
       slot.end = new Date(slot.endTime.year, slot.endTime.monthValue-1, slot.endTime.dayOfMonth,
           slot.endTime.hour,slot.endTime.minute);

       slot.title = "Wizyta";

        return slot;

    };



    $scope.reloadSlots = function () {
        console.log("wysyłam rikłesta");
        $http.get("/rest/doctors/"+$scope.doctorId+"/slots")
            .then(function(response) {
                console.log("jest coś");
                console.log(response.data);

                $scope.taken = {};
                $scope.taken.color = '#f00';
                $scope.taken.events = [];

                $scope.free = {};
                $scope.free.color = '#2145ff';
                $scope.free.events = [];
                // $scope.events = [];

                angular.forEach(response.data, function(slot) {
                    var event = $scope.slotToEvent(slot);
                    if(slot.takenBy){
                        $scope.taken.events.push(event);
                    }else{
                        $scope.free.events.push(event);
                    }

                });

                $scope.eventSources.splice(0,2,$scope.taken,$scope.free);
                console.log($scope);
            });
    };

    $scope.reloadSlots();

    $scope.handleEventClick = function( event, jsEvent, view){


        var dialogOpts = {
            templateUrl: 'visitDialog.html',
            controller: 'dialogCtrl',
            resolve: {
                doctor:function () {
                    return $scope.doctor;
                },
                date:function () {
                    return event.start;
                },
                slot:function () {
                    return event;
                }

            }
        };

        $modal.open(dialogOpts).result.then(function (result) {
            $scope.reloadSlots();
        });
    };

    /* config object */
    $scope.uiConfig = {
        calendar:{
            lang: "pl",
            locale: "pl",
            height: 450,
            editable: true,
            header:{
                left: 'month,agendaWeek,agendaDay',
                center: 'title',
                right: 'today prev,next'
            },
            eventClick: $scope.handleEventClick
        }
    };

});
