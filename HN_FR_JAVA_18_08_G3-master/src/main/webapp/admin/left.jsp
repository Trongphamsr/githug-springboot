<%@taglib uri="/struts-tags" prefix="s"%>
<div class="navbar-default sidebar" role="navigation">
	<div class="sidebar-nav navbar-collapse">
		<ul class="nav" id="side-menu">
			<li class="sidebar-search">
				<div class="input-group custom-search-form">
					<input type="text" class="form-control" placeholder="Search...">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<i class="fa fa-search"></i>
						</button>
					</span>
				</div>
			</li>
			<li><a href=<%=request.getContextPath()%> /admin_home><i
					class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>


			<!-- product -->

			<li><a href="javascript:void(0)"><i
					class="fa fa-bar-chart-o fa-fw"></i>Product<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">

					<li><a href="<%=request.getContextPath()%>/allProduct"><i
							class="fa fa-table fa-fw"></i> Manager Product</a></li>
					<li><a
						href="<%=request.getContextPath()%>/idCategory_ManuFacturer"><i
							class="fa fa-edit fa-fw"></i> Add New Product</a></li>
				</ul></li>


			<!-- category -->

			<li><a href="javascript:void(0)"><i
					class="fa fa-bar-chart-o fa-fw"></i> Category<span class="fa arrow"></span></a>
				<ul class="nav nav-second-level">

					<li><a
						href="<%=request.getContextPath()%>/admin/allCategory.jsp"><i
							class="fa fa-table fa-fw"></i> Manager Category</a></li>
					<li><a
						href="<%=request.getContextPath()%>/admin/addCategory.jsp"><i
							class="fa fa-edit fa-fw"></i> Add New Category</a></li>
				</ul></li>



			<!-- factory -->

			<li><a href="javascript:void(0)"><i
					class="fa fa-bar-chart-o fa-fw"></i> ManuFacturer<span
					class="fa arrow"></span></a>
				<ul class="nav nav-second-level">

					<li><a
						href="<%=request.getContextPath()%>/admin/allManuFacturer.jsp"><i
							class="fa fa-table fa-fw"></i>Manager ManuFacturer</a></li>
					<li><a
						href="<%=request.getContextPath()%>/admin/addManuFacturer.jsp"><i
							class="fa fa-edit fa-fw"></i>Add New ManuFacturer</a></li>
				</ul></li>

			<li><a href="home">Home Page</a></li>
		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>
<!-- /.navbar-static-side -->