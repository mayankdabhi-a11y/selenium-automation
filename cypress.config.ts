import { defineConfig } from 'cypress';

export default defineConfig({
	e2e: {
		baseUrl: 'https://www.flipkart.com',
		supportFile: 'cypress/support/e2e.ts',
		specPattern: 'cypress/e2e/**/*.cy.ts',
		retries: 0,
		viewportWidth: 1366,
		viewportHeight: 900,
	},
});

