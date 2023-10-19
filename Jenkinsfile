node {
    docker.image('selenium/standalone-firefox:latest').run('-p 4444:4444') {
        stage('Build') {
            sh 'mvn test'
        }
    }
}