package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

import Utils.Utilities;

public class Login extends Base {

	WebDriver driver;

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setup() {
		initializeBrowserAndOpenApplication("chrome");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();

	}

	@Test(priority = 1)
	public void verifyLoginWithValidCredentials() {

		driver.findElement(By.id("input-email")).sendKeys("barthwaldivyanshu@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("password");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException {
		driver.findElement(By.id("input-email"))
				.sendKeys("dwqdwqnshu" + Utilities.generateEmailWithTimeStamp() + "@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("pwdqeqd");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		Thread.sleep(2000);
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarning.contains(expectedWarning), "Expected warning message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("password");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarning.contains(expectedWarning), "Expected warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		driver.findElement(By.id("input-email")).sendKeys("barthwaldivyanshu@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("dqwdfq");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarning.contains(expectedWarning), "Expected warning message is not displayed");

	}

	@Test(priority = 5)
	public void verifyLoginWithoutEmailAndInvalidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).click();

		String actualWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarning.contains(expectedWarning), "Expected warning message is not displayed");

	}

}
