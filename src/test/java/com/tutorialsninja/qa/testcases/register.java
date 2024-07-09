package com.tutorialsninja.qa.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;

import Utils.Utilities;

public class register extends Base {

	WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		driver=initializeBrowserAndOpenApplication("firefox");
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Divyanshu");
		driver.findElement(By.id("input-lastname")).sendKeys("Barthwal");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		
	}
	@Test(priority=2)
	public void verifyRegisteringAccountByProvidingAllFields() throws InterruptedException {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Divyanshu");
		driver.findElement(By.id("input-lastname")).sendKeys("Barthwal");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(2000);
		String actualSuccessHeading=driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSuccessHeading,"Your Account Has Been Created!","Account Sucess page is not displayed");
		
		
	}
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmailAddress() throws InterruptedException {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Divyanshu");
		driver.findElement(By.id("input-lastname")).sendKeys("Barthwal");
		driver.findElement(By.id("input-email")).sendKeys("barthwaldivyanshu@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(2000);
		
		String actualWarning=driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		Assert.assertTrue(actualWarning.contains("Warning: E-Mail Address is already registered!"),"Warning message is not displayed");
		
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String actualPrivacyPolicyWarning=driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"));
	
		String actualFirstNameWarning=driver.findElement(By.xpath("//input[@name='firstname']//following-sibling::div[@class='text-danger']")).getText();
		Assert.assertTrue(actualFirstNameWarning.contains("First Name must be between 1 and 32 characters!"));
	}
	
	
}
