$(document).ready(function(){
	var sum = 0;
	$(".total").each(function(){
		sum += parseInt(convertTypeMoneyLineItem($(this).text().toString()));
	});
	$("div > .total-price").text("$"+convertFormatNumber(sum.toString()));
	$("#cart > tbody > tr").on("click",".input-number-product", function(e){
		var sumTotal = 0;
		var row = $(this).closest('tr');
		var total_line_item = row.find(".price-product").text() * row.find(".input-number-product").val();
		var total = (row.find("td:eq(4)"));
		total.text(total_line_item); 
		$(".total").each(function(){
			sumTotal += parseInt($(this).text());
		});
		$("div > .total-price").text("$"+convertFormatNumber(sumTotal.toString()));
		$.ajax({
			type : 'POST',
			url : 'editNumberProduct',
			data : {
				'productId' : row.find('td.NoneDisplay').text(),
				'number' : row.find(".input-number-product").val()
			},
			dataType : 'json',
			success : function(data) {
				$("#cartNumber").html(data.refreshNumberCart);
			}
		});
		
	})
	
	$("#cart > tbody > tr").on("click",".btn-remove-item",function(){
		var row = $(this).closest('tr');
		var total = row.find("td:eq(4)").text();
		var currentValue = parseInt(convertTypeMoney($("div > .total-price").text())) - parseInt(convertTypeMoneyLineItem(total));
		$("div > .total-price").text("$"+convertFormatNumber(currentValue.toString()));
		$.ajax({
			type : 'POST',
			url : 'removeProduct',
			data : {
				'productId' : row.find('td.NoneDisplay').text(),
			},
			dataType : 'json',
			success : function(data) {
				$("#cartNumber").html(data.refreshNumberCart);
			}
		});
		row.remove();
	})
	
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
		console.log(input);
		input = input.substr(1);
		console.log(input);
		for(var i = 0 ; i < input.length ; i++){
			if(input[i] !== '.'){
				result += input[i];
			}
		}
		return result;
	}
	function convertTypeMoneyLineItem(input){
		var result ="";
		input = input.toString();
		console.log(input);
		for(var i = 0 ; i < input.length ; i++){
			if(input[i] !== '.'){
				result += input[i];
			}
		}
		return result;
	}
})