describe('Flipkart Login - Request OTP', () => {
	it('opens site, verifies login, enters mobile, requests OTP and sees validation', () => {
		cy.visit('/');
		cy.dismissFlipkartModal();

		// Login entry (header link or direct login page)
		cy.xpath("//a[contains(@href,'/account/login')] | //span[normalize-space()='Login' or normalize-space()='Log in']/ancestor::a[1] | //div[normalize-space()='Login']/ancestor::a[1] | //span[normalize-space()='Login' or normalize-space()='Log in']")
			.should('be.visible')
			.click({ force: true });

		// Ensure on login page
		cy.url({ timeout: 20000 }).should('include', '/account/login');

		// Enter mobile number in the login field
		cy.xpath("//input[contains(@class, 'r4vIwl') and contains(@class, 'BV+Dqf')]")
			.should('be.visible')
			.clear()
			.type('99987654323', { delay: 0 });

		// Click Request OTP / Continue
		cy.xpath("//button[.//span[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'request otp')] or contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'request otp') or contains(.,'CONTINUE') or contains(.,'Continue')]")
			.click({ force: true });

		// Validate error for invalid mobile format (as per Selenium test)
		cy.xpath("//*[contains(normalize-space(.), 'Please enter valid Email ID/Mobile number')]")
			.should('be.visible');
	});
});

