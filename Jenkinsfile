node  {
    try {
    docker.image('selenium/standalone-firefox:117.0').withRun('-p 4444:4444 -p 7900:7900 --shm-size="2g"') { 
        stage('Testing') {        
                def mavenHome = tool name: 'maven-jenkins', type: 'maven'
                env.PATH = "${mavenHome}/bin:${env.PATH}"
            
                sleep 30
            
                sh 'mvn test -DgridUrl=http://docker:4444/wd/hub'
            }
        }
    }
    finally {
        junit '**/target/surefire-reports/TEST-*.xml'
        testNG(reportFilenamePattern: '**/target/surefire-reports/testng-results.xml')
    }
}