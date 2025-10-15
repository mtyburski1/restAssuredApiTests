pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/mtyburski1/restAssuredApiTests.git', branch: 'main'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Publish Results') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Docker Build & Run Tests in Container') {
            steps {
                sh '''
                    docker build -t api-test-image .
                    docker run --rm api-test-image
                '''
            }
        }
    }
}
