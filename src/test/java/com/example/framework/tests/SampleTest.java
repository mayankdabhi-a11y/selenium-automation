package com.example.framework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {

	@Test
	public void titleShouldNotBeEmpty() {
		String title = driver.getTitle();
		Assert.assertNotNull(title, "Title is null");
		Assert.assertFalse(title.isBlank(), "Title is blank");
	}
}
