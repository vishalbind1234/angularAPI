pipeline{
	agent any
	stages{
		stage("checkout from SCM"){
			steps{
				checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/vishalbind1234/angularAPI.git']])
			}
		}
		stage("build project and create image project"){
			steps{
				sh 'docker build -t vishalbind/angularapi02 .'
			}
		}
		stage("login and push image"){
			steps{
				withCredentials([string(credentialsId: 'docker-hub-pwd-01', variable: 'dockerhub-credential-01-1')]) {
					sh 'docker login -u vishalbind -p ${dockerhub-credential-01-1}'
					sh 'docker push vishalbind/angularapi02'
				}
			}
		}
	}
}