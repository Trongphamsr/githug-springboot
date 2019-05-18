<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="vi">

<head>
<title>Check out</title>

<s:include value="/page/common_head.jsp"></s:include>

<!-- Custom css -->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/checkout.css" />
	<script src="https://unpkg.com/sweetalert2@7.17.0/dist/sweetalert2.all.js"></script>
</head>
<body>
	<s:include value="/page/header.jsp"></s:include>

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

	<!-- BREADCRUMB -->
	<div id="breadcrumb" class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<h3 class="breadcrumb-header">Checkout</h3>
					<ul class="breadcrumb-tree">
						<li><a href="#">Home</a></li>
						<li class="active">Checkout</li>
					</ul>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /BREADCRUMB -->

	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">

				<div class="col-md-7">
					<div class="container">
						<div class="row">

							<aside class="col-sm-6 payment">
								<p>Payment</p>

								<article class="card">
									<div class="card-body p-5">

										<ul class="nav bg-light nav-pills rounded nav-fill mb-3"
											role="tablist">
											<li class="nav-item active"><a class="nav-link "
												data-toggle="pill" href="#nav-tab-card"> <i
													class="fa fa-credit-card"></i> Credit Card
											</a></li>
											<li class="nav-item"><a class="nav-link"
												data-toggle="pill" href="#nav-tab-paypal"> <i
													class="fa fa-paypal"></i> Paypal
											</a></li>
											<li class="nav-item"><a class="nav-link"
												data-toggle="pill" href="#nav-tab-bank"> <i
													class="fa fa-university"></i> Bank Transfer
											</a></li>
										</ul>

										<div class="tab-content">
											<div class="tab-pane fade active in " id="nav-tab-card">
												<p class="alert alert-success">Some text success or
													error</p>
												<form role="form" id="form-infor">
													<div class="form-group">
														<label for="username">Full name (on the card)</label> <input
															type="text" class="form-control" name="username"
															placeholder="">
													</div>
													<!-- form-group.// -->

													<div class="form-group">
														<label for="cardNumber">Card number</label>
														<div class="input-group">
															<input type="text" class="form-control" name="cardNumber"
																placeholder="">
															<div class="input-group-append">
																<span class="input-group-text text-muted"> <i
																	class="fa fa-cc-visa"></i> <i class="fa fa-cc-amex"></i>
																	<i class="fa fa-cc-mastercard"></i>
																</span>
															</div>
														</div>
													</div>
													<!-- form-group.// -->

													<div class="row">
														<div class="col-sm-8">
															<div class="form-group">
																<label> <span class="hidden-xs">Expiration</span>
																</label>
																<div class="input-group">
																	<input type="text" class="form-control"
																		placeholder="MM" name="month"> 
																</div>
																<div class="input-group">
																	<input
																		type="text" class="form-control" placeholder="YY"
																		name="year">
																</div>
															</div>
														</div>
														<div class="col-sm-4">
															<div class="form-group">
																<label data-toggle="tooltip" title=""
																	data-original-title="3 digits code on back side of the card">CVV
																	<i class="fa fa-question-circle"></i>
																</label> <input type="text" name="cvv" class="form-control">
															</div>
															<!-- form-group.// -->
														</div>
													</div>
													<!-- row.// -->
													<input class="subscribe btn btn-primary btn-block"
														type="submit" value="Confirm" />
												</form>
											</div>
											<!-- tab-pane.// -->
											<div class="tab-pane fade" id="nav-tab-paypal">
												<p>Paypal is easiest way to pay online</p>
												<p>
													<button type="button" class="btn btn-primary">
														<i class="fa fa-paypal"></i> Log in my Paypal
													</button>
												</p>
												<p>
													<strong>Note:</strong> Lorem ipsum dolor sit amet,
													consectetur adipisicing elit, sed do eiusmod tempor
													incididunt ut labore et dolore magna aliqua.
												</p>
											</div>
											<div class="tab-pane fade" id="nav-tab-bank">
												<p>Bank account details</p>
												<dl class="param">
													<dt>BANK:</dt>
													<dd>TP BANK</dd>
												</dl>
												<dl class="param">
													<dt>Account number:</dt>
													<dd>02139348769</dd>
												</dl>
												<dl class="param">
													<dt>IBAN:</dt>
													<dd>123456789</dd>
												</dl>
												<p>
													<strong>Note:</strong> Lorem ipsum dolor sit amet,
													consectetur adipisicing elit, sed do eiusmod tempor
													incididunt ut labore et dolore magna aliqua.
												</p>
											</div>
											<!-- tab-pane.// -->
										</div>
										<!-- tab-content .// -->

									</div>
									<!-- card-body.// -->
								</article>
								<!-- card.// -->


							</aside>
							<!-- col.// -->
						</div>
						<!-- row.// -->

					</div>
					<!--container end.//-->
				</div>

				<!-- Order Details -->
				<div class="col-md-5 order-details">
					<div class="section-title text-center">
						<h3 class="title">Your Order</h3>
					</div>
					<div class="order-summary">
						<div class="order-col">
							<div>
								<strong>PRODUCT</strong>
							</div>
							<div>
								<strong>TOTAL</strong>
							</div>
						</div>
						<div class="order-products">
							<s:iterator value="listItem">
								<div class="order-col">
									<div class="product-number">
										<s:property value="numberProduct" />
										x
										<s:property value="product.productName" />
										-
										<s:property value="product.color" />
									</div>
									<div class="total-price">${product.price*numberProduct}</div>
								</div>
							</s:iterator>
						</div>
						<div class="order-col">
							<div>Shiping</div>
							<div>
								<strong>FREE</strong>
							</div>
						</div>
						<div class="order-col">
							<div>
								<strong>TOTAL</strong>
							</div>
							<div>
								<strong class="order-total">$2940.00</strong>
							</div>
						</div>
					</div>
				</div>
				<!-- /Order Details -->
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
							<li><a href="#"> <i class="fa fa-facebook"></i>
							</a></li>
							<li><a href="#"> <i class="fa fa-twitter"></i>
							</a></li>
							<li><a href="#"> <i class="fa fa-instagram"></i>
							</a></li>
							<li><a href="#"> <i class="fa fa-pinterest"></i>
							</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /NEWSLETTER -->

	<!-- FOOTER -->
	<s:include value="/page/common_footer.jsp"></s:include>
	<!-- /FOOTER -->
	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
	<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/checkout.js"></script>
</body>

</html>