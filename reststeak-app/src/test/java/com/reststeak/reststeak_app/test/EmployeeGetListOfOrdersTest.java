package com.reststeak.reststeak_app.test;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import com.reststeak.reststeak_app.model.Orders;
import com.reststeak.reststeak_app.data.OrdersDAO;
import com.reststeak.reststeak_app.rest.OrdersWS;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;

@RunWith(CukeSpace.class)
@Features({ "src/test/resources/projectFeatures/employeeGetListOfOrders.feature" })
public class EmployeeGetListOfOrdersTest {

	@Deployment
	public static WebArchive createArchiveAndDeploy() {
		return ShrinkWrap.create(WebArchive.class, "Test.war").addClasses(Orders.class, OrdersDAO.class, OrdersWS.class)
				.addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	OrdersDAO ordersDAO;

	@EJB
	OrdersWS ordersWS;

	Response response;
	Orders order;
	
	@Given("^the employee is logged in$")
	public void the_employee_is_logged_in() throws Throwable {
	}

	@When("^the customer created their order \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void the_customer_created_their_order(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		order = new Orders();
	    order.setSteak(arg1);
	    order.setTemperature(arg2);
	    order.setSide(arg3);
	    order.setSauce(arg4);
	    order.setOrderStatus("Processing");
	}

	@Then("^the employee can get a list of containing that order$")
	public void the_employee_can_get_a_list_of_containing_that_order() throws Throwable {
		response = ordersWS.findAllOrders();
		assertEquals(200, response.getStatus());
	}

}
