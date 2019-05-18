$("#loginform").validate({
	rules: {
		email:{
			required: true,
 			email: true
		},
		password: {
			required: true
		}
	}
	,submitHandler: function(form){
		$("label.error").hide();
		 $.ajax({
	            url: "login", 
	            type: "POST",             
	            data: {
	            	email : $("#login-email").val(),
	            	password : $("#login-password").val()
	            },   
	            success: function(data) {
	            	if(data.notification){
	            		$("#error").hide();
	            		if(data.index === 1){
	            			window.location.href = 'home.action';
	            		}else if(data.index === 2){
	            			window.location.href = 'http://localhost:8080/HN_FR_JAVA_18_08_G3/product.action?productId='+data.productId;
	            		}else if(data.index === 3){
	            			window.location.href = 'shopping-cart';
	            		}else if(data.index == 4){
	            			window.location.href = 'checkout';
	            		}else{
	            			window.location.href = 'admin';
	            		}
	            	}else{
	            		$("#error").show();
	            		$("#error").text("Account not exist!");
	            	}
	            }
	        });
	}
})