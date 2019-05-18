<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Document</title>


<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath()%>/css/bootstrap-admin.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="<%=request.getContextPath()%>/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/css/font-awesome-admin.min.css"
	rel="stylesheet" type="text/css">

<!-- DEFIND CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style-admin.css">

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/defind_style_admin.css">
<script
	src="https://unpkg.com/sweetalert2@7.17.0/dist/sweetalert2.all.js"></script>
</head>
<body>

	<div class="container text-center text-center">

		<form class="form-horizontal" role="form" id="register-form">

			<h2 id="h2_register">Registration Form</h2>
			<div class="form-group">
				<label for="FirstName" class="col-sm-3 control-label">First
					Name</label>
				<div class="col-sm-4">
					<input type="text" id="FirstName" name="FirstName"
						placeholder="First Name" class="form-control" autofocus>

				</div>
			</div>
			<div class="form-group">
				<label for="LastName" class="col-sm-3 control-label">Last
					Name</label>
				<div class="col-sm-4">
					<input type="text" id="LastName" name="LastName"
						placeholder="Last Name" class="form-control" autofocus>

				</div>
			</div>
			<div class="form-group">
				<label for="Address" class="col-sm-3 control-label">Address</label>
				<div class="col-sm-4">
					<input type="text" id="Address" name="Address"
						placeholder="Address" class="form-control" autofocus>

				</div>
			</div>


			<div class="form-group">
				<label for="Phone" class="col-sm-3 control-label">Phone</label>
				<div class="col-sm-4">
					<input type="text" id="Phone" name="Phone" placeholder="Phone"
						class="form-control" autofocus>

				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-4">
					<input type="hidden" id="role" name="role" placeholder="role"
						class="form-control" autofocus>

				</div>
			</div>

			<div class="form-group">
				<label for="Email" class="col-sm-3 control-label">Email</label>
				<div class="col-sm-4">
					<input type="email" id="Email" name="Email" placeholder="Email"
						class="form-control" autofocus>

				</div>
			</div>

			<div class="form-group">
				<label for="Password" class="col-sm-3 control-label">Password</label>
				<div class="col-sm-4">
					<input type="password" id="Password" name="Password"
						placeholder="Password" class="form-control">
				</div>
			</div>

			<div class="form-group">
				<label for="Confirm Password" class="col-sm-3 control-label">Confirm
					Password</label>
				<div class="col-sm-4">
					<input type="password" id="ConfirmPassword" name="ConfirmPassword"
						placeholder="Confirm Password" class="form-control">
				</div>
			</div>

			<div class="form-group">
				<div class="col-md-4 col-sm-offset-3">
					<button type="submit" class="btn btn-info">Submit</button>
					<input type="checkbox" onclick="Toggle()" id="ShowPassword" /><b>Show
						Confirm Password</b>


				</div>
				<div class="text-center" id="responseRegister"
					style="margin-top: 14px;"></div>
			</div>
		</form>


	</div>

	<script>
		// Change the type of input to password or text 
		function Toggle() {
			
			var temp = document.getElementById("ConfirmPassword");
			
			if (temp.type === "password") {
				temp.type = "text";
			}else {
				temp.type = "password";
			}	
		}
	</script>
	<!-- ./container -->

</body>

</html>

<script src="<%=request.getContextPath()%>/js/jquery-admin.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="<%=request.getContextPath()%>/js/bootstrap-admin.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="<%=request.getContextPath()%>/js/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="<%=request.getContextPath()%>/js/sb-admin-2.js"></script>

<!-- jquery -->
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>

<!-- validate -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.min.js"></script>
<script src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>

<!-- my jquery -->
<script src="<%=request.getContextPath()%>/js/my_jquery_defind.js"></script>

</body>
</html>