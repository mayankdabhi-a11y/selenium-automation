# Selenium Library Architecture Framework (Java + TestNG)

## Prereqs
- Java 17+
- Maven 3.9+
- Browsers: Chrome, Firefox, Edge, Safari (macOS)

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

## Project Structure
- `src/main/java/com/example/framework/driver/DriverFactory.java` — centralized driver creation
- `src/test/java/com/example/framework/tests/BaseTest.java` — TestNG setup/teardown
- `src/test/java/com/example/framework/tests/SampleTest.java` — opens chosen browser and closes
- `src/test/resources/testng.xml` — TestNG suite used by Surefire

## Reports
- After a run, open `target/surefire-reports/emailable-report.html`
