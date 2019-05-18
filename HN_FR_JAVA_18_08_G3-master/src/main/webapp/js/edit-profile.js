$(document).ready(function(){
	$("input[value='Cancel']").click(function(){
		window.location ="home";
	});
})

$("#editform").validate({
	rules: {
		firstname:{
			required : true,
			minlength:2,
			maxlength:7
		},
		lastname:{
			required : true,
			minlength:5,
			maxlength:10
		},
		phone:{
			required : true,
			digits : true,
			minlength: 10,
			maxlength:10
		},
		address:{
			required: true,
			maxlength:50
		},
		email:{
			required: true,
 			email: true
		},
		password: {
			required: true
		},
		confirm_password:{
			required: true,
			equalTo: "#password"
		}
	},
	message:{
		firstname:{
			required : "This field is required",
			minlength: "You first name must consist of at least 2 characters",
			maxlength: "You first name less than  or equal 7 characters"
		},
		lastname:{
			required : "This field is required",
			minlength: "You last name must consist of at least 5 characters",
			maxlength: "You first name less than  or equal 10 characters"
		},
		phone:{
			required : "This field is required",
			digits: "Please enter only digits",
			minlength: "You phone must have 10 characters",
		},
		address:{
			required: "This field is required",
			maxlength: "You first name less than  or equal 50 characters"
		},
		email:{
			required: "This field is required",
 			email: "Please enter  correct xyz@mail.com"
		},
		password: {
			required: "This field is required"
		},
		confirm_password:{
			required: "This field is required",
			equalTo: "Password not same"
		}
	},
	submitHandler: function(form){
		 $.ajax({
		        url: 'update-profile',
		        type: 'POST',
		        data: { 
		        	json: JSON.stringify({
		        		id : $('#editform #id').val(),
			            firstName : $('#editform  #firstname').val(),
			            lastName : $('#editform  #lastname').val(),
			            address : $('#editform  #address').val(),
			            phone : $('#editform  #phone').val(),
			            email : $('#editform  #email').val(),
			            password : $('#editform  #password').val()
		        })},
		        success: function(data) {
		        	if(data.message){
		        		$(".notification").html("<span class='badge badge-pill badge-success'>Update Successfully</span>");
		        	}else{
		        		$(".notification").html("<span class='badge badge-pill badge-danger'>Update Fail</span>");
		        	}
		        }
		 });
	}
});
