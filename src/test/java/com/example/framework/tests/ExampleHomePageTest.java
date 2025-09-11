package com.example.framework.tests;

import com.example.framework.pages.ExampleHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExampleHomePageTest extends BaseTest {

	@Test
	public void titleAndHeadingAndLinkShouldBeValid() {
		ExampleHomePage home = new ExampleHomePage(driver).ensureLoaded();
		String title = driver.getTitle();
		Assert.assertTrue(title.toLowerCase().contains("example"), "Title should contain 'example'");
		Assert.assertEquals(home.getHeadingText(), "Example Domain", "Heading text mismatch");
		Assert.assertEquals(home.getMoreInformationLinkText().trim(), "More information...", "Link text mismatch");
		Assert.assertTrue(home.getMoreInformationLinkHref().contains("iana.org"), "Link href should contain iana.org");
	}
}

