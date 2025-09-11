package com.example.framework.pages.amazon;

import com.example.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AmazonProductPage extends BasePage {

	private static final By NOTIFY_ME_BUTTON = By.xpath("//input[@id='buy-now-button' or contains(@aria-labelledby,'availability') or contains(@value,'Notify')]");
	private static final By SIGN_IN_HEADER = By.cssSelector("h1.a-spacing-small");

	public AmazonProductPage(WebDriver driver) {
		super(driver);
	}

	public AmazonProductPage ensureLoaded() {
		wait.until(ExpectedConditions.or(
			ExpectedConditions.presenceOfElementLocated(NOTIFY_ME_BUTTON),
			ExpectedConditions.presenceOfElementLocated(SIGN_IN_HEADER)
		));
		return this;
	}

	public void triggerNotifyOrBuyNow() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(NOTIFY_ME_BUTTON)).click();
		} catch (Exception ignored) {
			// If not found, page may require sign-in directly
		}
	}

	public boolean isSignInShown() {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(SIGN_IN_HEADER));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

