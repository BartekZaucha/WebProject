// JavaScript Document
var rootURL = "http://localhost:8080/reststeak-app/rest/orders";

// data table for order
var findAll = function() {
	console.log('findAll');
	$.ajax({
		type : 'GET',
		url : rootURL,
		dataType : "json", // data type of response
		success : renderList
	});
};

var renderList = function(orderList) {
	console.log("renderList");
	$
			.each(
					orderList,
					function(index, order) {
						$('#tbOrderDisplay')
								.append(
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
												+ '" data-toggle="modal" data-target="#userUpdateModal">Update</button>'
												+ '<td></tr>');
					});
	var table = $('#dtOrderList').DataTable();
};

$(document).ready(function() {
	findAll();
});