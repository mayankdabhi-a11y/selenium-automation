package com.example.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public abstract class BasePage {
	protected final WebDriver driver;
	protected final WebDriverWait wait;

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public void waitForDocumentReady() {
		ExpectedCondition<Boolean> pageLoadCondition = d ->
			((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete");
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(pageLoadCondition);
	}
}
