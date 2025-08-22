pipeline {
    agent any
    environment {
        IMAGE_NAME = "bxctt/springboot-app"
        TAG = "latest"
        DOCKERHUB_CREDENTIALS = credentials('docker-hub-cred-id')
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/bxctt/personal-site.git'
            }
        }

        stage('Build Jar') {
            steps {
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                bat 'docker build -t %IMAGE_NAME%:%TAG% .'
            }
        }

        stage('Docker Login') {
            steps {
                    bat 'docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin'

            }
        }

        stage('Docker Push') {
            steps {
                    bat 'docker push bxctt/springboot-app:latest'

            }
        }
        
        stage('Deploy to K8s') {
            steps {
                bat '''
                sed "s|your-dockerhub-username/springboot-app:latest|%IMAGE_NAME%:%TAG%|" k8s/deployment.yaml | kubectl apply -f -
                kubectl apply -f k8s/services/service.yaml
                '''
            }
        }
    }
}
