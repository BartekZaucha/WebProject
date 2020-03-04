package com.reststeak.selenium.test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

public class MakingOrderSelenium {
	WebDriver driver = Hooks.driver;
	
	@Given("^the customer is logged in with valid credentials \"([^\"]*)\" \"([^\"]*)\"$")
	public void the_customer_is_logged_in_with_valid_credentials(String arg1, String arg2) throws Throwable {
		driver.get("http://localhost:8080/reststeak-app/");
		driver.findElement(By.id("loginButton")).click();
		driver.findElement(By.id("inputUsername")).sendKeys(arg1);
		driver.findElement(By.id("inputPassword")).sendKeys(arg2);
		driver.findElement(By.id("loginSubmit")).click();
	}

	@When("^the customer selects a steak$")
	public void the_customer_selects_a_steak() throws Throwable {
	   driver.findElement(By.id("steak")).click();
	   Select steak = new Select(driver.findElement(By.id("steak")));
	   steak.selectByVisibleText("10oz Rib Eye - €19.99");
	}

	@And("^steak temperature$")
	public void steak_temperature() throws Throwable {
		driver.findElement(By.id("temperature")).click();
		Select temperature = new Select(driver.findElement(By.id("temperature")));
		temperature.selectByVisibleText("Rare");
	}

	@And("^side$")
	public void side() throws Throwable {
		driver.findElement(By.id("side")).click();
		Select side = new Select(driver.findElement(By.id("side")));
		side.selectByVisibleText("Sweet Potato Fries - €4.99");
	}

	@And("^sauce$")
	public void sauce() throws Throwable {
		driver.findElement(By.id("sauce")).click();
		Select sauce = new Select(driver.findElement(By.id("sauce")));
		sauce.selectByVisibleText("Peppercorn");
	}

	@And("^click \"([^\"]*)\" button$")
	public void click_button(String arg1) throws Throwable {
		driver.findElement(By.id("makeOrder")).click();
	}

	@Then("^a modal will appear for them to confim the order$")
	public void a_modal_will_appear_for_them_to_confim_the_order() throws Throwable {
	    
	}
}
