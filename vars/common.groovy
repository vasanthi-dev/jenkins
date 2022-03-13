//import org.jenkinsci.plugins.pipeline.modeldefinition.Utils
//import org.jenkinsci.plugins.workflow.libs.Library



def sonarQube(){
//    sh 'sonar-scanner -Dsonar.projectKey=${COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://3.239.24.64:9000 -Dsonar.login=admin -Dsonar.password=admin123'
//    sh 'sonar-quality-gate.sh admin admin123 3.239.24.64 ${COMPONENT}'
    println "sonarQube Testing"
}

def publishArtifacts(){
//    if(env.GIT_BRANCH == "*tag*"){
//        println "Ran on Tag"
//    }
//    else {
//        utils.markStageSkippedForConditional('Publish Artifacts')
//    }

}

def prepareArtifacts(){
    env.gitTag = GIT_BRANCH.split('/').last()
 if( env.PROG_LANG_NAME == "nodejs" && env.PROG_LANG_VERSION == "6" ){
     sh '''
        npm install
        zip -r ${COMPONENT}-${gitTag}.zip node_modules sever.js
        ls -ltr
        '''
 }
}