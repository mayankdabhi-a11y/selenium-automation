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

		// Verify Login button visible in header and click it (prefer direct link to /account/login)
		By loginHeader = By.xpath("//a[contains(@href,'/account/login')] | //span[normalize-space()='Login' or normalize-space()='Log in']/ancestor::a[1] | //div[normalize-space()='Login']/ancestor::a[1] | //span[normalize-space()='Login' or normalize-space()='Log in']");
		WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
		Assert.assertTrue(loginBtn.isDisplayed(), "Expected Login button to be visible");
		try {
			wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
		} catch (Exception e) {
			// Fallback click
			loginBtn.click();
		}

		// Verify login form appears (or navigate directly if header did not open it)
		By mobileInput = By.xpath("//input[contains(@class, 'r4vIwl') and contains(@class, 'BV+Dqf')]");
		boolean onLogin = false;
		try {
			wait.until(ExpectedConditions.or(
				ExpectedConditions.visibilityOfElementLocated(mobileInput),
				ExpectedConditions.urlContains("/account/login")
			));
			onLogin = true;
		} catch (Exception ignored) {}
		if (!onLogin) {
			driver.get("https://www.flipkart.com/account/login");
		}
		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileInput));
		Assert.assertTrue(input.isDisplayed(), "Expected mobile input to be visible");

		// Enter mobile number and request OTP
		input.clear();
		String phone = "99987654323";
		input.sendKeys(phone);
		By requestOtp = By.xpath("//button[.//span[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'request otp')] or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'request otp') or contains(.,'CONTINUE') or contains(.,'Continue')]");
		wait.until(ExpectedConditions.elementToBeClickable(requestOtp)).click();

		// Verify validation message for invalid email/mobile appears
		By validationMsg = By.xpath("//*[contains(normalize-space(.), 'Please enter valid Email ID/Mobile number')]");
		WebElement validation = new WebDriverWait(driver, Duration.ofSeconds(15))
			.until(ExpectedConditions.visibilityOfElementLocated(validationMsg));
		Assert.assertTrue(validation.isDisplayed(), "Expected validation message to be visible");
		Assert.assertTrue(validation.getText().contains("Please enter valid Email ID/Mobile number"),
			"Validation message text mismatch");
	}
}

