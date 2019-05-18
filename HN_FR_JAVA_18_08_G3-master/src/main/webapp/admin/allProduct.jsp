<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<html lang="vi">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Bootstrap Admin Theme</title>

<jsp:include page="head.jsp"></jsp:include>

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
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3>
								<marquee width="60%" behavior="alternate" bgcolor="pink">Select
									All Product From Table Product</marquee>
							</h3>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<table width="100%"
								class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>No</th>
										<th style="display: none">id</th>
										<th>Name</th>
										<th>Image</th>
										<th>Color</th>
										<th>Price</th>
										<th>Action</th>
									</tr>
								</thead>

								<tbody>
									<s:set var="counter" value="0" />
									<s:iterator value="listProduct">
										<s:set var="counter" value="%{#counter+1}" />
										<tr>
											<td><s:property value="#counter" /></td>
											<td style="display: none"><s:property value="id" /></td>
											<td><s:property value="productName" /></td>
											<td>
												<%-- <img src="/${imagePathFileName}" --%> <img
												src="image/${imagePath}" width="40" height="40" />

											</td>
											<td><s:property value="color" /></td>
											<td>$ <s:property value="price" /></td>

											<td class="center td_all"><a
												onclick="return confirm('Are you sure you want to delete this item?');"
												class="alert alert-danger"
												href="deleteProduct?id=<s:property value="id"/>">delete</a>
												<a class="alert alert-success"
												href="fetchProduct?id=<s:property value="id"/>">edit</a></td>

											<%-- class="alert alert-success" href="updateProduct?submitType=updatedata&id=<s:property value="id"/>">edit</a></td> --%>
										</tr>
									</s:iterator>

								</tbody>
							</table>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
		</div>
		<!-- /#page-wrapper -->

	</div>

	<jsp:include page="footer.jsp"></jsp:include>
	<script>
		$(document).ready(function() {
			$('#dataTables-example').DataTable({
				responsive : true
			});
		});
	</script>

</body>

</html>
