package com.example.framework.pages.amazon;

import com.example.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AmazonProductPage extends BasePage {

	private static final By SIGN_IN_SUBMIT = By.id("signInSubmit");
	private static final By SIGN_IN_EMAIL = By.id("ap_email");
	private static final By SIGN_IN_HEADER = By.xpath("//h1[contains(normalize-space(.), 'Sign in') or contains(normalize-space(.), 'Sign-In')]");

	public AmazonProductPage(WebDriver driver) {
		super(driver);
	}

	public AmazonProductPage ensureLoaded() {
		// Wait for the product page or potential sign-in to render something meaningful
		new WebDriverWait(driver, Duration.ofSeconds(15))
			.until(ExpectedConditions.or(
				ExpectedConditions.presenceOfElementLocated(By.tagName("body")),
				ExpectedConditions.presenceOfElementLocated(SIGN_IN_HEADER)
			));
		return this;
	}

	public boolean waitForSignInPresence(Duration timeout) {
		try {
			new WebDriverWait(driver, timeout)
				.until(ExpectedConditions.or(
					ExpectedConditions.visibilityOfElementLocated(SIGN_IN_SUBMIT),
					ExpectedConditions.visibilityOfElementLocated(SIGN_IN_EMAIL),
					ExpectedConditions.visibilityOfElementLocated(SIGN_IN_HEADER)
				));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isSignInButtonPresent() {
		return !driver.findElements(SIGN_IN_SUBMIT).isEmpty();
	}
}

