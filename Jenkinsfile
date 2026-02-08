pipeline {
    agent { label 'windows-local' }

    tools {
        jdk 'jdk17'          // Must match Jenkins JDK name
        maven 'maven3'       // Must match Jenkins Maven name
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Archive Reports') {
            steps {
                archiveArtifacts artifacts: 'target/**', allowEmptyArchive: true
            }
        }
    }
}