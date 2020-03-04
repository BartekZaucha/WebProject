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
												+ '" data-toggle="modal" data-target="#employeeUpdateModal">Update</button>'
												+ '<td></tr>');
						$(document).on("click",'#updateOrder' + order.id, function(){
							$('.modal').on("click",'#saveOrderChanges', function(){
								updateOrder(order.id);
					            location.reload();
					        });
						})
					});
	var table = $('#dtOrderList').DataTable();
};


/*
 * UPDATE ORDER
 */
var updateOrder= function (id) {
	console.log('updateOrder');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url : rootURL + '/' + id,
		dataType: "json",
		data: formToJSONUpdate(id),
		success: function(data, textStatus, jqXHR){
                    
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Order error: ' + textStatus);
		}
	});
};

var formToJSONUpdate = function(id) {
	return JSON.stringify({
		"id" : id,
//		"steak" : $('#updateModalSteak').val(),
//		"temperature" : $('#updateModalTemperature').val(),
//		"side" : $('#updateModalSide').val(),
//		"sauce" : $('#updateModalSauce').val(),
		"orderStatus" : $('#updateModalStatus').val()
	});
};

$(document).ready(function() {
	findAll();
});


