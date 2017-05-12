app.controller("doctorCtrl", function($scope,$http,$routeParams,$modal,uiCalendarConfig) {

    $scope.doctorId = $routeParams.doctorId;
    $http.get("/rest/doctors/"+$scope.doctorId)
        .then(function(response) {
            $scope.doctorName = response.data.firstName+' '+response.data.lastName+' ';

            $scope.nameWithSpec = $scope.doctorName;
            angular.forEach(response.data.specializations,function (spec) {
                $scope.nameWithSpec += spec.name;
            })
        });
    
    $scope.eventSources = [];

    $scope.slotToEvent = function(slot){
        var start = new Date(slot.startTime.year, slot.startTime.monthValue-1, slot.startTime.dayOfMonth,
           slot.startTime.hour,slot.startTime.minute);
        var end = new Date(slot.endTime.year, slot.endTime.monthValue-1, slot.endTime.dayOfMonth,
           slot.endTime.hour,slot.endTime.minute);

        return {
            title: "Wizyta",
            start: start,
            end: end
        }
    };

    console.log("wysyłam rikłesta");
    $http.get("/rest/doctors/"+$scope.doctorId+"/slots")
        .then(function(response) {
            console.log("jest coś");
            console.log(response.data);

            $scope.events = [];
            
            angular.forEach(response.data, function(slot) {
                $scope.events.push($scope.slotToEvent(slot));
            });

            console.log("a teraz eventy");
            console.log($scope.events);

            $scope.eventSources.push($scope.events);
        });

    $scope.handleEventClick = function( event, jsEvent, view){


        var dialogOpts = {
            template: '<p>Coś</p>',
            controller: 'dialogCtrl'
        };

        $modal.open(dialogOpts);
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
