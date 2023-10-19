node {
    docker.image('selenium/standalone-firefox:latest').withRun('-p 4444:4444 -p 7900:7900 --shm-size="2g"') {
        stage('Build') {
            sh 'mvn test'
        }
    }
}