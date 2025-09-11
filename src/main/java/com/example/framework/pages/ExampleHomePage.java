package com.example.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExampleHomePage extends BasePage {

	private static final By HEADING = By.tagName("h1");
	private static final By MORE_INFO_LINK = By.cssSelector("a[href*='iana.org']");

	public ExampleHomePage(WebDriver driver) {
		super(driver);
	}

	public ExampleHomePage ensureLoaded() {
		wait.until(ExpectedConditions.titleContains("Example"));
		wait.until(ExpectedConditions.visibilityOfElementLocated(HEADING));
		wait.until(ExpectedConditions.presenceOfElementLocated(MORE_INFO_LINK));
		return this;
	}

	public String getHeadingText() {
		return driver.findElement(HEADING).getText();
	}

	public String getMoreInformationLinkText() {
		return driver.findElement(MORE_INFO_LINK).getText();
	}

	public String getMoreInformationLinkHref() {
		return driver.findElement(MORE_INFO_LINK).getAttribute("href");
	}
}

