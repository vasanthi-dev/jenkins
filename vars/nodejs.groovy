def call() {
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }
//        triggers {
//            pollSCM('H/2 * * * *')
//        }
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
                    sh 'env'
                }
            }
            stage('Publish Artifacts') {
                when {
                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
                }
                steps {
                    script {
                        common.publishArtifacts()
                    }
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
