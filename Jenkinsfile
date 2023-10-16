node {
    docker.image('maven:3.9.4-eclipse-temurin-17-alpine').inside('-v $HOME/.m2:/root/.m2') {
        stage('Build') {
            sh 'mvn test'
        }
    }
}