<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin</title>

    <!-- Bootstrap Core CSS -->
    <link href="<%=request.getContextPath() %>/css/bootstrap-admin.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="<%=request.getContextPath() %>/css/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<%=request.getContextPath() %>/css/sb-admin-2.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="<%=request.getContextPath() %>/css/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<%=request.getContextPath() %>/css/font-awesome-admin.min.css" rel="stylesheet" type="text/css">

    <!-- DEFIND CSS -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style-admin.css">

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html">SB Admin v2.0</a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="logout?role=${sessionScope.MEMBER.role}"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

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
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="index.html"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Product<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <!-- <li>
                                    <a href="flot.html">Flot Charts</a>
                                </li>
                                <li>
                                    <a href="morris.html">Morris.js Charts</a>
                                </li> -->

                                <li>
                                    <a href="<%=request.getContextPath() %>/page/allProduct.jsp"><i class="fa fa-table fa-fw"></i> allProduct</a>
                                </li>
                                <li>
                                    <a href="add_product.action"><i class="fa fa-edit fa-fw"></i> addProduct</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <!-- <li>
                            <a href="/page/allProduct.jsp"><i class="fa fa-table fa-fw"></i> allProduct</a>
                        </li>
                        <li>
                            <a href="addProduct.html"><i class="fa fa-edit fa-fw"></i> addProduct</a>
                        </li>-->
                        <li>
                            <a href="<%=request.getContextPath() %>/page/login.jsp">Login Page</a>
                        </li> 
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        <marquee width="50%" behavior="alternate" bgcolor="pink">WELCOME TO ADMIN</marquee></h1>

                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Unde quia dolor numquam, laudantium magni illum delectus, harum dolorum fuga! Sed consectetur accusamus aut culpa tempora alias, voluptatibus vero, ad pariatur! Tempore debitis veniam voluptatum provident laudantium nihil, doloremque quibusdam molestias totam dolore quia, fuga explicabo accusantium rerum sed omnis tempora, itaque recusandae. Reiciendis fuga molestiae omnis iure velit, facere veniam repellat odio sequi voluptate doloremque similique? Repudiandae, beatae cupiditate corrupti minima animi voluptas quaerat autem distinctio vitae inventore ipsam id, alias praesentium quos. Amet, nesciunt! Pariatur aliquid expedita sit, hic qui inventore deleniti reiciendis consequuntur facere veritatis quisquam? Quisquam ducimus ea rem quae inventore, quibusdam iure, nisi nemo dolores voluptatum ullam, fugit doloremque tenetur quaerat dolorum incidunt! Maiores officia praesentium nobis ipsam modi aperiam eum veniam, odit consequatur sed numquam est voluptatibus, nesciunt dolor consectetur. Asperiores at incidunt sapiente fuga, beatae cupiditate autem recusandae esse, cum aperiam commodi saepe, ipsum!</p>

                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Neque deleniti inventore consectetur qui distinctio ipsam. Soluta quis tenetur, iure suscipit numquam? Aut architecto sapiente laudantium, repudiandae earum nostrum cumque esse eaque optio deleniti quibusdam obcaecati sunt voluptate quaerat perspiciatis placeat, fugit alias quo quod vitae consequatur minima culpa odit? Dolores.</p>

                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Culpa amet sit accusamus id, aliquid nulla recusandae, non iure, commodi sed placeat soluta tempora. Laborum nihil maiores rerum dolor modi neque minima natus aliquam. Pariatur sint itaque inventore temporibus maxime. Blanditiis facilis eum ex aliquam praesentium veniam harum repellat consequuntur voluptate eaque, quasi aperiam voluptatem voluptas accusantium totam sit ea. Commodi repellendus nesciunt consectetur vero aliquid temporibus possimus neque ab totam?</p>
                </div>
                <!-- /.col-lg-12 -->
            </div>
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <!-- jQuery -->
    <script src="<%=request.getContextPath() %>/js/jquery-admin.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<%=request.getContextPath() %>/js/bootstrap-admin.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<%=request.getContextPath() %>/js/metisMenu.min.js"></script>

    <!-- Morris Charts JavaScript -->
    <script src="<%=request.getContextPath() %>/js/raphael.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/morris.min.js"></script>
    <script src="<%=request.getContextPath() %>/js/morris-data.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="<%=request.getContextPath() %>/js/sb-admin-2.js"></script>

</body>

</html>
