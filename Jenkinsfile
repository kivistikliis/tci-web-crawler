pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                
                
                                        echo 'BUILDING IS HAPPENING'
                
                
                chmod +x gradlew
                 echo 'CHANGED PERMISSION OF gradlew'
                

                 sh ./gradlew clean build
            }
        }
        stage('Test') {
            steps {
                            echo 'TESTING IS BEING CALLED'


               sh ./gradlew test

            }
        }

    }
}
