var rootURL = "http://localhost:8080/reststeak-app/rest/orders";

/*
 * GET LIST OF ORDERS 
 */
var findAll = function() {
	console.log('findAll');
	$.ajax({
		type : 'GET',
		url : rootURL,
		dataType : "json", // data type of response
		success : renderList
	});
};

/*
 * DATA TABLE
 */
var renderList = function(orderList) {
	console.log("renderList");
	$.each(orderList,function(index, order) {
		$('#tbOrderDisplay').append(
				'<tr><td>'
				+ order.id
				+ '</td><td>'
				+ order.steak
				+ '</td><td>'
				+ order.temperature
				+ '</td><td>'
				+ order.side
				+ '</td><td>'
				+ order.sauce
				+ '</td><td>'
				+ order.orderStatus
				+ '</td><td>'
				+ '<button type="button" id="updateOrder'
				+ order.id
				+ '" class="btn btn-primary" id="'
				+ order.id
				+ '" data-toggle="modal" data-target="#updateOrderModal">Update</button>'
				+ '</td></tr>');
		$(document).on("click",'#updateOrder' + order.id, function(){
			$('.modal').on("click",'#saveOrderChanges', function(){
				updateOrder(order.id, order.steak, order.temperature, order.side, order.sauce);
	            location.reload();
	        });
		})
		$(document).on("click",'#deleteOrder' + order.id, function(){
			deleteOrder(order.id);
			location.reload();
		})
	});
	var table = $('#dtOrderList').DataTable();
};

/*
 * UPDATE ORDER
 */
var updateOrder= function (id, steak, temperature, side, sauce) {
	console.log('updateOrder');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url : rootURL + '/' + id,
		dataType: "json",
		data: formToJSONUpdate(id, steak, temperature, side, sauce),
		success: function(data, textStatus, jqXHR){
			
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Order error: ' + textStatus);
		}
	});
};

var formToJSONUpdate = function(id, steak, temperature, side, sauce) {
	return JSON.stringify({
		"id" : id,
		"steak" : steak,
		"temperature" : temperature,
		"side" : side,
		"sauce" : sauce,
		"orderStatus" : $('#updateModalStatus').val()
	});
};

$(document).ready(function() {
	findAll();
	
});


