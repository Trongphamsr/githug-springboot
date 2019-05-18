<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/edit-profile.css" />
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Edit Profile</h1>
		<hr>
		<div class="row">
			<!-- left column -->
			<div class="col-md-3">
				<div class="text-center">
					<img src="<%=request.getContextPath()%>/img/default-user.png"
						height="100" width="100" class="avatar img-circle" alt="avatar">
				</div>
			</div>

			<!-- edit form column -->
			<div class="col-md-9 personal-info">
				<div class="alert alert-info alert-dismissable">
					<a class="panel-close close" data-dismiss="alert">×</a> <i
						class="fa fa-coffee"></i> This is an <strong>.alert</strong>. Use
					this to show important messages to the user.
				</div>
				<h3>Personal info</h3>

				<form class="form-horizontal" role="form" id="editform">
				<input style="display:none" class="form-control" id="id" name="id"
								type="text" value="<s:property value = "member.id"/>">
					<div class="form-group">
						<label class="col-lg-3 control-label">First name:</label>
						<div class="col-lg-8">
							<input class="form-control" id="firstname" name="firstname"
								type="text" value="<s:property value = "member.firstName"/>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Last name:</label>
						<div class="col-lg-8">
							<input class="form-control" id="lastname" name="lastname"
								type="text" value="<s:property value = "member.lastName"/>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Email:</label>
						<div class="col-lg-8">
							<input class="form-control" id="email" name="email" type="text"
								value="<s:property value = "member.email"/>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Address:</label>
						<div class="col-lg-8">
							<input class="form-control" id="address" name="address"
								type="text" value="<s:property value = "member.address"/>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">Phone:</label>
						<div class="col-lg-8">
							<input class="form-control" id="phone" name="phone" type="text"
								value="<s:property value = "member.phone"/>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Password:</label>
						<div class="col-md-8">
							<input class="form-control" id="password" name="password"
								type="password" value="<s:property value = "member.password"/>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label">Confirm password:</label>
						<div class="col-md-8">
							<input class="form-control" id="confirm_password"
								name="confirm_password" type="password"
								value="<s:property value = "member.password"/>">
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"></label>
						<div class="col-md-8">
							<input type="submit" class="btn btn-primary" value="Save Changes">
							<span></span> <input type="button" class="btn btn-default"
								value="Cancel">
						</div>
						<div class="notification">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<hr>
	<script src="<%=request.getContextPath()%>/js/edit-profile.js"></script>
</body>
</html>