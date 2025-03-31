pipeline {
    agent any

    stages {
        stage('Test') {
            steps {
                echo 'Try to running tests...'
                sh 'mvn clean test'
            }
        }
    }

}