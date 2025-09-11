package com.example.framework.tests;

import com.example.framework.pages.amazon.AmazonHomePage;
import com.example.framework.pages.amazon.AmazonProductPage;
import com.example.framework.pages.amazon.AmazonResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonNotifyMeTest extends BaseTest {

	@Test
	public void notifyMeFlow_ShouldShowSignIn() {
		new AmazonHomePage(driver)
			.ensureLoaded()
			.dismissCookieBannerIfPresent()
			.searchFor("iPhone 17");

		new AmazonResultsPage(driver)
			.ensureLoaded()
			.openFirstResult();

		AmazonProductPage product = new AmazonProductPage(driver)
			.ensureLoaded();
		product.triggerNotifyOrBuyNow();
		Assert.assertTrue(product.isSignInShown(), "Expected sign-in page to be shown");
	}
}

