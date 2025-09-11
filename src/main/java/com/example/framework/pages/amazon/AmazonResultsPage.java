package com.example.framework.pages.amazon;

import com.example.framework.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class AmazonResultsPage extends BasePage {

	private static final By RESULTS_CONTAINER = By.cssSelector("div.s-main-slot");
	private static final By RESULT_LINKS = By.cssSelector("div[data-component-type='s-search-result'] h2 a");
	private static final By FIRST_RESULT_CONTAINER_XPATH = By.xpath("//body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]/div[1]/div[2]");

	public AmazonResultsPage(WebDriver driver) {
		super(driver);
	}

	public AmazonResultsPage ensureLoaded() {
		waitForDocumentReady();
		wait.until(ExpectedConditions.or(
			ExpectedConditions.visibilityOfElementLocated(RESULTS_CONTAINER),
			ExpectedConditions.presenceOfElementLocated(FIRST_RESULT_CONTAINER_XPATH)
		));
		return this;
	}

	public void openFirstResult() {
		// Try user-provided XPath container first
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_RESULT_CONTAINER_XPATH));
			WebElement container = driver.findElement(FIRST_RESULT_CONTAINER_XPATH);
			WebElement link;
			try {
				link = container.findElement(By.cssSelector("h2 a"));
			} catch (NoSuchElementException ignore) {
				link = container;
			}
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(link)).click();
			} catch (ElementClickInterceptedException | TimeoutException e) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
			}
			return;
		} catch (TimeoutException ignored) {
			// Fallback to CSS selector approach
		}

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

