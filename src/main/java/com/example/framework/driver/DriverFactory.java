package com.example.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public final class DriverFactory {

	private DriverFactory() {}

	public static WebDriver createDriver(String browser, boolean headless) {
		String choice = browser == null ? "chrome" : browser.toLowerCase();
		switch (choice) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chrome = new ChromeOptions();
				if (headless) chrome.addArguments("--headless=new", "--disable-gpu");
				chrome.addArguments("--window-size=1920,1080", "--no-sandbox", "--disable-dev-shm-usage");
				return new ChromeDriver(chrome);
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions ff = new FirefoxOptions();
				if (headless) ff.addArguments("-headless");
				return new FirefoxDriver(ff);
			case "edge":
				WebDriverManager.edgedriver().setup();
				EdgeOptions edge = new EdgeOptions();
				if (headless) edge.addArguments("--headless=new");
				edge.addArguments("--window-size=1920,1080");
				return new EdgeDriver(edge);
			case "safari":
				SafariOptions safari = new SafariOptions();
				return new SafariDriver(safari);
			default:
				throw new IllegalArgumentException("Unsupported browser: " + choice);
		}
	}
}

