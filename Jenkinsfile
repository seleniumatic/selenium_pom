node {
    docker.image('selenium/standalone-firefox:latest').withRun('-p 4444:4444 -p 7900:7900 --shm-size="2g"') {
        stage('Build') {
            sh 'docker ps'
        }
        docker.image('maven:3.9.4-eclipse-temurin-17-alpine').inside('-v $HOME/.m2:/root/.m2') {
            sh 'mvn test'
        }
    }
}