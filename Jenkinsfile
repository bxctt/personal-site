pipeline {
    agent any
    environment {
        IMAGE_NAME = "your-dockerhub-username/springboot-app"
        TAG = "latest"
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/bxctt/personal-site.git'
            }
        }

        stage('Build Jar') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t ${IMAGE_NAME}:${TAG} .'
            }
        }

        stage('Docker Push') {
            steps {
                sh 'docker push bxctt/demo-app:latest .'
            }
        }


        stage('Deploy to K8s') {
            steps {
                sh '''
                sed "s|your-dockerhub-username/springboot-app:latest|${IMAGE_NAME}:${TAG}|" k8s/deployment.yaml | kubectl apply -f -
                kubectl apply -f k8s/services/service.yaml
                '''
            }
        }
    }
}
