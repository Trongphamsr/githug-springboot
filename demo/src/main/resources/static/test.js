var app = angular.module('myApp', []);
app.controller('employee', function($scope, $http, $window) {
	$scope.addSearch = true;
	$scope.detail = false;
	$scope.showUpdateEmployee=false;
	$scope.showList = true;
	$scope.employeeForm = {
			id: 1,
			content: "",
			username: ""
	};

	function getList() {
		$http({
			method: 'GET',
			url: 'http://localhost:8080/api/employees'
		}).then(function successCallback(response) {
			$scope.employees = response.data.data;
			console.log(response)
		}, function errorCallback(response) {
		});
	}
	getList();

	$scope.getDetail = function (employee) {
		$http({
			method:"GET",
			url: 'http://localhost:8080/api/employees/' + employee.id
		}).then(function successCallback(response){			
			$scope.detail = true;
			$scope.showAddNewEmployee = false;
			$scope.addSearch = false;
			$scope.showList = false;
			$scope.employee = response.data.data;
		}, function errorCallback(response){
		});
	}
	
	
	$scope.back = function () {
		$scope.showList = true;
		$scope.addSearch = true;
		$scope.detail = false;
		getList();
		
	  };
	  
	$scope.saveNewEmployee = function() {
		$http({
			      method : "POST",
			      url : "http://localhost:8080/api/employees",
			      data : {
				        "content" : $scope.content,
				        "username" : $scope.username
				      }
		    }).then(function mySuccess(response) {
			$scope.showAddNewEmployee = false;
			$scope.addSearch = true;
			getList();
			    });
	}

	$scope.showUpdate = function(employee) {

		$scope.showAddNewEmployee = false;
		$scope.addSearch = false;
		$scope.detail = false;
		$scope.showUpdateEmployee=true

		$scope.employeeForm.id = employee.id;
		$scope.employeeForm.content = employee.content;
		$scope.employeeForm.username = employee.username;  
	}


	$scope.updateEmployee = function () {
		$http({
			      method : "PUT",
			      url : "http://localhost:8080/api/employees/" + $scope.employeeForm.id,
			data : $scope.employeeForm

			    }).then(function mySuccess(response) {
				      alert("emp has updated Successfully")
				$scope.showUpdateEmployee=false;
				$scope.addSearch = true;
				getList();
				    });
	}

	$scope.delete = function(employee) {

		//$http DELETE function
		$http({

			method: 'DELETE',
			url: 'http://localhost:8080/api/employees/' + employee.id

		}).then(function successCallback(response) {

			alert("User has deleted Successfully");
			getList();

		}, function errorCallback(response) {

			alert("Error. while deleting user Try Again!");

		});

	};


	$scope.showAdd = function() {
		$scope.showAddNewEmployee = true;
		$scope.addSearch = false;
		$scope.detail = false;
	}
	$scope.close = function() {
		$scope.showAddNewEmployee = false;
		$scope.addSearch = true;
		$scope.detail = false;
	}
	$scope.reset = function() {
		$scope.username = "";
		$scope.content = "";
	}
});
