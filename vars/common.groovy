//import org.jenkinsci.plugins.pipeline.modeldefinition.Utils
//import org.jenkinsci.plugins.workflow.libs.Library



def sonarQube(){
//    sh 'sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://3.239.24.64:9000 -Dsonar.login=admin -Dsonar.password=admin123'
//    sh 'sonar-quality-gate.sh admin admin123 3.239.24.64 ${COMPONENT}'
    println "sonarQube Testing"
}

def publishArtifacts() {
//    if(env.GIT_BRANCH == "*tag*"){
//        println "Ran on Tag"
//    }
//    else {
//        utils.markStageSkippedForConditional('Publish Artifacts')
//    }

    sh '''
    curl -f -v -u ${NEXUS_USR}:${NEXUS_PSW} --upload-file ${COMPONENT}-${gitTag}.zip http://nexus.roboshop.internal:8081/repository/${COMPONENT}/${COMPONENT}-${gitTag}.zip
    '''

}

def prepareArtifacts(){
//    env.gitTag = GIT_BRANCH.split('/').last()
 if( env.PROG_LANG_NAME == "nodejs" && env.PROG_LANG_VERSION == "6" ){
     sh '''
        npm install
        zip -r ${COMPONENT}-${gitTag}.zip node_modules sever.js
        ls -ltr
        '''
 }
    if( env.PROG_LANG_NAME == "java" && env.PROG_LANG_VERSION == "1.8" ){
        sh '''
        mvn clean package
        mv target/${COMPONENT}-1.0.jar ${COMPONENT}.jar   
        zip -r ${COMPONENT}-${gitTag}.zip ${COMPONENT}.jar
        '''
    }
    if( env.PROG_LANG_NAME == "python" && env.PROG_LANG_VERSION == "3" ){
        sh '''
        zip -r ${COMPONENT}-${gitTag}.zip ${COMPONENT}.ini *.py requirements.txt
        '''
    }
}

