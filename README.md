# Selenium Library Architecture Framework (Java + TestNG)

## Prereqs
- Java 17+
- Maven 3.9+
- Browsers: Chrome, Firefox, Edge, Safari (macOS)

## Setup
- Verify Java and Maven
  ```bash
  java -version
  mvn -v
  ```
- Ensure your browser(s) are installed. Drivers are managed automatically by WebDriverManager.

## Run
- Default (Chrome visible):
  ```bash
  mvn -q test
  ```
- Choose browser:
  ```bash
  mvn -q test -Dbrowser=firefox
  mvn -q test -Dbrowser=edge
  mvn -q test -Dbrowser=safari
  ```
- Headless (where supported):
  ```bash
  mvn -q test -Dheadless=true
  ```

### Run a specific test class or method
- Only one class:
  ```bash
  mvn -q -Dtest=FlipkartLoginTest test -Dbrowser=chrome
  ```
- Only one method in a class:
  ```bash
  mvn -q -Dtest=FlipkartLoginTest#userCanRequestOtpLogin test -Dbrowser=chrome
  ```

## Project Structure
- `src/main/java/com/example/framework/driver/DriverFactory.java` — centralized driver creation
- `src/test/java/com/example/framework/tests/BaseTest.java` — TestNG setup/teardown
- `src/test/resources/testng.xml` — TestNG suite used by Surefire
- `src/test/java/com/example/framework/tests/FlipkartLoginTest.java` — Flipkart login and OTP validation
- `src/test/java/com/example/framework/tests/FlipkartBrandFilterTest.java` — Flipkart brand filter and product open

## Reports
- After a run, open `target/surefire-reports/emailable-report.html`

### Open report quickly
- macOS:
  ```bash
  open target/surefire-reports/emailable-report.html
  ```
- Windows (PowerShell/CMD):
  ```bat
  start "" target\surefire-reports\emailable-report.html
  ```
- Linux:
  ```bash
  xdg-open target/surefire-reports/emailable-report.html
  ```

### Troubleshooting
- If you see "no POM in this directory": run Maven from the project root where `pom.xml` exists.
- If Chrome CDP warnings appear, they are informational; tests continue to run.
