$(document).ready(function(){
	// xu ly phan form search
	var sortNameOptions = $(".form-search-product select[name='sortName'] option");
	$(sortNameOptions).attr('selected',false);
	for(let i=0;i<sortNameOptions.length;i++){
		if(sortNameOptions[i].value === $(".input-sortName").val()){
			sortNameOptions[i].selected = true;
		}
	}
	var sortPriceOptions = $(".form-search-product select[name='sortPrice'] option");
	$(sortPriceOptions).attr('selected',false);
	for(let i=0;i<sortPriceOptions.length;i++){
		if(sortPriceOptions[i].value === $(".input-sortPrice").val()){
			sortPriceOptions[i].selected = true;
		}
	}
	var priceMinOptions = $(".form-search-product select[name='priceMin'] option");
	$(priceMinOptions).attr('selected',false);
	for(let i=0;i<priceMinOptions.length;i++){
		if(priceMinOptions[i].value === $(".input-priceMin").val()){
			priceMinOptions[i].selected = true;
		}
	}
	var priceMaxOptions = $(".form-search-product select[name='priceMax'] option");
	$(priceMaxOptions).attr('selected',false);
	for(let i=0;i<priceMaxOptions.length;i++){
		if(priceMaxOptions[i].value === $(".input-priceMax").val()){
			priceMaxOptions[i].selected = true;
		}
	}
	// ket thuc su ly ohan form search
	
	// xu ly phan trang
	let currentPage = 1;
	$(document).ready(function(){
		$(".pagination li a").click(function(event){
			event.preventDefault();
			let isDisabled = $(this).parent().attr('class');
			if(isDisabled !== 'disabled'){
				$(".grid-product .lam-mo").css({
					"z-index" : 10,
					"opacity" : 0.7
				});
				$(".img-gif").css({
					"display" : "block"
				});
				$(".pagination li a").parent().removeClass('active');
				$(this).parent().addClass('active');
				let id = $(this).attr('id');
				if(id === 'previous' || id === 'next')
					currentPage += parseInt($(this).attr('data-index'));
				else
					currentPage = parseInt($(this).attr('data-index'));
				if(currentPage === 1)
					$("#previous").parent().addClass('disabled');
				else
					$("#previous").parent().removeClass('disabled');
				$.ajax({
					url: $('.input-url').val(),
					type: 'get',
					dataType: 'json',
					data: {
						currentPage: currentPage,
						categoryId: $('.input-categoryId').val(),
						sortName: $('.input-sortName').val(),
						sortPrice: $('.input-sortPrice').val(),
						priceMin: $('.input-priceMin').val(),
						priceMax: $('.input-priceMax').val()
					},
					success: function(result){
						$(".grid-product .product").css('display','none');
						let listProduct = result.listProduct;
						console.log(listProduct.length);
						let listCurrentProduct = $(".grid-product .product");
						if(listProduct.length > 0){
							for(let i=0;i<listProduct.length;i++){
								$(listCurrentProduct[i]).find('.product-img img').attr('src',listProduct[i].imagePath);
								$(listCurrentProduct[i]).find('.product-category').text(listProduct[i].category.name);
								$(listCurrentProduct[i]).find('.product-price').text(listProduct[i].price);
								$(listCurrentProduct[i]).find('.product-name a').text(listProduct[i].productName);
								$(listCurrentProduct[i]).find('.product-name a').attr('href','product.action?id=' + listProduct[i].id);
								$(listCurrentProduct[i]).find('.a-detail').attr('href','product.action?id=' + listProduct[i].id);
								$(listCurrentProduct[i]).css('display','block');
							}
						}
						$(".grid-product .lam-mo").css({
							"z-index" : -1,
							"opacity": 0
						});
						$(".img-gif").css({
							"display" : "none"
						})
					}
				})
			}
		})
	})
	
})