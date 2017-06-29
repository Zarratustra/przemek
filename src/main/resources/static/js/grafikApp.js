var app = angular.module("grafikApp", ["ngRoute",'ngCookies','ui.calendar','ui.bootstrap']);

app.config(['$routeProvider', function ($routeProvider) {

        $routeProvider
            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login.html'
            })

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

            .otherwise({ redirectTo: '/login' });
    }])

    .run(['$rootScope', '$location', '$cookieStore', '$http',
        function ($rootScope, $location, $cookieStore, $http) {
            // keep user logged in after page refresh
            $rootScope.globals = $cookieStore.get('globals') || {};
            if ($rootScope.globals.currentUser) {
                $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
            }

            $rootScope.$on('$locationChangeStart', function (event, next, current) {
                // redirect to login page if not logged in
                if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                    $location.path('/login');
                }
            });
        }]);


    ///awdredgd
//     .config(function($routeProvider) {
//     $routeProvider
//         .when('/login', {
//             controller: 'LoginController',
//             templateUrl: 'login.html'
//         })
//         .when("/", {
//             templateUrl : "start.html",
//             controller:"mainCtrl"
//         })
//         .when("/doctors", {
//             templateUrl : "allDoctors.html",
//             controller:   "doctorsCtrl"
//         })
//         .when("/doctor/:doctorId", {
//             templateUrl : "singleDoctor.html",
//             controller:  "doctorCtrl"
//         })
//         .otherwise({ redirectTo: '/login' })
//         .run(['$rootScope', '$location', '$cookieStore', '$http',
//         function ($rootScope, $location, $cookieStore, $http) {
//             // keep user logged in after page refresh
//             $rootScope.globals = $cookieStore.get('globals') || {};
//             if ($rootScope.globals.currentUser) {
//                 $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
//             }
//
//             $rootScope.$on('$locationChangeStart', function (event, next, current) {
//                 // redirect to login page if not logged in
//                 if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
//                     $location.path('/login');
//                 }
//             });
//         }]);
// });