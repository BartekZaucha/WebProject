package com.reststeak.reststeak_app.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.reststeak.reststeak_app.data.OrdersDAO;
import com.reststeak.reststeak_app.model.Orders;

@Path("/orders")
@Stateless
@LocalBean
public class OrdersWS {

	@EJB
	private OrdersDAO ordersDao;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAllOrders() {
		List<Orders> orderList = ordersDao.getAllOrders();
		return Response.status(200).entity(orderList).build();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response getOrderById(@PathParam("id") int id) {
		Orders order = ordersDao.getOrderById(id);
		return Response.status(200).entity(order).build();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveOrder(Orders order) {
		ordersDao.saveOrder(order);
		return Response.status(201).entity(order).build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateOrder(Orders order) {
		ordersDao.updateOrder(order);
		return Response.status(200).entity(order).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deleteOrder(@PathParam("id") int id) {
		ordersDao.deleteOrder(id);
		return Response.status(204).build();
	}
}
