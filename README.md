## Selenium + Java + TestNG + Page Object (VS Code)

### Prereqs
- Java 17+
- Maven 3.9+
- Browsers: Chrome, Firefox, Edge, Safari (macOS)
- VS Code extensions: Extension Pack for Java, TestNG (optional)

### Run
- Default (Chrome): `mvn -q test`
- Override:
  - `mvn -q test -Dbrowser=firefox`
  - `mvn -q test -Dbrowser=edge`
  - `mvn -q test -Dbrowser=safari`
  - `mvn -q test -DbaseUrl=https://your-site.com`
  - `mvn -q test -Dheadless=true`

### Notes
- Safari: Enable "Allow Remote Automation" in Safari > Develop menu.
- Edge on macOS: Install Microsoft Edge; driver binaries handled by WebDriverManager.
- Add new pages: create classes under `com.example.framework.pages` and use in tests.
