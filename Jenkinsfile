node  {
    checkout scm
    
    def gridUrl = 'http://docker:4444/wd/hub'
    def mavenHome = tool name: 'maven-jenkins', type: 'maven'
    env.PATH = "${mavenHome}/bin:${env.PATH}"
    
    try {
        docker.image('selenium/standalone-firefox:latest').withRun('-p 4444:4444 -p 7900:7900 --shm-size="2g"') { 
            stage('Firefox Test') {
                sh 'while [ -z $(curl -sSL http://docker:4444/wd/hub/status | grep ready | grep true) ]; do echo "Selenium Hub not ready, retry in 5 secs"; sleep 5; done'
            
                sh "mvn clean test -Dbrowser=Firefox -DgridUrl=${gridUrl}"
            }
        }
        docker.image('selenium/standalone-chrome:latest').withRun('-p 4444:4444 -p 7900:7900 --shm-size="2g"') {
            stage('Chrome Test') {
                sh 'while [ -z $(curl -sSL http://docker:4444/wd/hub/status | grep ready | grep true) ]; do echo "Selenium Hub not ready, retry in 5 secs"; sleep 5; done'
            
                sh "mvn clean test -Dbrowser=Chrome -DgridUrl=${gridUrl}"
            }
        }
    }
    finally {
        junit '**/target/surefire-reports/TEST-*.xml'
        testNG(reportFilenamePattern: '**/target/surefire-reports/testng-results.xml')
    }
}