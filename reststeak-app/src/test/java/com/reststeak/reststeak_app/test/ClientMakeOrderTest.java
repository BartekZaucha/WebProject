package com.reststeak.reststeak_app.test;

import static org.junit.Assert.*;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.reststeak.reststeak_app.model.Orders;
import com.reststeak.reststeak_app.data.OrdersDAO;
import com.reststeak.reststeak_app.rest.OrdersWS;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;

import com.reststeak.reststeak_app.model.Orders;
import com.reststeak.reststeak_app.data.OrdersDAO;
import com.reststeak.reststeak_app.rest.OrdersWS;

@RunWith(CukeSpace.class)
@Features({ "src/test/resources/projectFeatures/order.feature" })
public class ClientMakeOrderTest {

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
	Orders order ;

	@Given("^the user is logged in$")
	public void the_user_is_logged_in() throws Throwable {
		
	}
	
	@When("^the customer creates his order \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void the_customer_creates_his_order(String arg1, String arg2, String arg3, String arg4) throws Throwable {
		order = new Orders();
	    order.setSteak(arg1);
	    order.setTemperature(arg2);
	    order.setSide(arg3);
	    order.setSauce(arg4);
	    order.setOrderStatus("Processing");
	    response = ordersWS.saveOrder(order);
	}

	@When("^they click Make Order button$")
	public void they_click_Make_Order_button() throws Throwable {
	
	}

	@Then("^a confirmation modal will appear$")
	public void a_confirmation_modal_will_appear() throws Throwable {

	}

	@Then("^when the customer clicks Confirm the order will be made$")
	public void when_the_customer_clicks_Confirm_the_order_will_be_made() throws Throwable {
		assertEquals(201, response.getStatus());
		assertNotNull(response.getEntity());
	}
}
