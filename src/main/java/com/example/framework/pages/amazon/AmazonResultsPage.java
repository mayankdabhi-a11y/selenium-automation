package com.example.framework.pages.amazon;

import com.example.framework.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		// Resolve a reliable first link element
		WebElement link = null;
		try {
			// Prefer a direct <a> in the first result item if available
			link = new WebDriverWait(driver, Duration.ofSeconds(20))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@data-component-type='s-search-result']//h2//a[contains(@href,'/dp/') or contains(@href,'/gp/')])[1]")));
		} catch (TimeoutException e) {
			try {
				// Use provided container xpath, then find its h2/a
				wait.until(ExpectedConditions.presenceOfElementLocated(FIRST_RESULT_CONTAINER_XPATH));
				WebElement container = driver.findElement(FIRST_RESULT_CONTAINER_XPATH);
				link = container.findElement(By.cssSelector("h2 a"));
			} catch (Exception ignored) {
				// Fallback to CSS list of result links
				wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(RESULT_LINKS, 0));
				List<WebElement> links = driver.findElements(RESULT_LINKS);
				link = links.get(0);
			}
		}

		// Scroll into view and attempt click strategies; if all fail, navigate via href
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", link);
		String href = null;
		try {
			href = link.getAttribute("href");
		} catch (StaleElementReferenceException ignored) {}
		try {
			wait.until(ExpectedConditions.elementToBeClickable(link)).click();
		} catch (ElementClickInterceptedException | TimeoutException e1) {
			try {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
			} catch (Exception e2) {
				try {
					link.sendKeys(Keys.ENTER);
				} catch (Exception e3) {
					// Final fallback: direct navigation
					if (href != null && !href.isBlank()) {
						driver.navigate().to(href);
					}
				}
			}
		}

		// Wait for navigation to product or sign-in page
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> {
			String url = d.getCurrentUrl();
			boolean navigated = url.contains("/dp/") || url.contains("/gp/");
			boolean signInVisible = !d.findElements(By.id("signInSubmit")).isEmpty() || !d.findElements(By.id("ap_email")).isEmpty();
			return navigated || signInVisible;
		});
	}

	public void openFirstIphone17Result() {
		openFirstResult();
	}
}

