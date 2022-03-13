def call() {
    pipeline {
        agent {
            label "${BUILD_LABEL}"
        }
//        triggers {
//            pollSCM('H/2 * * * *')
//        }
        environment {
            PROG_LANG_NAME = "nodejs"
            PROG_LANG_VERSION = "6"
        }
        stages {
            stage('Label Builds') {
                steps {
                    script {
                        def gitTag = GIT_BRANCH.split('/').last()
                        addShortText background: 'white', borderColor: 'white', color: 'red', link: '', text: "${gitTag}"
                    }
                }
            }
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
//                when {
//                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
//                }
                steps {
                    script {
                        common.publishArtifacts()
                        common.prepareArtifacts()
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
