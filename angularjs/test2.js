	
//create	
	
'use strict';	
/**	
* ProjectCreateController	
*/	
	
CtlApp.controller('ProjectCreateController',	
    [   '$scope', '$rootScope', 'toastr', '$location', 'DepartmentInfoServices',	
        function ($scope, $rootScope, $toastr, $location, $service) {	
	
        /*======================================================================================================*	
         *== INITIAL                                                                                         ===*	
         *======================================================================================================*/	
	
        $scope.object = {status : 'ACTIVE'};	
        $scope.$on('$viewContentLoaded', function () {	
            Metronic.initAjax();	
        });	
	
        /*======================================================================================================*	
         *== GET INSTANCE DATA FROM API SERVICES                                                             ===*	
         *======================================================================================================*/	
	
	
        $scope.submit = function () {	
            $service.createObject($scope.object).then(	
                function (response) {	
                    if (response.code == RESPONSE_CODE.R_200) {	
                        $toastr.success(response.message, 'Success!');	
                        $location.path('/project');	
                    } else {	
                        // Show error	
                        $toastr.error(response.message, 'Error!');	
                    }	
                }	
            );	
        };	
	
        } // End controller function	
    ] // End controller param	
); // End controller declare	
	
	
// index	
	
'use strict';	
	
/**	
* ProjectIndexControler	
*/	
CtlApp.controller('ProjectIndexControler',	
    ['$scope', '$confirm', 'toastr', '$location', '$rootScope', 'ProjectServices', '$http',	
        function ($scope, $confirm, $toastr, $location, $rootScope, $service, $http) {	
	
            /*======================================================================================================*	
             *== INITIAL                                                                                         ===*	
             *======================================================================================================*/	
            // Initial default data	
         $scope.showAddNewProject = false;	
                $scope.showSearchProject = false;	
                $scope.showUpdateProject = false;	
                // Get data from server API and bind to screen	
                $http({	
                    method : "GET",	
                    url : "http://10.16.12.55/api/projects",	
                    headers: {	
                      'Authorization': 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjQsXCJ1c2VybmFtZVwiOlwiaG9hbmdsNFwiLFwibHN0Um9sZU5hbWVcIjpbXCJVU0VSXCJdfSIsImV4cCI6MTU1MTA4MTgwN30.ML-j3Tnf_n8nFe4zPNmOr7_Gn-0QmyjyVhy4VG1QdvEGT0ZQK3MJyK9peEPCLc2rfnzFODaQF33DMRVgFMiTdA',	
                      'Content-Type' : 'application/json'	
                    }	
                  }).then(function mySuccess(response) {	
                    $scope.projects = response.data.content;	
                    $scope.totalPages = response.data.totalPages;	
                    let totalPages = response.data.totalPages;	
                    let arrayPages = [];	
                    for (let i = 0; i < totalPages; i++) {	
                      arrayPages[i] = i + 1;	
                    }	
                    $scope.arrayPages = arrayPages;	
                  },function myError(response) {	
                    $scope.myWelcome = response.statusText;	
                  });	
                	
                	
                	
            /*======================================================================================================*	
             *== GET INSTANCE DATA FROM API SERVICES                                                             ===*	
             *======================================================================================================*/	
	
            	
	
            /*======================================================================================================*	
             *== SCREEN'S EVENT LISTENING                                                                        ===*	
             *======================================================================================================*/	
	
                $scope.showAdd = () => {	
                    $scope.showSearchProject = false;	
                    $scope.showUpdateProject = false;	
                    $scope.showAddNewProject = !$scope.showAddNewProject;	
                  };	
                  	
                  $scope.showSearch = () => {	
                         $scope.showSearchProject = !$scope.showSearchProject;	
                         $scope.showUpdateProject = false;	
                         $scope.showAddNewProject = false;	
                       };	
                       $scope.showUpdate = (project) => {	
                                $scope.showSearchProject = false	
                               $scope.showUpdateProject = !$scope.showUpdateProject;	
                               $scope.showAddNewProject = false;	
//                             console.log("HEHEHE", project)	
                               $scope.id = project.id;	
                               $scope.projectName = project.projectName;	
                               $scope.startDateUpdate = new Date(project.startDate);	
                               $scope.endDateUpdate = new Date(project.endDate);	
                             }	
                       	
                       	
                             	
                           $scope.saveNewProject = function(){	
                                    console.log($scope.projectName)	
//                              $scope.projectNameNewProject = projectName;	
//                              $scope.startDateNewProject = new Date(startDate);	
//                              $scope.endDateNewProject = new Date(endDate);	
                                    	
                                 	
                                    	
                           };	
                           	
                           $scope.close = () => {	
                               $scope.showAddNewProject = false;	
                               $scope.showSearchProject = false;	
                               $scope.showUpdateProject = false;	
                             };	
            /*======================================================================================================*	
             *== OTHER PROCESSING                                                                                ===*	
             *======================================================================================================*/	
	
	
	
	
        } // End controller function	
    ] // End controller param	
); // End controller declare	
	
	
///	
	
'use strict';	
/**	
* DepartmentUpdateController	
*/	
	
CtlApp.controller('DepartmentUpdateController',	
    [   '$scope', '$rootScope', 'toastr', '$state', '$location', '$stateParams', 'DepartmentInfoServices',	
        function ($scope, $rootScope, $toastr, $state, $location, $stateParams, $service) {	
	
            /*======================================================================================================*	
             *== INITIAL                                                                                         ===*	
             *======================================================================================================*/	
	
            $scope.object = {};	
            $scope.$on('$viewContentLoaded', function () {	
                Metronic.initAjax();	
            });	
	
            /*======================================================================================================*	
             *== GET INSTANCE DATA FROM API SERVICES                                                             ===*	
             *======================================================================================================*/	
	
            $service.getDetail($stateParams.id).then(	
                function (response) {	
                    if (response.code == RESPONSE_CODE.R_200) {	
                        $scope.object = response.data;	
	
                    } else {	
                        // Show error	
                        $toastr.error(response.message, 'Error!');	
                    }	
                }	
            );	
	
            $scope.submit = function () {	
                if ($scope.object) {	
                    $scope.object.id = $stateParams.id;	
                    $service.updateObject($scope.object).then(	
                        function (response) {	
                            if (response.code == RESPONSE_CODE.R_200) {	
                                $toastr.success(response.message, 'Success!');	
                                $location.path('/project');	
                            } else {	
                                $toastr.error(response.message, 'Error!');	
                            }	
                        }	
                    );	
                }	
            };	
	
        } // End controller function	
    ] // End controller param	
); // End controller declare	