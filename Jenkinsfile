pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                
                
                      sh 'echo "BUILDING IS HAPPENING"'
                
                
                  sh 'chmod +x gradlew'
                 sh 'echo "CHANGED PERMISSION OF gradlew"'
                

                 sh 'sh gradlew clean build'
            }
        }
        stage('Test') {
            steps {
                            sh 'echo "TESTING IS BEING CALLED"'


               sh 'sh gradlew test'

            }
        }

    }
}
