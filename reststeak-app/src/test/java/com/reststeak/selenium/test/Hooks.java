package com.reststeak.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {
    public static WebDriver driver;

    @Before(order=1)
    public void setUp(){
    	ChromeOptions options = new ChromeOptions();
//		options.addArguments("--start-maximized", "--headless", "--no-sandbox");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        driver.quit();
    } 

}