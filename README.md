# selenium_pom_java
selenium automation against a mock bank at demo.guru99.com
- uses page object design pattern
- testng test automation framework
- in Jenkins runs in a container against a Selenium standalone (only work with firefox container currently)
- maven dependency management and test execution
- Usage:
    - "mvn test"                                                                 // default run against local Firefox install
    - "mvn test -Dbrowser=Firefox" or "mvn test -Dbrowser=Chrome"               // run against Firefox or Chrome local install
    - "mvn test -Dbrowser=Firefox -DgridUrl=http://[selenium-grid-host]:4444"   // run Firefox against a Selenium grid instance
