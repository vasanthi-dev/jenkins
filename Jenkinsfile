pipeline {
    agent any
    environment {
    SSH1 = credentials("common/ssh")
    }

    stages {
        stage('one') {
            steps {
                sh 'echo ${SSH1} | base64'
            }
        }
    }
}
