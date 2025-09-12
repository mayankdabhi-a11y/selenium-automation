package com.example.framework.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SampleTest extends BaseTest {

	@Test
	public void openAndCloseChrome() {
		// Open Amazon India homepage
		driver.get("https://www.amazon.in");

		// Search for iPhone 17
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By searchBox = By.id("twotabsearchtextbox");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys("iPhone 17", Keys.ENTER);

		// Dismiss cookie banner if it appears
		try {
			By acceptCookies = By.id("sp-cc-accept");
			wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
		} catch (Exception ignored) {}

		// Click first search result link via provided XPath; fallback to CSS if needed
		By firstHeaderXPath = By.xpath("/html/body/div[1]/div[30]/div[10]/div[1]/div.sg-col-inner[1]/span[2]/div[1]/div[9]/div.sg-col-inner[1]/div[1]/span.a-declarative[1]/div[1]/div.a-section[1]/div.puisg-row[1]/div[2]/div.puisg-col-inner[1]/div[1]/div[1]/a[1]/h2[1]");
		try {
			WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(firstHeaderXPath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", header);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(header)).click();
			} catch (Exception clickHeaderEx) {
				try {
					WebElement anchor = header.findElement(By.xpath("ancestor::a[1]"));
					wait.until(ExpectedConditions.elementToBeClickable(anchor)).click();
				} catch (Exception clickAnchorEx) {
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", header);
				}
			}
		} catch (org.openqa.selenium.InvalidSelectorException | org.openqa.selenium.TimeoutException e) {
			By resultLinks = By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a");
			wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultLinks, 0));
			List<WebElement> links = driver.findElements(resultLinks);
			WebElement first = links.get(0);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", first);
			try {
				wait.until(ExpectedConditions.elementToBeClickable(first)).click();
			} catch (Exception e2) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", first);
			}
		}
	}
}

