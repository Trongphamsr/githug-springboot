<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="vi">

<head>

<title>Fresher Academy - Fresher Java 08</title>

<s:include value="/page/common_head.jsp"></s:include>

<!-- custom css -->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/home.css" />

<!-- Jquery ui css -->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/jquery-ui.min.css" />
</head>
<body>
	<!-- Header -->
	<s:include value="/page/header.jsp"></s:include>
	<!-- End Header -->
	<!-- Carousel -->
	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<div class="item active">
					<img src="<%=request.getContextPath()%>/img/carousel-1.png"
						alt="Los Angeles">
				</div>

				<div class="item">
					<img src="<%=request.getContextPath()%>/img/carousel-2.png"
						alt="Chicago">
				</div>

				<div class="item">
					<img src="<%=request.getContextPath()%>/img/carousel-3.png"
						alt="New York">
				</div>
			</div>

			<!-- Left and right controls -->
			<a id="prev" class="left carousel-control" href="#myCarousel"
				data-slide="prev"> <span><i class="fas fa-chevron-left"></i></span>
			</a> <a id="next" class="right carousel-control" href="#myCarousel"
				data-slide="next"> <span><i class="fas fa-chevron-right"></i></span>
			</a>
		</div>
		<!-- /Carousel-->
	</div>

	<!-- NAVIGATION -->
	<nav id="navigation">
		<!-- container -->
		<div class="container">
			<!-- responsive-nav -->
			<div id="responsive-nav">
				<!-- NAV -->
				<ul class="main-nav nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#">Hot Deals</a></li>
					<li><a href="#">Categories</a></li>
					<li><a href="#">Laptops</a></li>
					<li><a href="#">Smartphones</a></li>
					<li><a href="#">Cameras</a></li>
					<li><a href="#">Accessories</a></li>
				</ul>
				<!-- /NAV -->
			</div>
			<!-- /responsive-nav -->
		</div>
		<!-- /container -->
	</nav>
	<!-- /NAVIGATION -->

	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">

				<!-- Products tab & slick -->
				<div class="col-md-12">
					<p id="new-product-tile">New Product</p>
					<div class="row">
						<div class="products-tabs">
							<!-- tab -->
							<div id="tab1" class="tab-pane active">
								<div class="products-slick" data-nav="#slick-nav-1">
									<s:iterator value="listNewProduct">
										<s:url action="product" var="productAction">
											<s:param name="productId">
												<s:property value="id" />
											</s:param>
										</s:url>
										<!-- product -->
										<div class="product new-product">
											<div class="product-img">
												<img src="image/<s:property
															value="imagePath" />"
													alt="">
												<div class="product-label">
													<span class="sale">-30%</span> <span class="new">NEW</span>
												</div>
											</div>
											<div class="product-body">
												<p class="product-category">
													<s:property value="category.name" />
												</p>
												<h3 class="product-name">
													<a href="${productAction }"><s:property
															value="productName" /></a>
												</h3>
												<h4 class="product-price">$
													<s:property value="price"/>
												</h4>
												<div class="product-rating">
													<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
														class="fa fa-star"></i> <i class="fa fa-star"></i> <i
														class="fa fa-star"></i>
												</div>
												<div class="add-cart">
													<button class="btn btn-info btn-detail test">
														<a class="a-detail" href="${productAction }"><i
															class="fa fa-info-circle" aria-hidden="true"></i> Detail</a>
													</button>
													<button class="btn btn-warning btn-cart"
														data-id='<s:property value="id"/>'>
														<a class="a-add-cart"><i class="fa fa-shopping-cart"></i>
															Add to cart</a>
													</button>
												</div>
											</div>
										</div>
										<!-- /product -->
									</s:iterator>
								</div>
								<div id="slick-nav-1" class="products-slick-nav"></div>
							</div>
							<!-- /tab -->
						</div>
					</div>
				</div>
				<!-- Products tab & slick -->
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->

	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">

			<div class="row">
				<!-- section title -->
				<div class="col-md-12">
					<div class="section-title">
						<p id="new-product-tile">List Product</p>
					</div>
				</div>
				<!-- /section title -->
			</div>

			<div class="row search-by-type">
				<form action="searchProduct" method="get" class="form-search-product">
					<div class="col-md-2">
						<label>Category </label>
						<select name="categoryId">
							<option value="0">All</option>
							<s:iterator value="listCategory">
								<option value='<s:property value="id"/>'><s:property value="name"/></option>
							</s:iterator>
						</select>
					</div>
					<div class="col-md-2">
						<label>Name </label>
						<select name="sortName">
							<option value="Default">Default</option>
							<option value="ASC">ASC</option>
							<option value="DESC">DESC</option>
						</select>
					</div>
					
					<div class="col-md-2">
						<label>Price </label>
						<select name="sortPrice">
							<option value="Default">Default</option>
							<option value="ASC">ASC</option>
							<option value="DESC">DESC</option>
						</select>
					</div>
					
					<div class="col-md-2">
						<label>Min </label>
						<select name="priceMin">
							<option value="0">0</option>
							<option value="10000000">10000000</option>
							<option value="20000000">20000000</option>
							<option value="30000000">30000000</option>
						</select>
					</div>
					
					<div class="col-md-2">
						<label>Max </label>
						<select name="priceMax">
							<option value="30000000">30000000</option>
							<option value="20000000">20000000</option>
							<option value="10000000">10000000</option>
							<option value="0">0</option>
						</select>
					</div>
					
					<div class="col-md-2 btn">
						<input type="submit" value="Search" class="btn-search"/>
					</div>
				</form>
			</div>
			<div class="div-grid-product">
				<p class="img-gif">
					<img src="<%=request.getContextPath()%>/img/magnify.gif" />
				</p>
				<div class="grid-product">
					<div class="lam-mo"></div>
					<s:iterator value="listProduct">
						<s:url action="product" var="productAction">
							<s:param name="productId">
								<s:property value="id" />
							</s:param>
						</s:url>
						<!-- product -->
						<div class="product">
							<div class="product-img">
								<img src="image/<s:property value="imagePath"/>"
									alt="">
								<div class="product-label">
									<span class="sale">-30%</span> <span class="new">NEW</span>
								</div>
							</div>
							<div class="product-body">
								<p class="product-category">
									<s:property value="category.name" />
								</p>
								<h3 class="product-name">
									<a href="${productAction }"><s:property value="productName" /></a>
								</h3>
								<h4 class="product-price">
									$<s:property value="price"/>

								</h4>
								<div class="product-rating">
									<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
										class="fa fa-star"></i> <i class="fa fa-star"></i> <i
										class="fa fa-star"></i>
								</div>
								<div class="add-cart">
									<button class="btn btn-info btn-detail">
										<a class="a-detail" href="${productAction }"><i
											class="fa fa-info-circle" aria-hidden="true"></i> Detail</a>
									</button>
									<button class="btn btn-warning btn-cart"
										data-id='<s:property value="id"/>'>
										<a><i class="fa fa-shopping-cart"></i> Add to cart</a>
									</button>
								</div>
							</div>
						</div>
						<!-- /product -->
					</s:iterator>
				</div>
				<!-- /Grid-product -->
			</div>
			<!-- Pagination -->
			<ul class="pagination">
				<li class="disabled"><a id="previous" href="#" data-index="-1">Previous</a></li>
				<s:iterator begin="1" end="totalPages">
					<s:if test="top == 1">
						<li class="active"><a href="#"
							data-index='<s:property value="top"/>'><s:property
									value="top" /></a></li>
					</s:if>
					<s:else>
						<li><a href="#" data-index='<s:property value="top"/>'><s:property
									value="top" /></a></li>
					</s:else>
				</s:iterator>
				<li><a id="next" href="#" data-index="1">Next</a></li>
			</ul>
			<!-- /Pagination -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->

	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-4 col-xs-6">
					<div class="section-title">
						<h4 class="title">Top selling</h4>
						<div class="section-nav">
							<div id="slick-nav-3" class="products-slick-nav"></div>
						</div>
					</div>

					<div class="products-widget-slick" data-nav="#slick-nav-3">
						<div>
							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product07.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product08.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product09.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- product widget -->
						</div>

						<div>
							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product01.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product02.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product03.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- product widget -->
						</div>
					</div>
				</div>

				<div class="col-md-4 col-xs-6">
					<div class="section-title">
						<h4 class="title">Top selling</h4>
						<div class="section-nav">
							<div id="slick-nav-4" class="products-slick-nav"></div>
						</div>
					</div>

					<div class="products-widget-slick" data-nav="#slick-nav-4">
						<div>
							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product04.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product05.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product06.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- product widget -->
						</div>

						<div>
							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product07.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product08.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product09.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- product widget -->
						</div>
					</div>
				</div>

				<div class="clearfix visible-sm visible-xs"></div>

				<div class="col-md-4 col-xs-6">
					<div class="section-title">
						<h4 class="title">Top selling</h4>
						<div class="section-nav">
							<div id="slick-nav-5" class="products-slick-nav"></div>
						</div>
					</div>

					<div class="products-widget-slick" data-nav="#slick-nav-5">
						<div>
							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product01.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product02.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product03.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- product widget -->
						</div>

						<div>
							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product04.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product05.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- /product widget -->

							<!-- product widget -->
							<div class="product-widget">
								<div class="product-img">
									<img src="<%=request.getContextPath()%>/img/product06.png"
										alt="">
								</div>
								<div class="product-body">
									<p class="product-category">Category</p>
									<h3 class="product-name">
										<a href="#">product name goes here</a>
									</h3>
									<h4 class="product-price">
										$980.00
										<del class="product-old-price">$990.00</del>
									</h4>
								</div>
							</div>
							<!-- product widget -->
						</div>
					</div>
				</div>

			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /SECTION -->

	<!-- NEWSLETTER -->
	<div id="newsletter" class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<div class="newsletter">
						<p>
							Sign Up for the <strong>NEWSLETTER</strong>
						</p>
						<form>
							<input class="input" type="email" placeholder="Enter Your Email">
							<button class="newsletter-btn">
								<i class="fa fa-envelope"></i> Subscribe
							</button>
						</form>
						<ul class="newsletter-follow">
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-instagram"></i></a></li>
							<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /NEWSLETTER -->
	
	<input type="hidden" value="${categoryId }" class="input-categoryId"/>
	<input type="hidden" value="${sortName }" class="input-sortName"/>
	<input type="hidden" value="${sortPrice }" class="input-sortPrice"/>
	<input type="hidden" value="${priceMin }" class="input-priceMin"/>
	<input type="hidden" value="${priceMax }" class="input-priceMax"/>
	<input type="hidden" value="${url }" class="input-url"/>

	<!-- Footer -->
	<s:include value="/page/common_footer.jsp"></s:include>
	<!-- /Footer -->

	<!-- Jquery ui js -->
	<script src="<%=request.getContextPath()%>/js/jquery-ui.min.js"></script>

	<!-- cart js -->
	<script src="<%=request.getContextPath()%>/js/cart.js"></script>
	<!-- home.js -->
	<script type="text/javascript" src="<%= request.getContextPath() %>/js/home.js"></script>
	<!-- /home.js -->


</body>

</html>