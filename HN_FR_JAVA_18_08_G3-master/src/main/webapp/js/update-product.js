$(document).ready(function() {
	$('button[type="reset"]').click(function() {
		window.location.href = "allProduct";
	});

	$('input[type="file"]').change(function() {
		readURL(this);
	})
	function readURL(input) {

		  if (input.files && input.files[0]) {
		    var reader = new FileReader();

		    reader.onload = function(e) {
		      $('#img_curent').attr('src', e.target.result);

		      $('#img_curent').hide();
		      $('#img_curent').fadeIn(650);

		    }
		    reader.readAsDataURL(input.files[0]);
		  }
		}
})