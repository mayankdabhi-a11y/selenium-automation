package com.example.framework.pages.amazon;

import com.example.framework.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class AmazonResultsPage extends BasePage {

	private static final By RESULTS_CONTAINER = By.cssSelector("div.s-main-slot");
	private static final By RESULT_LINKS = By.cssSelector("div[data-component-type='s-search-result'] h2 a");

	public AmazonResultsPage(WebDriver driver) {
		super(driver);
	}

	public AmazonResultsPage ensureLoaded() {
		waitForDocumentReady();
		wait.until(ExpectedConditions.visibilityOfElementLocated(RESULTS_CONTAINER));
		return this;
	}

	public void openFirstResult() {
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(RESULT_LINKS, 0));
		List<WebElement> links = driver.findElements(RESULT_LINKS);
		WebElement first = links.get(0);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", first);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(first)).click();
		} catch (ElementClickInterceptedException | TimeoutException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", first);
		}
	}

	public void openFirstIphone17Result() {
		openFirstResult();
	}
}

