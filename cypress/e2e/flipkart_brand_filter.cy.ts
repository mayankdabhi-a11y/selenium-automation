describe('Flipkart Brand Filter - iPhone 17', () => {
	it('searches, applies APPLE brand, opens first result and checks title', () => {
		cy.visit('/');
		cy.dismissFlipkartModal();

		// Search for iPhone 17
		cy.get('input[name="q"]').should('be.visible').type('iPhone 17{enter}');

		// Try to expand Brand and click APPLE if present
		cy.xpath("//div[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'brand')]").then(($headers) => {
			if ($headers.length) cy.wrap($headers[0]).click({ force: true });
		});
		cy.xpath("//label[contains(.,'APPLE') or contains(.,'Apple')]/preceding::input[@type='checkbox'][1] | //div[label[contains(.,'APPLE') or contains(.,'Apple')]]//input[@type='checkbox']")
			.then(($cb) => { if ($cb.length) cy.wrap($cb[0]).check({ force: true }); });

		// Ensure at least one iPhone result, click first
		cy.xpath("//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'iphone')]")
			.should('have.length.greaterThan', 0)
			.first()
			.scrollIntoView()
			.then(($a) => {
				const before = new Set(Cypress.dom.getWindowByElement($a[0]).open ? [] : []);
				cy.wrap($a).click({ force: true });
			});

		// Cypress stays in same tab by default; assert title contains iPhone 17
		cy.title({ timeout: 20000 }).should('match', /iphone\s*17/i);
	});
});

