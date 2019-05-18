<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html lang="vi">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<jsp:include page="head.jsp"></jsp:include>

<style type="text/css">
.btn-icon-defind {
	position: relative;
	top: -73px;
	left: 183px;
}

.img_curent {
	border: 1px solid gray;
	top: -79px;
	left: 195px;
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
						<marquee width="50%" behavior="alternate" bgcolor="pink">Update
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
							<form action="updateProduct?id=${id}" method="post"
								enctype="multipart/form-data">
								<s:iterator value="product">
									<div class="row">
										<div class="col-lg-10">
											<div class="form-group">

												<input type="text" disabled id="id" name="id"
													class="form-control" value='<s:property value="id"/>'>
											</div>
											<div class="form-group">
												<label>Product Name</label> <input type="text"
													id="productName" name="productName" class="form-control"
													value='<s:property value="productName"/>'>
											</div>


											<div class="form-group">
												<a
													class="btn-icon-defind btn btn-danger btn-circle icon_del"><i
													class="fa fa-times"></i></a> <img class="img_curent" id="img_curent"
													src="image/<s:property value="product.imagePath"/>"
													width="150" height="150" /> <input type="file"
													id="imagePathName" name="myFile" >
											</div>


											<div class="form-group">
												<input type="hidden" name="manuFacturerId"
													class="form-control"
													value='<s:property value="manuFacturer.id"/>'>
											</div>

											<div class="form-group">
												<input type="hidden" name="categoryId" class="form-control"
													value='<s:property value="category.id"/>'>
											</div>

											<div class="form-group">
												<label>Color </label> <select name="color">
													<option value="Black" selected="selected">Black</option>
													<option value="Red">Red</option>
													<option value="Gold">Gold</option>
													<option value="White">White</option>
													<option value="Gray">Gray</option>
													<option value="Bright Blue">Bright Blue</option>
												</select>
											</div>

											<div class="form-group">
												<label>Price Name</label> <input type="text"
													value='<s:property value="price"/>' name="price"
													class="form-control">

											</div>

											<div class="form-group">
												<label>Description </label>
												<textarea name="description" rows="4" class="form-control">
												<s:property value="description" />
											</textarea>
											</div>

											<button type="submit"
												class="btn btn-default btn_submit_button">Update
												Product</button>
											<button type="reset" class="btn btn-default">Cancel</button>
										</div>
									</div>
								</s:iterator>
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


	<jsp:include page="footer.jsp"></jsp:include>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/update-product.js"></script>
	
</body>

</html>
