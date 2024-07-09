package com.tutorialsninja.qa.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {

	
	WebDriver driver;
	
	public WebDriver initializeBrowserAndOpenApplication(String browserName) {
		
		
		if(browserName.equals("chrome")) {
			driver=new ChromeDriver();
		}
		if(browserName.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		if(browserName.equals("edge")) {
			driver=new EdgeDriver();
		}
		
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
			driver.get("http://tutorialsninja.com/demo/");
		
			return driver;
	}
}
