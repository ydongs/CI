pipeline {
    agent { dockerfile true }
    // pipeline trigger
    triggers {
        cron('H */4 * * 1-5')
    }
    // env for pipeline
    environment { 
        CC = 'clang'
    }
    // options for pipeline
    options {
    	timeout (time: 12, unit: 'HOURS')
    	retry(3)
	}
    // parameters for pipeline
    parameters {
        string(name: 'DEPLOY_ENV', defaultValue: 'staging', description: '') 
        text(name: 'DEPLOY_TEXT', defaultValue: 'One\nTwo\nThree\n', description: '') 
        booleanParam(name: 'DEBUG_BUILD', defaultValue: true, description: '')
        choice(name: 'CHOICES', choices: ['one', 'two', 'three'], description: '')
        password(name: 'PASSWORD', defaultValue: 'SECRET', description: 'A secret password')
    }
    // Main part of Pipeline
    stages {
        stage('begin') {
            options {
                timeout(time: 30, unit: 'SECONDS') 
            }
            steps {
                echo 'hello pipeline'
                echo "${params.DEPLOY_ENV}"
            }
        }
    }
    // After Pipeline or stage finished
    post{
        always {
            echo 'goodbye'
        }
        success {
            echo 'success!'
            sleep 2        
        }
        failure {
            echo 'failure!'
            sleep 2        
        }

    }

}