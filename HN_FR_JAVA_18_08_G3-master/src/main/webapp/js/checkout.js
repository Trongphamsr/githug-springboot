$(document).ready(function() {
	var sum = 0;
	$(".order-products > .order-col > .total-price").each(function() {
		$(this).text("$"+convertFormatNumber($(this).text()));
	})
	$(".order-products > .order-col > .total-price").each(function() {
		sum += parseInt(convertTypeMoney($(this).html()));
	})
	$(".order-col > div > .order-total").text("$"+convertFormatNumber(sum.toString()));

	function convertFormatNumber(input){
		var length = input.length;
		var count = 0;
		var result="";
		for( var i = length -1 ; i >= 0 ; i--){
			count++;
			result += input[i];
			if(count % 3 === 0 && count !== 0){
				result += ".";
			}
		}
		return reverse(result);
	}
	
	function reverse(input){
		var result="";
		for(var i = input.length-1 ; i >= 0 ; i--){
			result += input[i];
		}
		if(result[0] === ".") {
			result = result.substr(1);
		}
		return result;
	}
	
	function convertTypeMoney(input){
		var result ="";
		input = input.toString();
		input = input.substr(1);
		for(var i = 0 ; i < input.length ; i++){
			if(input[i] !== '.'){
				result += input[i];
			}
		}
		return result;
	}
	$("#form-infor").validate({
		rules: {
			username:{
				required : true,
				minlength:10,
				maxlength:20
			},
			cardNumber:{
				required : true,
				digits : true,
				minlength:16,
				maxlength:16
			},
			month:{
				required : true,
				digits : true,
				minlength: 2,
				maxlength: 2
			},
			year:{
				required: true,
				digits : true,
				minlength: 2,
				maxlength: 2
			},
			cvv:{
				required: true,
				digits : true,
				minlength: 3,
				maxlength: 3
			}
		},
		message:{
			username:{
				required : "This field is required",
				minlength: "You first full name must consist of at least 10 characters",
				maxlength: "You first full name less than  or equal 20 characters"
			},
			cardNumber:{
				required : "This field is required",
				digits: "Please enter only digits",
				minlength: "Cart number must have enough 16 digits",
				maxlength: "Cart number must have enough 16 digits"
			},
			month:{
				required : "This field is required",
				digits: "Please enter only digits",
				minlength: "You phone must have 2 digits",
				maxlength: "You phone must have 2 digits"
			},
			year:{
				required : "This field is required",
				digits: "Please enter only digits",
				minlength: "You phone must have 2 digits",
				maxlength: "You phone must have 2 digits"
			},
			cvv:{
				required : "This field is required",
				digits: "Please enter only digits",
				minlength: "You phone must have 3 digits",
				maxlength: "You phone must have 3 digits"
			}
		},
		submitHandler: function(form){
			var price = $(".order-col > div > .order-total").text();
			if(price !== "$0"){
				$.ajax({
			        url: 'confirm-payment',
			        type: 'POST',
			        data: { 
			        	},
			        success: function(data) {
			        	if(data.message){
			        		swal({ title:"Thank you!",
			        			 text:"Please wait for the product to be shipped within 2-3 days",
			        			 type:"success",
			        			 showCloseButton: true,
			        			 focusConfirm: true,
			        			 showConfirmButton: false,
			        			 footer: "<a id='continue-shop' href=home><i class='fa fa-shopping-cart' class='btn btn-warning'></i> Continue Shopping</a>"}).
			        			 then(function(){
			        				 window.location.href = 'home';
			        		});
			        	}else{
			        		alert("fail")
			        	}
			        }
			 });
			}else{
				alert("Can't processing orders");
			}
		}
	});
});

