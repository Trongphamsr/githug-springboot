$(document).ready(function() {
	$("#register-form").validate({
		rules: {
			Password: "required",

			FirstName: {
				required: true,
				maxlength: 7,
				minlength:2
			},

			LastName: {
				required: true,
				maxlength: 10,
				minlength:5
			},
			Address: {
				required: true,
				maxlength: 50
			},
			Phone: {
				required: true,
				number: true,
				maxlength: 10,
				minlength:10
			},
			Email: {
				required:true,
				email:true
			},
			ConfirmPassword: {
				required: true,
				equalTo: "#Password"

			}
		},
		messages: {
			Password: "This item can't be empty",

			FirstName: {
				required: "This item can't be empty",
				maxlength: "FirstName must be less than or equals 7 characters",
				minlength: "FirstName must be more than or equals 2 characters"
			},
			LastName: {
				required: "This item can't be empty",
				maxlength: "FirstName must be less than or equals 10 characters",
				minlength: "FirstName must be more than or equals 5 characters"
			},
			Address: {
				required: "This item can't be empty",
				maxlength: "The address must be less than or equal to 50 characters"
			},
			Phone: {
				required: "This item can't be empty",
				number: "The Phone must is number",
				maxlength: "The Phone must is 10 number",
				minlength: "The Phone must is 10 number"
			},

			Email: {
				required: "This item can't be empty",
				email: "The email must is abc@gmail.com"
			},

			ConfirmPassword: {
				required: "This item can't be empty",
				equalTo: "Password not same"
			}
		}
		,submitHandler: function(form){
			$("label.error").hide();
			registerMember();

		}
	});
});


//register member

function registerMember() {

	var FirstName = $("#FirstName").val();
	var LastName = $("#LastName").val();
	var Address = $("#Address").val();
	var Phone = $("#Phone").val();
	var Email = $("#Email").val();
	var Password = $("#Password").val();

	if(FirstName=="" || LastName=="" || Address=="" || Phone=="" || Email=="" || Password =="" ){
		return false;
	}

	$.ajax({
		type : "POST",
		url : "registerMember",

		data : "FirstName=" + FirstName + "&LastName=" + LastName
		+ "&Address=" + Address+
		"&Phone=" + Phone +  "&Email=" + Email
		+ "&Password=" + Password,

		success : function(data) {
			var ht = data.msg;
			if (data.result === 1) {
				swal({ title:"Register successfully!",
					type:"success",
					focusConfirm: true,
					showConfirmButton: false,
					timer: 2000
				}).
				then(function(){
					window.location.href = 'login.jsp';
				});

			}else{
				swal("Email already exists please check email");
			}

		},
	});
}

//category 

$(document).ready(function(){
	$("#add_category").validate({
		rules: {
			name: "required"
		},

		messages:{
			name: "This item can't be empty"
		}
		,submitHandler: function(form){
			$("label.error").hide();
			addCategory();

		}

	});
});

function addCategory() {

	var name = $("#name").val();

	if(name==""){
		return false;
	}

	$.ajax({
		type : "POST",
		url : "addCategory.action",

		data : "name=" + name,

		success : function(data) {
			var ht = data.msg;
			if (data.result ===1) {
				window.location.href = 'allCategory.jsp';
			}

		},
		error : function(data) {
			alert("There are some errors please check again");
		}
	});
}


function allCategory() {
	$.ajax({
		type : "GET",
		url : "allCategory.action",
		success : function(result) {

			var tblData = "";
			$.each(result.listCategory,
					function() {
				tblData += "<tr><td>"
					+ this.id
					+ "</td>"
					+"<td>"
					+this.name
					+"</td>"
					+ "<td>"
					+ "<button onclick='fetchCategory(this);' class='btn btn-sm btn-info' data-toggle='modal' data-target='#updateModal'>Update</button>"
					+ "<button onclick='deleteCategory(this);' class='btn btn-sm btn-danger'>Delete</button>"
					+ "</td></tr>";
			});
			$("#tbody").html(tblData);
		},
		error : function(result) {
			alert("There are some errors please check again");
		}
	});
}



function fetchCategory(that) {
	$("#name").val($(that).parent().prev().text());
	$("#hidden").val($(that).parent().prev().text());
}


function updateCategory() {
	$.ajax({
		type : "POST",
		url : "updateCategory.action",
		data : "name=" + $("#name").val()
		+"&hidden="+ $("#hidden").val(),
		success : function(data) {
			var ht = data.msg;
			alert(ht)
			
			if (ht ==="Successfull" ) {
				window.location.href = 'allCategory.jsp';
			}
		},
		error : function(result) {
			alert("Some error occured.");
		}
	});
}


function deleteCategory(that) {
	let result = confirm("Are you want to delete this item?");
	if (!result) {
		return;
	}

	$.ajax({
		
		
		
		type : "POST",
		url : "deleteCategory.action",
		data : "name=" + $(that).parent().prev().text(),
		
		success : function(data) {
			var ht = data.msg;
			alert(ht+ " delete ok")
			if (data.result ===1 ) {
				window.location.href = 'allCategory.jsp';
			}
		},
		error : function(data) {
			alert("Some error occured.");
		}
	});
}




//factory 

$(document).ready(function(){
	$("#add_ManuFacturer").validate({
		rules: {
			name: "required"
		},

		messages:{
			name: "This item can't be empty"
		}
		,submitHandler: function(form){
			$("label.error").hide();
			addManuFacturer();
		}

	});
});


function addManuFacturer() {
alert("123");
	var name = $("#name").val();
	$.ajax({
		type : "POST",
		url : "addManuFacturer.action",

		data : "name=" + name,

		success : function(data) {
			var ht = data.msg;
			$("#responseManuFacturer").html(ht);
		},
		error : function(data) {
			alert("There are some errors please check again");
		}
	});
}


function allManuFacturer() {
	$.ajax({
		type : "GET",
		url : "allManuFacturer.action",
		success : function(result) {

			var tblData = "";
			$.each(result.listManuFacturer,
					function() {
				tblData += "<tr><td>"
					+ this.id
					+ "</td>"
					+"<td>"
					+this.name
					+"</td>"
					+ "<td>"
					+ "<button onclick='fetchManuFacturer(this);' class='btn btn-sm btn-info' data-toggle='modal' data-target='#updateModal'>Update</button>"
					+ "<button onclick='deleteManuFacturer(this);' class='btn btn-sm btn-danger'>Delete</button>"
					+ "</td></tr>";
			});
			$("#tbody").html(tblData);
		},
		error : function(result) {
			alert("There are some errors please check again");
		}
	});
}



function fetchManuFacturer(that) {
	$("#name").val($(that).parent().prev().text());
	$("#hidden").val($(that).parent().prev().text());
}


function updateManuFacturer() {
	$.ajax({
		type : "POST",
		url : "updateManuFacturer.action",

		data : "name=" + $("#name").val()
		+"&hidden="+ $("#hidden").val(),


		success : function(result) {

			var ht = result.msg;
			$("#responseManuFacturer").html(ht);

			if (result == 'success') {
				window.location.href = 'allManuFacturer.jsp';
			}
		},
		error : function(result) {
			alert("Some error occured.");
		}
	});
	allManuFacturer();
}


function deleteManuFacturer(that) {
	let result = confirm("Are you want to delete this item?");
	if (!result) {
		return;
	}

	$.ajax({
		type : "POST",
		url : "deleteManuFacturer.action",
		data : "name=" + $(that).parent().prev().text(),
		success : function(data) {
			if (data.msg === "Delete Successful") {
				(data.msg)
				$(that).closest('tr').remove();
				if (data == 'success') {
					window.location.href = 'allManuFacturer.jsp';
				}

			} else {
				alert(data.msg)
			}
		},
		error : function(data) {
			alert("Some error occured.");
		}
	});
	allManuFacturer();

}


$(document).ready(function(){
	$("#add_product").validate({
		rules: {
			productName: "required",
			myFile:"required",
			color:"required",
			price:"required"
		},

		messages:{
			productName: "This item can't be empty",
			myFile: "This item can't be empty",
			color: "This item can't be empty",
			price:"This item can't be empty"

		}
	});
});






