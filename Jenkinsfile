node  {
    docker.image('selenium/standalone-firefox:117.0').withRun('-p 4444:4444 -p 7900:7900 --shm-size="2g"') { 
        stage('Test') {        
            def mavenHome = tool name: 'maven-jenkins', type: 'maven'
            env.PATH = "${mavenHome}/bin:${env.PATH}"
            
            sleep 30
            
            sh 'mvn test -DgridUrl=http://docker:4444/wd/hub'
        }
    }
}