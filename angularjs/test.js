Project
var app = angular.module('myApp', []);
app.controller('detail_project', function($scope, $http) {
  $http({
    method : "GET",
    url : "http://localhost:9191/api/project/1",
    headers: {
      'Authorization': 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjQsXCJ1c2VybmFtZVwiOlwiY2hpZW5udlwiLFwibHN0Um9sZU5hbWVcIjpbXCJVU0VSXCJdfSIsImV4cCI6MTU1MDcxNTkxNH0.Aac5sxV7VPK4psbBp-bsMLMFjqKs9pn-HdwDMEHuRcMTbhXx8lbP9cFzfWxkMreZl5EhMdJE0SKYFIVqbgw_eA',
      'Content-Type' : 'application/json'
    }
  }).then((response) => {
    let project = response.data;
    $scope.projectName = project.projectName;
    $scope.startDate = project.startDate;
    $scope.endDate = project.endDate;
    $scope.areaName = project.areaName;
    $scope.isActive = project.active;
  }, function myError(response) {
    $scope.myWelcome = response.statusText;
  });

});

app.controller('project', function($scope, $http) {
  $http({
    method : "GET",
    url : "http://localhost:9191/api/1/projects",
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

  $scope.showAddNewProject = false;
  $scope.showSearchProject = false;
  $scope.showUpdateProject = false;
  $scope.showAdd = function(){
    $scope.showAddNewProject = false;
    $scope.showSearchProject = false;
    $scope.showUpdateProject = false;
    $scope.showAddNewProject = !$scope.showAddNewProject;
  };

  $scope.showSearch = function(){
    $scope.showAddNewProject = false;
    $scope.showSearchProject = false;
    $scope.showUpdateProject = false;
    $scope.showSearchProject = !$scope.showSearchProject;
  };

  $scope.showUpdate = function(id){
    $scope.showAddNewProject = false;
    $scope.showSearchProject = false;
    $scope.showUpdateProject = false;
    $scope.showUpdateProject = !$scope.showUpdateProject;
    $http({
      method : "GET",
      url : "http://localhost:9191/api/1/project/" + id,
      headers: {
        'Authorization': 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjQsXCJ1c2VybmFtZVwiOlwiY2hpZW5udlwiLFwibHN0Um9sZU5hbWVcIjpbXCJVU0VSXCJdfSIsImV4cCI6MTU1MDcxNTkxNH0.Aac5sxV7VPK4psbBp-bsMLMFjqKs9pn-HdwDMEHuRcMTbhXx8lbP9cFzfWxkMreZl5EhMdJE0SKYFIVqbgw_eA',
        'Content-Type' : 'application/json'
      }
    }).then(function mySuccess(response) {
      var project = response.data;
      console.log(project);
      $scope.id = project.id;
      $scope.projectName = project.projectName;
      $scope.startDateUpdate = new Date(project.startDate);
      $scope.endDateUpdate = new Date(project.endDate);
    });
  }

  $scope.close = function(){
    $scope.showAddNewProject = false;
    $scope.showSearchProject = false;
    $scope.showUpdateProject = false;
  };

  $scope.saveNewProject = function(){
    $http({
      method : "POST",
      url : "http://localhost:9191/api/1/project",
      headers: {
        'Authorization': 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjQsXCJ1c2VybmFtZVwiOlwiY2hpZW5udlwiLFwibHN0Um9sZU5hbWVcIjpbXCJVU0VSXCJdfSIsImV4cCI6MTU1MDcxNTkxNH0.Aac5sxV7VPK4psbBp-bsMLMFjqKs9pn-HdwDMEHuRcMTbhXx8lbP9cFzfWxkMreZl5EhMdJE0SKYFIVqbgw_eA',
        'Content-Type' : 'application/json'
      },
      data : {
        "projectName" : $scope.projectNameNewProject,
        "startDate" : $scope.startDateNewProject,
        "endDate" : $scope.endDateNewProject
      }
    }).then(function mySuccess(response) {
      console.log(response);
    });
  };

  $scope.updateProject = function(id){
    $http({
      method : "PUT",
      url : "http://localhost:9191/api/1/project/" + id,
      headers: {
        'Authorization': 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjQsXCJ1c2VybmFtZVwiOlwiY2hpZW5udlwiLFwibHN0Um9sZU5hbWVcIjpbXCJVU0VSXCJdfSIsImV4cCI6MTU1MDcxNTkxNH0.Aac5sxV7VPK4psbBp-bsMLMFjqKs9pn-HdwDMEHuRcMTbhXx8lbP9cFzfWxkMreZl5EhMdJE0SKYFIVqbgw_eA',
        'Content-Type' : 'application/json'
      },
      data : {
        "startDate" : $scope.startDateUpdate,
        "endDate" : $scope.endDateUpdate
      }
    }).then(function mySuccess(response) {
      console.log(response);
    });
  };

  $scope.searchProject = function(){
    var active;
    if($scope.isActiveSearch == true){
      active = 1;
    } else {
      active = 0;
    }
    $http({
      method : "POST",
      url : "http://localhost:9191/api/1/project/searches" ,
      headers: {
        'Authorization': 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjQsXCJ1c2VybmFtZVwiOlwiY2hpZW5udlwiLFwibHN0Um9sZU5hbWVcIjpbXCJVU0VSXCJdfSIsImV4cCI6MTU1MDcxNTkxNH0.Aac5sxV7VPK4psbBp-bsMLMFjqKs9pn-HdwDMEHuRcMTbhXx8lbP9cFzfWxkMreZl5EhMdJE0SKYFIVqbgw_eA',
        'Content-Type' : 'application/json'
      },
      data : {
        "projectName" : $scope.projectNameSearch,
        "fromStartDate" : $scope.fromStartDate,
        "toDate" : $scope.toStartDate,
        "active" : active
      }
    }).then(function mySuccess(response) {
      console.log(response.data);
      $scope.projects = response.data.content;
      $scope.totalPages = response.data.totalPages;
    });
  };

  $scope.deleteProject = function(id, active){
    var isActive;
    if(active == "ACTIVE"){
      isActive = 0;
    } else {
      isActive = 1;
    }
    $http({
      method : "DELETE",
      url : "http://localhost:9191/api/1/project/" + id + "?active=" + isActive,
      headers: {
        'Authorization': 'Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJpZFwiOjQsXCJ1c2VybmFtZVwiOlwiY2hpZW5udlwiLFwibHN0Um9sZU5hbWVcIjpbXCJVU0VSXCJdfSIsImV4cCI6MTU1MDcxNTkxNH0.Aac5sxV7VPK4psbBp-bsMLMFjqKs9pn-HdwDMEHuRcMTbhXx8lbP9cFzfWxkMreZl5EhMdJE0SKYFIVqbgw_eA',
        'Content-Type' : 'application/json'
      }
    }).then(function mySuccess(response) {
      console.log(response);
    });
  };

  $scope.showModalDelete = function(id, active){
    $scope.projectIdDelete = id;
    $scope.projectActiveDelete = active;
  };

});


/// holiday.html

<!DOCTYPE html>
<html lang="en" ng-app="myApp">

<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>

  <div class="col-md-9 col-md-offset-3" ng-controller="holiday">
    <div>
      <div style="float : right; display: inline-block;">
        <p style="display: inline-block; margin-right : 50px">Language:</p>
        <p style="display: inline-block;">Hi,</p>
      </div>
    </div>

    <!-- Search -->
    <div class="well row" style="margin-top: 2%;" ng-show="showSearchHoliday">
      <h4>Search Holiday</h4>
      <div style="text-align: justify; line-height: 1.8;" class="col-md-offset-2">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="col-md-3 ">From Holiday Date:</label>
            <div class="col-md-6 col-md-3">
              <input type="date" class="form-control" ng-model="fromStartDate">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 ">To Holiday Date:</label>
            <div class="col-md-6 col-md-3">
              <input type="date" class="form-control" ng-model="toStartDate">
            </div>
          </div>
      </div>

      <div class="col-md-offset-4 buttonCustomized" style="margin-left:227px;">
        <button type="button" class="btn btn-primary" ng-click="searchHoliday()">Search</button>
        <button type="button" class="btn btn-danger">Reset</button>
        <button type="button" class="btn btn-info" ng-click="close()">Close</button>
      </div>

      </form>
    </div>

    <!-- upload -->
    <div class="well row" style="margin-top: 2%;">
      <h4>Upload File</h4>
      <div style="text-align: justify; line-height: 1.8;" class="col-md-offset-2">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="col-md-3 ">File Input:</label>
            <div class="col-md-6 col-md-3">



            </div>
          </div>
      </div>

      <div class="col-md-offset-4 buttonCustomized" style="margin-left:227px;">
        <button type="button" class="btn btn-primary" ng-click="searchholiday()">Upload</button>
        <button type="button" class="btn btn-info" ng-click="close()">Close</button>
      </div>

      </form>
    </div>


    <!-- add new holiday -->
    <div class="well row" style="margin-top: 2%;" ng-show="showAddNewHoliday">
      <h4>Add New Holiday</h4>
      <div style="text-align: justify; line-height: 1.8;" class="col-md-offset-2">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="col-md-3 ">Holiday Date:</label>
            <div class="col-md-6 col-md-3">
              <input type="date" class="form-control" ng-model="startDateNewHoliday">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 ">Remarks:</label>
            <div class="col-md-6 col-md-3">
              <input type="text" class="form-control" ng-model="holidayNameNewHoliday">
            </div>
          </div>

          <div class="col-md-offset-4 buttonCustomized" style="margin-left:227px;">
            <button type="button" class="btn btn-primary" ng-click="saveNewHoliday()">Save</button>
            <button type="button" class="btn btn-danger">Reset</button>
            <button type="button" class="btn btn-info" ng-click="close()">Close</button>
          </div>

        </form>
      </div>
    </div>

    <!-- Update -->
    <div class="well row" style="margin-top: 2%;" ng-show="showUpdateHoliday">
      <h4>Update Holiday</h4>
      <div style="text-align: justify; line-height: 1.8;" class="col-md-offset-2">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="col-md-3 ">Holiday Id:</label>
            <div class="col-md-6 col-md-3">
              <p>{{id}}</p>
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 ">Holiday Date:</label>
            <div class="col-md-6 col-md-3">
              <input type="date" class="form-control" ng-model="startDateUpdate">
            </div>
          </div>
          <div class="form-group">
            <label class="col-md-3 ">Remarks:</label>
            <div class="col-md-6 col-md-3">
              <p class="form-control-static">{{holidayName}}</p>
            </div>
          </div>


          <div class="col-md-offset-4 buttonCustomized" style="margin-left:227px;">
            <button type="button" class="btn btn-primary" ng-click="updateHoliday(id)">Save</button>
            <button type="button" class="btn btn-danger">Reset</button>
            <button type="button" class="btn btn-info" ng-click="close()">Close</button>
          </div>

       </form>
      </div>
    </div>

    <!-- delete -->
    <div class="modal fade modalDelete" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
      <div class="modal-dialog modal-sm" role="document">
        <div class="modal-content">
          <h3>Do you want to delete it?</h3>
          <button type="button" class="btn btn-info" ng-click="deleteHoliday(holidayIdDelete,holidayActiveDelete)">Yes</button>
          <button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
        </div>
      </div>
    </div>

    <div class="well row" style="margin-top: 2%;">
      <button type="button" class="btn btn-success" ng-click="showAdd()">Add</button>
      <button type="button" class="btn btn-primary" style="float:right;" ng-click="showSearch()">Search</button>
    </div>
    <div class="well row" style="margin-top: 2%;">
      <table class="table table-striped table-bordered">
        <tr class="warning">
          <th class="col-md-1">ID</th>
          <th class="col-md-4">Holiday Date</th>
          <th class="col-md-5">Remarks</th>
          <th class="col-md-2"></th>
        </tr>
        <tr ng-repeat="x in holidays">
          <td>{{x.id}}</td>
         <td>{{x.holidayDate}}</td>
          <td>{{x.remarks}}</td>
          <td style="text-align: center;">
            <a href="" ng-click="showUpdate(x.id)">Edit</a> |
            <a href="" data-toggle="modal" data-target=".modalDelete" ng-click="showModalDelete(x.id,x.active)">Delete</a>
          </td>
        </tr>
      </table>
      <nav aria-label="Page navigation" style="float: right;" ng-show="totalPages > 1">
        <ul class="pagination">
          <li>
            <a href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <li><a href="#">&lsaquo;</a></li>
          <li ng-repeat="x in arrayPages"><a href="#">{{x}}</a></li>
          <li><a href="#">&rsaquo;</a></li>
          <li>
            <a href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </div>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="app.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.5/angular.min.js"></script>
  <script src="app.js"></script>

</body>

</html>


////pro.html

<!DOCTYPE html>
<html lang="en" ng-app="myApp">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<div class="col-md-9 col-md-offset-3" ng-controller="project">
  <div>
    <div style="float : right; display: inline-block;">
      <p style="display: inline-block; margin-right : 50px">Language:</p>
      <p style="display: inline-block;">Hi,</p>
    </div>
  </div>

  <!-- Search -->
  <div class="well row" style="margin-top: 2%;" ng-show="showSearchProject">
    <h4>Search Project</h4>
    <div style="text-align: justify; line-height: 1.8;" class="col-md-offset-2">
      <form class="form-horizontal">
        <div class="form-group">
          <label class="col-md-3 ">Project Name:</label>
          <div class="col-md-6 col-md-3">
            <p class="form-control">example</p>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">From Start Date:</label>
          <div class="col-md-6 col-md-3">
            <input type="date" class="form-control" ng-model="fromStartDate">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">To Start Date:</label>
          <div class="col-md-6 col-md-3">
            <input type="date" class="form-control" ng-model="toStartDate">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">Area Name:</label>
          <div class="col-md-6 col-md-3">
            <input type="text" class="form-control" ng-model="projectNameSearch">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">IsActive:</label>
          <div class="col-md-6">
            <label>
              <input type="checkbox" ng-model="isActiveSearch">
            </label>
          </div>
        </div>

          <div class="col-md-offset-4 buttonCustomized" style="margin-left:227px;">
            <button type="button" class="btn btn-primary" ng-click="searchProject()">Search</button>
            <button type="button" class="btn btn-danger">Reset</button>
            <button type="button" class="btn btn-info" ng-click="close()">Close</button>
          </div>

      </form>
    </div>
  </div>

  <!-- add new project -->
  <div class="well row" style="margin-top: 2%;" ng-show="showAddNewProject">
    <h4>Add New Project</h4>
    <div style="text-align: justify; line-height: 1.8;" class="col-md-offset-2">
      <form class="form-horizontal">
        <div class="form-group">
          <label class="col-md-3 ">Project Name:</label>
          <div class="col-md-6 col-md-3">
            <p class="form-control">example</p>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">Start Date:</label>
          <div class="col-md-6 col-md-3">
            <input type="date" class="form-control" ng-model="startDateNewProject">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">End Date:</label>
          <div class="col-md-6 col-md-3">
            <input type="date" class="form-control" ng-model="endDateNewProject">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">Area Name:</label>
          <div class="col-md-6 col-md-3">
            <input type="text" class="form-control" ng-model="projectNameNewProject">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">IsActive:</label>
          <div class="col-md-6">
            <label>
              <input type="checkbox" ng-model="isActiveSearch">
            </label>
          </div>
        </div>

          <div class="col-md-offset-4 buttonCustomized" style="margin-left:227px;">
            <button type="button" class="btn btn-primary" ng-click="saveNewProject()">Save</button>
            <button type="button" class="btn btn-danger">Reset</button>
            <button type="button" class="btn btn-info" ng-click="close()">Close</button>
          </div>

      </form>
    </div>
  </div>

  <!-- Update -->
  <div class="well row" style="margin-top: 2%;" ng-show="showUpdateProject">
    <h4>Update Project</h4>
    <div style="text-align: justify; line-height: 1.8;" class="col-md-offset-2">
      <form class="form-horizontal">
        <div class="form-group">
          <label class="col-md-3 ">Project Id:</label>
          <div class="col-md-6 col-md-3">
            <p>{{id}}</p>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">Project Name:</label>
          <div class="col-md-6 col-md-3">
            <p>example</p>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">Start Date:</label>
          <div class="col-md-6 col-md-3">
            <input type="date" class="form-control" ng-model="startDateUpdate">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">End Date:</label>
          <div class="col-md-6 col-md-3">
            <input type="date" class="form-control" ng-model="endDateUpdate">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">Area Name:</label>
          <div class="col-md-6 col-md-3">
            <p class="form-control-static">{{projectName}}</p>
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 ">IsActive:</label>
          <div class="col-md-6">
            <label>
              <input type="checkbox" ng-model="isActiveSearch">
            </label>
          </div>
        </div>

          <div class="col-md-offset-4 buttonCustomized" style="margin-left:227px;">
            <button type="button" class="btn btn-primary" ng-click="updateProject(id)">Save</button>
            <button type="button" class="btn btn-danger">Reset</button>
            <button type="button" class="btn btn-info" ng-click="close()">Close</button>
          </div>

      </form>
    </div>
  </div>

  <!-- delete -->
  <div class="modal fade modalDelete" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-sm" role="document">
      <div class="modal-content">
        <h3>Do you want to delete it?</h3>
        <button type="button" class="btn btn-info" ng-click="deleteProject(projectIdDelete,projectActiveDelete)">Yes</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">No</button>
      </div>
    </div>
  </div>

  <div class="well row" style="margin-top: 2%;">
    <button type="button" class="btn btn-success" ng-click="showAdd()">Add</button>
    <button type="button" class="btn btn-primary" style="float:right;" ng-click="showSearch()">Search</button>
  </div>
  <div class="well row" style="margin-top: 2%;">
    <table class="table table-striped table-bordered">
      <tr class="warning">
        <th class="col-md-1">ID</th>
        <th class="col-md-3">Project Name</th>
        <th class="col-md-1.5">Start Date</th>
        <th class="col-md-1.5">End Date</th>
        <th class="col-md-2">Area Name</th>
        <th class="col-md-1">IsActive</th>
        <th class="col-md-2"></th>
      </tr>
      <tr ng-repeat="x in projects">
        <td>{{x.id}}</td>
        <td>{{x.projectName}}</td>
        <td>{{x.startDate}}</td>
        <td>{{x.endDate}}</td>
        <td>{{x.areaName}}</td>
        <td>{{x.active}}</td>
        <td style="text-align: center;">
          <a href="">View File</a> |
          <a href="" ng-click="showUpdate(x.id)">Edit</a> |
          <a href="" data-toggle="modal" data-target=".modalDelete" ng-click="showModalDelete(x.id,x.active)">Delete</a>
        </td>
      </tr>
    </table>
    <nav aria-label="Page navigation" style="float: right;" ng-show="totalPages > 1">
      <ul class="pagination">
        <li>
          <a href="#" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
          </a>
        </li>
        <li><a href="#">&lsaquo;</a></li>
        <li ng-repeat="x in arrayPages"><a href="#">{{x}}</a></li>
        <li><a href="#">&rsaquo;</a></li>
        <li>
          <a href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </nav>
  </div>
</div>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="app.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.5/angular.min.js"></script>
<script src="app.js"></script>

</body>
</html>


////viwlog


<!DOCTYPE html>
<html lang="en" ng-app="myApp">

<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>

  <div class="col-md-9 col-md-offset-3" ng-controller="viewlogs">
    <div>
      <a style="text-decoration: underline;">&lt;Back to Project</a>
      <div style="float : right; display: inline-block;">
        <p style="display: inline-block; margin-right : 50px">Language:</p>
        <p style="display: inline-block;">Hi,</p>
      </div>
    </div>

    <!-- Search -->
    <div class="well row" style="margin-top: 2%;">
      <h4>Search Logs</h4>
      <div style="text-align: justify; line-height: 1.8;" class="col-md-offset-2">
        <form class="form-horizontal">
          <div class="form-group">
            <label class="col-md-3 ">Input Date:</label>
            <div class="col-md-6 col-md-3">
              <input type="date" class="form-control" ng-model="fromStartDate">
            </div>
          </div>
      </div>

      <div class="col-md-offset-4 buttonCustomized" style="margin-left:227px;">
        <button type="button" class="btn btn-primary"">Search</button>
        <button type="button" class="btn btn-danger">Reset</button>
      </div>

      </form>
    </div>

    <!-- logs error -->
    <div class="well row" style="margin-top: 2%;">
      <h4>Logs Error</h4>
      <button type="button" class="btn btn-primary" style="float:right;">Delete</button>

      <table class="table table-striped table-bordered">
        <tr class="warning">
          <th class="col-md-1">Number</th>
          <th class="col-md-11">Content</th>
        </tr>
        <tr ng-repeat="x in viewlogss">
          <td>{{x.number}}</td>
          <td>{{x.content}}</td>
        </tr>
      </table>
      <nav aria-label="Page navigation" style="float: right;" ng-show="totalPages > 1">
        <ul class="pagination">
          <li>
            <a href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <li><a href="#">&lsaquo;</a></li>
          <li ng-repeat="x in arrayPages"><a href="#">{{x}}</a></li>
          <li><a href="#">&rsaquo;</a></li>
          <li>
            <a href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>

    <!-- logs info -->
    <div class="well row" style="margin-top: 2%;">
      <h4>Logs Info</h4>
      <button type="button" class="btn btn-primary" style="float:right;">Delete</button>

      <table class="table table-striped table-bordered">
        <tr class="warning">
          <th class="col-md-1">Number</th>
          <th class="col-md-11">Content</th>
        </tr>
        <tr ng-repeat="x in viewlogss">
          <td>{{x.number}}</td>
          <td>{{x.content}}</td>
        </tr>
      </table>
      <nav aria-label="Page navigation" style="float: right;" ng-show="totalPages > 1">
        <ul class="pagination">
          <li>
            <a href="#" aria-label="Previous">
              <span aria-hidden="true">&laquo;</span>
            </a>
          </li>
          <li><a href="#">&lsaquo;</a></li>
          <li ng-repeat="x in arrayPages"><a href="#">{{x}}</a></li>
          <li><a href="#">&rsaquo;</a></li>
          <li>
            <a href="#" aria-label="Next">
              <span aria-hidden="true">&raquo;</span>
            </a>
          </li>
        </ul>
      </nav>
    </div>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="app.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.5/angular.min.js"></script>
    <script src="app.js"></script>

</body>

</html>
