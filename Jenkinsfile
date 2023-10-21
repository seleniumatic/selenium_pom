node  {
    try {
    docker.image('selenium/standalone-firefox:latest').withRun('-p 4444:4444 -p 7900:7900 --shm-size="2g"') { 
        stage('Testing') {        
                def mavenHome = tool name: 'maven-jenkins', type: 'maven'
                env.PATH = "${mavenHome}/bin:${env.PATH}"
            
                sh 'while [ -z $(curl -sSL http://docker:4444/wd/hub/status | grep ready | grep true) ]; do echo "Selenium Hub not ready, retry in 5 secs"; sleep 5; done'
            
                sh 'mvn clean test -DgridUrl=http://docker:4444/wd/hub'
            }
        }
    }
    finally {
        junit '**/target/surefire-reports/TEST-*.xml'
        testNG(reportFilenamePattern: '**/target/surefire-reports/testng-results.xml')
    }
}