package com.example.framework.tests;

import com.example.framework.pages.amazon.AmazonHomePage;
import com.example.framework.pages.amazon.AmazonResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonSearchVisibilityTest extends BaseTest {

	@Test
	public void firstResultForIphone17_ShouldBeVisible() {
		new AmazonHomePage(driver)
			.ensureLoaded()
			.dismissCookieBannerIfPresent()
			.searchFor("iPhone 17");

		boolean visible = new AmazonResultsPage(driver)
			.ensureLoaded()
			.isFirstResultVisible();

		Assert.assertTrue(visible, "Expected first result to be visible");
	}
}

