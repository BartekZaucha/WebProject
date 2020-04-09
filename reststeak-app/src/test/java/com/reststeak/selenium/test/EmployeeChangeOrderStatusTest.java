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

public class EmployeeChangeOrderStatusTest {

	public static WebDriver driver;
	
	@Test
	public void testEmployeeChangeOrderStatus() throws Exception {
		driver.get("http://localhost:8080/reststeak-app/");
		Thread.sleep(1000);
		driver.findElement(By.id("loginButton")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("inputUsername")).sendKeys("Employee");
		driver.findElement(By.id("inputPassword")).sendKeys("Employee1");
		driver.findElement(By.id("loginSubmit")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/header/div/div/div[2]/table/tbody/tr/td[7]/button")).click();
		Thread.sleep(1000);
		Select newStatus = new Select(driver.findElement(By.id("updateModalStatus")));
		newStatus.selectByIndex(3);
		driver.findElement(By.id("saveOrderChanges")).click();
		Thread.sleep(1000);
		String verifyStatus = driver.findElement(By.xpath("/html/body/header/div/div/div[2]/table/tbody/tr/td[6]"))
				.getText();
		assertEquals("Done", verifyStatus);
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
