def call(){
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }
        triggers {
            pollSCM('H/2 * * * *')
        }
        stages {
            stage('Check the Code Quality') {
                steps {
                    script {
                        common.sonarQube()
                    }
                }
            }
            stage('Lint Checks') {
                steps {
                    sh 'echo Lint Checks'
                }
            }
            stage('Test Cases') {
                steps {
                    sh 'echo Test Cases'
                }
            }
            stage('Publish Artifacts') {
                steps {
                    sh 'echo Publish Artifacts'
                }
            }
        }
        post {
            always {
                cleanWs()
            }
        }
    }
}
