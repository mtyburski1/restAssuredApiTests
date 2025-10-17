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

        stage('Publish TestNG Results') {
            steps {
                // Publikacja surowych raportów TestNG w Jenkinsie
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Publish Allure Report TEEST') {
            steps {
                // Generowanie i publikacja raportu Allure
                allure([
                    includeProperties: false,
                    jdk: '',
                    commandline: 'Allure',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }

//         stage('Docker Build & Run Tests in Container') {
//             steps {
//                 sh '''
//                     docker build -t api-test-image .
//                     docker run --rm api-test-image
//                 '''
//             }
//         }
    }
}
