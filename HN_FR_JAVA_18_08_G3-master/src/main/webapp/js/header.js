$(document).ready(function() {
	$.ajax({
		url: 'getNumberProduct',
		type: 'POST',
		dataType: 'json',
		success: function(result){
			$("#cartNumber").html(result.cartNumber);
			}
		});
		
	$(window).scroll(function(event) {
		/* Act on the event */
		if ($(document).scrollTop() >= 10) {
			$("#header").addClass('navbar-fixed-top');
			$("#header").css("z-index","50");
		} else {
			$("#header").removeClass('navbar-fixed-top');
		}
	});
	
	$.ajax({
		url: 'getMember',
		type: 'POST',
		dataType: 'json',
		success: function(result){
			if(! result.message){
				$("div > .sign-in").show();
				$("div > .profile").hide();
			}else{
				$("div > .sign-in").hide();
				$("div > .profile").show();
				$("div > .profile ").html('<li><a href="myaccount"><i class="fa fa-user-o"></i>'+result.fullName+'</a></li>'
						                 + '<li ><a href="logout"><i class="fas fa-sign-out-alt"></i> Logout</a></li>');				
				}
			}
		});
	
	$(".header-search .input").keyup(function(){
		let val  = $(this).val();
		let ul = $(".header-search .list-product");
		if(val !== ''){
			$.ajax({
				url: 'searchProductByValue',
				type: 'get',
				dataType: 'json',
				data: {
					val : val
				},
				success: function(result){
					let listProduct = result.listProduct;
					console.log(listProduct.length);
					if(listProduct != null && listProduct.length > 0){
						let text = '';
						for(let product of listProduct){
							text += "<li><span class='img'><img src='" + product.imagePath + "'/></span><a class='name' href='product?productId=" + product.id + "'>" + product.productName + "</a></li>";
						}
						$(ul).html(text);
						$(ul).css('display','block');
					}
				}
			})
		}
		$(ul).css('display','none');
	})
	
	let mouseOver = false;
	$(".header-search > div > ul").mouseover(function(){
		mouseOver = true;
	})
	
	$(".header-search .input").focusout(function(){
		if(!mouseOver)
			$(".header-search .list-product").css('display','none');
	})
})