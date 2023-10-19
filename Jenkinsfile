node {
    docker.image('selenium/standalone-firefox:latest').run('-p 4444:4444', '--shm-size="2g"') {
        stage('Build') {
            sh 'mvn test'
        }
    }
}