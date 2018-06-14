pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                chmod 777 gradlew
                                        echo 'BUILDING IS HAPPENING'

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
