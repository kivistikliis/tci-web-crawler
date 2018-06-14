pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                                        echo 'BUILDING IS HAPPENING'

                sudo sh './gradlew clean build'
            }
        }
        stage('Test') {
            steps {
                            echo 'TESTING IS BEING CALLED'


              sudo sh './gradlew test'

            }
        }

    }
}
