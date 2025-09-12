package com.example.framework.tests;

import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

	@Test
	public void openAndCloseChrome() {
		// Driver is created in BaseTest.setUp(); open Amazon homepage
		driver.get("https://www.amazon.com");
	}
}

