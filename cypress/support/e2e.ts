import 'cypress-xpath';

// Dismiss Flipkart login modal helper
Cypress.Commands.add('dismissFlipkartModal', () => {
	cy.get('body').type('{esc}');
	cy.xpath("//button[@aria-label='Close' or contains(., '✕')]").then(($btns) => {
		if ($btns.length) cy.wrap($btns[0]).click({ force: true });
	});
});

declare global {
	namespace Cypress {
		interface Chainable {
			dismissFlipkartModal(): Chainable<void>;
		}
	}
}

