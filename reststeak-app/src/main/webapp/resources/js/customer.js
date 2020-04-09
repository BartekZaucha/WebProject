var rootURL = "http://localhost:8080/reststeak-app/rest/orders";
var id;

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
			document.getElementById("errorBodyTitle").innerHTML = "Error";
      		document.getElementById("errorBodyText").innerHTML = "Please select all fields";
    		$('#errorModal').modal('show');
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
		}
	});
};

$(document).on("click", '#deleteOrder', function() {
	deleteOrder(id);
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
				+ '" class="btn btn-primary" id=""'
				+ order.id
				+ '>UPDATE</button>'
				+ '</td><td>'
				+ '<button type="button" id="deleteOrder'
				+ order.id
				+ '" class="btn btn-danger" id=""'
				+ order.id
				+ '>DELETE</button>'
				+ '</td><td>'
				+ '<button type="button" id="payForOrder'
				+ order.id
				+ '" class="btn btn-success" id=""'
				+ order.id
				+ '>PAY</button>'
				+ '</td></tr>');
		$(document).on("click",'#updateOrder' + order.id, function(){
			if(order.orderStatus == "In Progress"){
				document.getElementById("errorBodyTitle").innerHTML = "Error";
          		document.getElementById("errorBodyText").innerHTML = "Your order is in progress so it cannot be changed";
        		$('#errorModal').modal('show');
			}else if(order.orderStatus == "Done"){
				document.getElementById("errorBodyTitle").innerHTML = "Error";
          		document.getElementById("errorBodyText").innerHTML = "Your order is ready so it cannot be changed";
        		$('#errorModal').modal('show');
			}else{
				$('#updateOrderModal').modal('toggle');
				$('.modal').on("click",'#saveOrderChanges', function(){
					updateOrder(order.id);
			        location.reload();
			    });
			}
		})
		$(document).on("click",'#deleteOrder' + order.id, function(){
			if(order.orderStatus == "In Progress"){
				document.getElementById("errorBodyTitle").innerHTML = "Error";
          		document.getElementById("errorBodyText").innerHTML = "Your order is in progress so it cannot be deleted";
        		$('#errorModal').modal('show');
			}else if(order.orderStatus == "Done"){
				document.getElementById("errorBodyTitle").innerHTML = "Error";
          		document.getElementById("errorBodyText").innerHTML = "Your order is ready so it cannot be deleted";
        		$('#errorModal').modal('show');
			}else{
				document.getElementById("warrningBodyTitle").innerHTML = "Warrning";
          		document.getElementById("warrningBodyText").innerHTML = "You are about to delete your order";
        		$('#warrningModal').modal('show');
        		$('#deleteButton').click(function() {
        			deleteOrder(order.id);
        			location.reload();
        		});
			}
		})
		$(document).on("click",'#payForOrder' + order.id, function(){
			if(order.orderStatus == "Processing" || order.orderStatus == "In Progress"){
				document.getElementById("errorBodyTitle").innerHTML = "Error";
          		document.getElementById("errorBodyText").innerHTML = "Your order is not ready! Please wait.";
        		$('#errorModal').modal('show');
			}else{
        		$('#payModal').modal('show');
        		$('#finishOrderButton').click(function() {
        			if($('#payName').val() == "" || $('#payCard').val() == "" || $('#payCvc').val() == ""){
        				
        			}else{
        				deleteOrder(order.id);
        				location.reload();
        			}
        		});
			}
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
		}
	});
};

var formToJSONUpdate = function(id) {
	return JSON.stringify({
		"id" : id,
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
	$("#menuPanel").hide();
	$("#myOrderPanel").show();
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