package com.reststeak.selenium.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomerCrudTest {

	public static WebDriver driver;
	
	@Test
	public void customerCrudTest() throws Exception{
		driver.get("http://localhost:8080/reststeak-app/");
		Thread.sleep(1000);
		driver.findElement(By.id("loginButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("inputUsername")).sendKeys("Customer");
		driver.findElement(By.id("inputPassword")).sendKeys("Customer1");
		driver.findElement(By.id("loginSubmit")).click();
		Thread.sleep(1000);
		//Make Order
		driver.findElement(By.id("menu")).click();
		Thread.sleep(1000);
		Select steak = new Select(driver.findElement(By.id("steak")));
		steak.selectByIndex(1);
		Select temp = new Select(driver.findElement(By.id("temperature")));
		temp.selectByIndex(1);
		Select side = new Select(driver.findElement(By.id("side")));
		side.selectByIndex(1);
		Select sauce = new Select(driver.findElement(By.id("sauce")));
		sauce.selectByIndex(1);
		driver.findElement(By.id("makeOrder")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("confirmOrder")).click();
		Thread.sleep(1000);
		String newlyCreatedSteak = driver
				.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/table/tbody/tr[2]/td[2]")).getText();
		String newlyCreatedTemp = driver
				.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/table/tbody/tr[2]/td[3]")).getText();
		String newlyCreatedSide = driver
				.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/table/tbody/tr[2]/td[4]")).getText();
		String newlyCreatedSauce = driver
				.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/table/tbody/tr[2]/td[5]")).getText();
		assertEquals("10oz Rib Eye - €19.99", newlyCreatedSteak);
		assertEquals("Rare", newlyCreatedTemp);
		assertEquals("Sweet Potato Fries - €4.99", newlyCreatedSide);
		assertEquals("Peppercorn", newlyCreatedSauce);
		//Update Order
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/table/tbody/tr[2]/td[7]/button")).click();
		Thread.sleep(1000);
		Select updateSteak = new Select(driver.findElement(By.id("updateModalSteak")));
		updateSteak.selectByIndex(3);
		Select updateTemp = new Select(driver.findElement(By.id("updateModalTemperature")));
		updateTemp.selectByIndex(2);
		Select updateSide = new Select(driver.findElement(By.id("updateModalSide")));
		updateSide.selectByIndex(1);
		Select updateSauce = new Select(driver.findElement(By.id("updateModalSauce")));
		updateSauce.selectByIndex(3);
		driver.findElement(By.id("saveOrderChanges")).click();
		Thread.sleep(1000);
		String firstOrderSteak = driver
				.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/table/tbody/tr[2]/td[2]")).getText();
		String firstOrderTemp = driver
				.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/table/tbody/tr[2]/td[3]")).getText();
		String firstOrderSide = driver
				.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/table/tbody/tr[2]/td[4]")).getText();
		String firstOrderSauce = driver
				.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/table/tbody/tr[2]/td[5]")).getText();
		assertEquals("8oz Sirloin - €19.99", firstOrderSteak);
		assertEquals("Medium Rare", firstOrderTemp);
		assertEquals("Sweet Potato Fries - €4.99", firstOrderSide);
		assertEquals("Chimichurri", firstOrderSauce);
		//Delete Order
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/table/tbody/tr[2]/td[8]/button")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("deleteButton")).click();
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
