package com.example.framework.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class FlipkartLoginTest extends BaseTest {

	@Test
	public void userCanRequestOtpLogin() {
		driver.get("https://www.flipkart.com");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Ensure potential modal is closed to reveal header Login
		try {
			driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
		} catch (Exception ignored) {}

		// Verify Login button visible in header
		By loginHeader = By.xpath("//span[normalize-space()='Login' or normalize-space()='Log in']");
		WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
		Assert.assertTrue(loginBtn.isDisplayed(), "Expected Login button to be visible");

		// Click Login
		loginBtn.click();

		// Verify login form appears
		By mobileInput = By.xpath("//input[@type='text' or @type='tel' or @type='number'][@maxlength and @maxlength>='10' or contains(translate(@placeholder,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'mobile')]");
		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileInput));
		Assert.assertTrue(input.isDisplayed(), "Expected mobile input to be visible");

		// Enter mobile number and request OTP
		input.clear();
		input.sendKeys("9998217768");
		By requestOtp = By.xpath("//span[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'request otp')]/ancestor::button | //button[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'request otp')] | //button[contains(.,'CONTINUE') or contains(.,'Continue')]");
		wait.until(ExpectedConditions.elementToBeClickable(requestOtp)).click();
	}
}

