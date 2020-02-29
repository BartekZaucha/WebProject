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

import com.reststeak.reststeak_app.data.UsersDAO;
import com.reststeak.reststeak_app.model.Users;

@Path("/users")
@Stateless
@LocalBean
public class UsersWS {

	@EJB
	private UsersDAO usersDao;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAllUsers() {
		List<Users> userList = usersDao.getAllUsers();
		return Response.status(200).entity(userList).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{username}")
	public Response getUserByUsername(@PathParam("username") String username) {
		Users user = usersDao.getUserByUserName(username);
		return Response.status(200).entity(user).build();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveUser(Users user) {
		usersDao.saveUser(user);
		return Response.status(201).entity(user).build();
	}

	@PUT
	@Path("/{username}")
	@Consumes("application/json")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response updateUser(Users user) {
		usersDao.updateUser(user);
		return Response.status(200).entity(user).build();
	}

	@DELETE
	@Path("/{username}")
	public Response deleteUser(@PathParam("username") String username) {
		usersDao.deleteUser(username);
		return Response.status(204).build();
	}

}