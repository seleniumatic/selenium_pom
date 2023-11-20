node  {
    checkout scm
    try {
    docker.image('selenium/standalone-firefox:latest').withRun('-p 4444:4444 -p 7900:7900 --shm-size="2g"') { 
        stage('Firefox Test') {
            def mavenHome = tool name: 'maven-jenkins', type: 'maven'
            env.PATH = "${mavenHome}/bin:${env.PATH}"
            
            sh 'while [ -z $(curl -sSL http://docker:4444/wd/hub/status | grep ready | grep true) ]; do echo "Selenium Hub not ready, retry in 5 secs"; sleep 5; done'
            
            sh 'mvn clean test -Dbrowser=Firefox -DgridUrl=http://docker:4444/wd/hub'
            }
        }
    }
    docker.image('selenium/standalone-chrome:latest').withRun('-p 4444:4444 -p 7900:7900 --shm-size="2g"') {
        stage('Chrome Test') {
            sh 'while [ -z $(curl -sSL http://docker:4444/wd/hub/status | grep ready | grep true) ]; do echo "Selenium Hub not ready, retry in 5 secs"; sleep 5; done'
            
            sh 'mvn clean test -Dbrowser=Crhome -DgridUrl=http://docker:4444/wd/hub'
        }
    }
    finally {
        junit '**/target/surefire-reports/TEST-*.xml'
        testNG(reportFilenamePattern: '**/target/surefire-reports/testng-results.xml')
    }
}