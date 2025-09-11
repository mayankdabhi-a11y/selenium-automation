package com.example.framework.pages.amazon;

import com.example.framework.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class AmazonResultsPage extends BasePage {

	private static final By RESULTS_CONTAINER = By.cssSelector("div.s-main-slot");
	private static final By RESULT_LINKS = By.cssSelector("div[data-component-type='s-search-result'] h2 a");

	public AmazonResultsPage(WebDriver driver) {
		super(driver);
	}

	public AmazonResultsPage ensureLoaded() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(RESULTS_CONTAINER));
		return this;
	}

	public void openFirstResult() {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(RESULT_LINKS, 0));
		WebElement first = driver.findElements(RESULT_LINKS).get(0);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", first);
		wait.until(ExpectedConditions.elementToBeClickable(first)).click();
	}
}

