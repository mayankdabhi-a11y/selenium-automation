package com.example.framework.pages.amazon;

import com.example.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AmazonHomePage extends BasePage {

	private static final By SEARCH_INPUT = By.id("twotabsearchtextbox");
	private static final By SEARCH_SUBMIT = By.id("nav-search-submit-button");
	private static final By COOKIE_ACCEPT_BTN = By.id("sp-cc-accept");

	public AmazonHomePage(WebDriver driver) {
		super(driver);
	}

	public AmazonHomePage ensureLoaded() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT));
		return this;
	}

	public AmazonHomePage dismissCookieBannerIfPresent() {
		try {
			wait.until(ExpectedConditions.or(
				ExpectedConditions.presenceOfElementLocated(COOKIE_ACCEPT_BTN),
				ExpectedConditions.visibilityOfElementLocated(SEARCH_INPUT)
			));
			if (!driver.findElements(COOKIE_ACCEPT_BTN).isEmpty()) {
				driver.findElement(COOKIE_ACCEPT_BTN).click();
			}
		} catch (Exception ignored) {
			// If consent is not shown, continue
		}
		return this;
	}

	public void searchFor(String query) {
		driver.findElement(SEARCH_INPUT).clear();
		driver.findElement(SEARCH_INPUT).sendKeys(query);
		// Prefer keyboard submit as it's more reliable across locales
		driver.findElement(SEARCH_INPUT).sendKeys(Keys.ENTER);
		// Fallback click in case ENTER didn't submit
		try {
			wait.until(ExpectedConditions.elementToBeClickable(SEARCH_SUBMIT)).click();
		} catch (Exception ignored) {
			// Ignore if already navigated
		}
	}
}

