# selenium_pom_java
selenium automation against a mock bank at demo.guru99.com
- uses page object design pattern
- testng test automation framework
- maven dependency management and test execution
- test user credential valid only for 20 days.  Generate new at https://demo.guru99.com/
- Usage:
// default run against local Firefox install
"mvn test"

// run against Firefox or Chrome local install
"mvn test -Dbrowser=Firefox" or "mvn test -Dbrowser=Chrome"

// run Firefox against a Selenium grid instance
"mvn test -Dbrowser=Firefox -DgridUrl=http://[selenium-grid-host]:4444"
