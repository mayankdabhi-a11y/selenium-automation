package com.example.framework.driver;

import com.example.framework.config.Config;
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

	public static WebDriver createDriver() {
		String browser = Config.getBrowser();
		boolean headless = Config.isHeadless();

		switch (browser) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				ChromeOptions chromeOptions = new ChromeOptions();
				if (headless) {
					chromeOptions.addArguments("--headless=new", "--disable-gpu");
				}
				chromeOptions.addArguments("--window-size=1920,1080");
				return new ChromeDriver(chromeOptions);

			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				if (headless) {
					firefoxOptions.addArguments("-headless");
				}
				return new FirefoxDriver(firefoxOptions);

			case "edge":
				WebDriverManager.edgedriver().setup();
				EdgeOptions edgeOptions = new EdgeOptions();
				if (headless) {
					edgeOptions.addArguments("--headless=new");
				}
				edgeOptions.addArguments("--window-size=1920,1080");
				return new EdgeDriver(edgeOptions);

			case "safari":
				SafariOptions safariOptions = new SafariOptions();
				return new SafariDriver(safariOptions);

			default:
				throw new IllegalArgumentException("Unsupported browser: " + browser);
		}
	}
}
