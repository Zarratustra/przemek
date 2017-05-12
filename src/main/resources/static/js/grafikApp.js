var app = angular.module("grafikApp", ["ngRoute",'ui.calendar','ui.bootstrap']);

app.config(function($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl : "start.html",
            controller:"mainCtrl"
        })
        .when("/doctors", {
            templateUrl : "allDoctors.html",
            controller:   "doctorsCtrl"
        })
        .when("/doctor/:doctorId", {
            templateUrl : "singleDoctor.html",
            controller:  "doctorCtrl"
        })
        .otherwise({
            template : "<h1>Błąd</h1><p>Panie, gdzieżeś Pan polazł?</p>"
        });
});