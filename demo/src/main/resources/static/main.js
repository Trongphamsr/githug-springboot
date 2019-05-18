var app = angular.module("EmployeeManagement", []);
app.controller("EmployeeController", function($scope, $http) {
	
	$scope.employees = [];
	$scope.employeeForm = {
			id: 1,
			content: "",
			username: ""
	};
	
	_refreshEmployeeData();
	
	$scope.submitEmployee = function() {
		let method = "";
		let url = "";
		
		if($scope.employeeForm.id == -1) {
			method="POST";
			url="api/employees"
		}else{
			method="PUT";
			url="/api/employees/1"
		}
		
		$http({
			method:method,
			url:url,
			data:angular.toJson($scope.employeeForm),
			headers:{
				'Content-type':'application/json'
			}
		}).then(_success, _error);
	};
	
	$scope.createEmployee = function() {
		_clearFormData();
	}
	
	$scope.deleteEmployee = function(employee) {
		$http({
			method:"DELETE",
			url:"/api/employees/" + employee.id,
			data:angular.toJson($scope.employeeForm)
		}).then(_success, _error);
	}
	
	$scope.editEmployee = function(employee) {
		$scope.employeeForm.content = employee.content;
		$scope.employeeForm.username = employee.username;
	}
	
	
	function _refreshEmployeeData(){
//		$http({
//			method:'GET',
//			url:'/api/employees'
//		}).then(
//			function(res){
//				$scope.employees= res.data;
//			},
//			function(res) {
//				console.log("error" + res.status +" : " +res.data);
//			}
//		);
		
		$http({
		    method: 'GET',
		    url: '/api/employees'

		  }).then(function successCallback(response) {

		    $scope.employees = response.data;

		  }, function errorCallback(response) {

		    alert("Error. Try Again!");

		  });
	}
	
	
	
	function _success(res){
		_refreshEmployeeData();
		_clearFormData();
	}
	
	function _error(res){
		let data = res.data;
		let status = res.status;
		let header = res.header;
		let config = res.config;
		alert("error" + status + " : " + data);
	}
	
	function _clearFormData() {
		$scope.employeeForm.id = -1;
		$scope.employeeForm.content = "",
		$scope.employeeForm.username = ""
	};
	
});