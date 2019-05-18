<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html>
<html lang="vi">
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<jsp:include page="head.jsp"></jsp:include>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
.form_image_multil {
	padding-left: 120px;
	padding-top: 20px;
	padding-bottom: 30px !important;
}

.btn_submit_button {
	margin-left: 105px;
}

.btn-icon-defind {
	position: relative;
	top: -73px;
	left: 183px;
}

.error {
	color: red;
}
</style>

</head>

<body>

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<jsp:include page="header.jsp"></jsp:include>

			<jsp:include page="left.jsp"></jsp:include>
		</nav>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">
						<marquee width="50%" behavior="alternate" bgcolor="pink">Add
							Product Database</marquee>
					</h1>
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-body">
							<form id="add_product"
								action="<%=request.getContextPath()%>/addProduct" method="post"
								enctype="multipart/form-data">

								<div class="row">

									<div class="col-lg-7">

										<div class="form-group">
											<label>Product Name</label> <input type="text"
												id="productName" name="productName" class="form-control">
										</div>

										<div class="form-group">
											<label>Image Path</label> <input type="file" name="myFile"
												id="myFile" name="myFile">
										</div>


										
										<div  class="form-group">
											<label for="sel1">ManuFacturer</label> <select
												name="manuFacturerName" class="form-control" id="manuFacturer">

												<s:iterator value="listManuFacturer">
													<option value="<s:property value = "name"/>">
														<s:property value="name" />
													</option>
												</s:iterator>
											</select>
										</div>

										
										<div class="form-group">
											<label for="sel1">Category</label> <select
												name="categoryName" class="form-control" id="category">

												<s:iterator value="listCategory">

													<option value="<s:property value = "name"/>">
														<s:property value="name" />

													</option>

												</s:iterator>

											</select>
										</div>

										<div class="form-group">
											<label>Color </label>
											<select name="color">
												<option value="Black" selected="selected">Black</option>
												<option value="Red">Red</option>
												<option value="Gold">Gold</option>
												<option value="White" >White</option>
												<option value="Gray" >Gray</option>
												<option value="Bright Blue">Bright Blue</option>
											</select>
										</div>

										<div class="form-group">
											<label>Price </label> <input type="text" name="price"
												class="form-control">
										</div>
										
										<div class="form-group">
											<label>Description </label> <textarea name="description" rows="4" class="form-control"></textarea>
										</div>


									</div>

									<div class="col-lg-5 form_image_multil">
										<div class="form-group">
											<input type="file" name="imageDetailFileName"
												id="imageDetailFileName">
										</div>

										<div class="form-group">
											<input type="file" name="imageDetailFileName"
												id="imageDetailFileName">
										</div>

										<div class="form-group">
											<input type="file" name="imageDetailFileName"
												id="imageDetailFileName">
										</div>

										<div class="form-group" id="insert"></div>

										<button type="button" class="btn btn-primary"
											id="adddImageDetail">add image detail</button>
									</div>

									<button type="submit" class="btn btn-default btn_submit_button">Submit
										Button</button>
									<button type="reset" class="btn btn-default">Reset
										Button</button>
								</div>
							</form>
						</div>
						<!-- /.row (nested) -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /#page-wrapper -->


	<script>
		$(document)
				.ready(
						function() {
							$("#adddImageDetail")
									.click(
											function() {

												$("#insert")
														.append(
																'<label for="exampleFormControlFile1">upload  file detail</label> <input type="file" name="imageDetailFileName"id="imageDetailFileName">');
											});
						});
	</script>

	<jsp:include page="footer.jsp"></jsp:include>

</body>

</html>
