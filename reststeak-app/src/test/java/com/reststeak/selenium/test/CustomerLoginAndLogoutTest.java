package com.reststeak.selenium.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomerLoginAndLogoutTest {

	public static WebDriver driver;

	@Test
	public void testCustmerLoginAndLogout() throws Exception {
		driver.get("http://localhost:8080/reststeak-app/");
		Thread.sleep(1000);
		driver.findElement(By.id("loginButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("inputUsername")).sendKeys("Customer");
		driver.findElement(By.id("inputPassword")).sendKeys("Customer1");
		driver.findElement(By.id("loginSubmit")).click();
		assertEquals("http://localhost:8080/reststeak-app/customer.html", driver.getCurrentUrl());
		Thread.sleep(1000);
		driver.findElement(By.id("logout")).click();
		assertEquals("http://localhost:8080/reststeak-app/index.html", driver.getCurrentUrl());
	}
	
	@Before
	public void beforeTest() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized", "--headless", "--no-sandbox");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
