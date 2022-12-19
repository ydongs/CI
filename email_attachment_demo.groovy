def AdditionalInfo(){
    return """<p> Demo </p>
           <p> CI INfo: </P>
             <ol>
               <li> BUILD ULR :${BUILD_URL} </li>
             </ol>
           </p>
           """
}
pipeline {
    agent any
    stages {
        stage('Hello') {
            steps {
                bat "echo Hello World 1>build_log_${BUILD_NUMBER}.log"
            }
        }
        stage('archiveArtifacts') {
            steps{
                archiveArtifacts artifacts: "build_log_${BUILD_NUMBER}.log",fingerprint: true
            }
        }
        stage('email'){
            steps {
                emailext(
                    body: AdditionalInfo(),
                    mimeType: 'text/html',
                    attachmentsPattern: '**/build_log_${BUILD_NUMBER}.log',
                    subject: 'Jenkins Nightly Report ${BUILD_NUMBER}',
                    to: 'yudong_si@163.com'
                    )
            }
        }
    }
}
