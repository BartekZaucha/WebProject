package com.reststeak.integration.test;

import static org.junit.Assert.assertEquals;
import java.util.List;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.reststeak.reststeak_app.data.OrdersDAO;
import com.reststeak.reststeak_app.model.Orders;
import com.reststeak.reststeak_app.rest.OrdersWS;
import com.reststeak.reststeak_app.rest.JaxRsActivator;
import com.reststeak.reststeak_app.utils.OrderUtilsDAO;

@RunWith(Arquillian.class)
public class OrdersIntegrationTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class, "Test.jar")
				.addClasses(OrdersDAO.class, Orders.class,
						JaxRsActivator.class,OrdersWS.class,
						OrderUtilsDAO.class)
			//	.addPackage(EventCause.class.getPackage())
			//	.addPackage(EventCauseDAO.class.getPackage())
						//this line will pick up the production db
				.addAsManifestResource("META-INF/persistence.xml",
						"persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	@EJB
	private OrdersWS orderWS;
	@EJB
	private OrdersDAO orderDAO;
	@EJB
	private OrderUtilsDAO utilsDAO;
	
	private Orders order;
	
	@Before
	public void setUp() {
		//this function means that we start with an empty table
		//And add one wine
		//it should be possible to test with an in memory db for efficiency
		utilsDAO.deleteTable();
		order = new Orders();
		order.setId(1);
		order.setSteak("steak");
		order.setTemperature("medium");
		order.setSide("chips");
		order.setSauce("sauce");
		orderDAO.saveOrder(order);

	}
	@Test
	public void testGetAllOrders() {
		List<Orders> orderList = orderDAO.getAllOrders();
		assertEquals("Data fetch = data persisted", orderList.size(), 1);
	}
	
	@Test
	public void testSaveOrder() {
		Orders newOrder = new Orders();
		newOrder.setId(2);
		newOrder.setSteak("steak");
		newOrder.setTemperature("medium");
		newOrder.setSide("chips");
		newOrder.setSauce("sauce");
		List<Orders> orderList = orderDAO.getAllOrders();
		assertEquals("Data fetch = data persisted", orderList.size(), 2);
	}
	
	@Test
	public void testUpdateOrder() {
		assertEquals(1, order.getId());
		assertEquals("steak", order.getSteak());
		assertEquals("medium", order.getTemperature());
		assertEquals("chips", order.getSide());
		assertEquals("sauce", order.getSauce());
		order.setSteak("someOtherSteak");
		orderDAO.updateOrder(order);
		List<Orders> orderList = orderDAO.getAllOrders();
		assertEquals("Data fetch = data persisted", orderList.size(), 1);
		assertEquals("someOtherSteak", order.getSteak());
	}
	
	@Test
	public void testDeleteOrder() {
		orderDAO.deleteOrder(order.getId());
		List<Orders> orderList = orderDAO.getAllOrders();
		assertEquals("Data fetch = data persisted", orderList.size(), 0);
	}
	
}
