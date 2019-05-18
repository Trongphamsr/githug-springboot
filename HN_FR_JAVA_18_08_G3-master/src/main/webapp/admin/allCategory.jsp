<!DOCTYPE html>
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

<body onload="allCategory()">

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">

			<jsp:include page="header.jsp"></jsp:include>

			<jsp:include page="left.jsp"></jsp:include>
		</nav>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-9">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3>
								<marquee width="60%" behavior="alternate" bgcolor="pink">Select
									All Category</marquee>
							</h3>
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<table width="100%"
								class="table table-striped table-bordered table-hover"
								id="dataTables-example">
								<thead>
									<tr>
										<th>Stt</th>
										<th>Name</th>
										<th>Action</th>
									</tr>
								</thead>

								<tbody id="tbody">
								</tbody>

							</table>
						</div>
						<!-- /.panel-body -->
					</div>



					<div class="container" id="updateBlock">
						<div class="modal fade" id="updateModal" role="dialog">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">Update New Information</h4>
									</div>

									<div class="modal-body">
										<div class="form-group">
											<input type="text" name="name" id="name"
												class="form-control input-sm" placeholder="name"> <input
												type="hidden" name="hidden" id="hidden">
										</div>

										<button onclick="updateCategory();"
											class="btn btn-info btn-block">Update</button>
										<div id="responseCategory" class="text-center"
											style="margin-top: 13px;"></div>
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.row -->
	</div>
	<!-- /#page-wrapper -->



	<jsp:include page="footer.jsp"></jsp:include>
	<script>
    $(document).ready(function() {
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
    </script>

</body>

</html>
