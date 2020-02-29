var rootURL = "http://localhost:8080/reststeak-app/rest/orders";
var id;
/*var steak;
var temperature;
var side;
var sauce;*/

/*
 * SAVE ORDER 
 */
var saveOrder = function() {
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : rootURL,
		dataType : "json",
		data : formToJSON(),
		success : function(data, textStatus, jqXHR) {
			id = data.id;
			$('#steakId').html(data.id);
			$('#steakOrdered').html(data.steak);
			$('#steakTemperature').html(data.temperature);
			$('#steakSide').html(data.side);
			$('#steakSauce').html(data.sauce);
			$('#orderModal').modal('toggle');
		},
		error : function(data, textStatus, jqXHR) {
			alert("Error: " + errorThrown);
		}
	});
};

$(document).on("click", '#makeOrder', function() {
	saveOrder();
});

var formToJSON = function() {
	return JSON.stringify({
		"steak" : $('#steak').val(),
		"temperature" : $('#temperature').val(),
		"side" : $('#side').val(),
		"sauce" : $('#sauce').val(),
		"orderStatus" : "Processing"
	});
};

/*
 * DELETE ORDER 
 */
var deleteOrder = function(id) {
	console.log('deleteOrder');
	$.ajax({
		type : 'DELETE',
		url : rootURL + '/' + id,
		success : function(data, textStatus, jqXHR) {

		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('error: ' + errorThrown);
		}
	});
};

$(document).on("click", '#deleteOrder', function() {
	deleteOrder(id);
	location.reload();
});

/*
 * GET LIST OF ORDERS 
 */
var findAll = function() {
	console.log('findAll');
	$.ajax({
		type : 'GET',
		url : rootURL,
		dataType : "json",
		success : renderList
	});
};


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
				+ '<td></tr>');
		$(document).on("click",'#updateOrderModal', function(){
			
	                  $('.modal').on("click",'#saveOrderChanges', function(){
	                	  updateOrder(id);
	                	  location.reload();

	                  });
		})
	});
	var table = $('#dtOrderList').DataTable();
};


/*
 * GET ORDER
 */
var findOrderById = function() {
	console.log('findOrderById');
	$.ajax({
		type : 'GET',
		url : rootURL + + $('#test').val(),
		dataType : "json",
		success : function(data) {
			$('#updateSteakOrdered').html(data.steak);
			$('#updateSteakTemperature').html(data.temperature);
			$('#updateSteakSide').html(data.side);
			$('#updateSteakSauce').html(data.sauce);
		},
		error : function(data, textStatus, jqXHR) {
			alert("Error: " + errorThrown);
		}
	});
};

/*
 * UPDATE ORDER
 */

var updateOrder= function () {
	console.log('updateOrder');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url : rootURL + '/' + $('#test').val(),
		dataType: "json",
		data: formToJSONUpdate(),
		success: function(data, textStatus, jqXHR){
			alert('Order updated successfully');
                    
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Order error: ' + textStatus);
		}
	});
};

var formToJSONUpdate = function() {
	return JSON.stringify({
		"steak" : $('#updateModalSteak').val(),
		"temperature" : $('#updateModalTemperature').val(),
		"side" : $('#updateModalSide').val(),
		"sauce" : $('#updateModalSauce').val(),
		"orderStatus" : "Processing"
	});
};
/*
 * HIDE/SHOW DIVs
 */
$(document).ready(function() {
	findAll();
	$("#menuPanel").show();
	$("#myOrderPanel").hide();
	$('#menu').click(function() {
		$("#menuPanel").show();
		$("#myOrderPanel").hide();
	});
	$('#myOrder').click(function() {
		$("#myOrderPanel").show();
		$("#menuPanel").hide();
	});
	$("#confirmOrder").click(function() {
		location.reload();
	})
});