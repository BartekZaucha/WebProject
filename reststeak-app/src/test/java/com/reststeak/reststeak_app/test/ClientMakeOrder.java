package com.reststeak.reststeak_app.test;

import static org.junit.Assert.*;

import java.util.List;

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

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;

@RunWith(CukeSpace.class)
@Features({ "src/test/resources/projectFeatures/order.feature" })
public class ClientMakeOrder {

	
}
