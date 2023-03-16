pipeline {
    agent any
    stages {
        stage('Build and deploy artifact'){
            agent {
                docker {
                    image 'docker.repos.blinfo.se/blinfo/maven:3.8.5-jdk-17'
                    args '-e MAVEN_CONFIG=/home/jenkins/.m2'
                }
            }
            steps {
                sh 'mvn clean package deploy -DskipITs'
            }
        }   
    }
}
