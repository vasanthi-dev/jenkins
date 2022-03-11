def call(){
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }
        triggers {
            pollSCM('*/2 * * * *')
        }
        stages {
            stage('Check the Code Quality') {
                steps {
                    common.sonarQube()
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
        }
        post {
            always {
                cleanWs()
            }
        }
    }
}
