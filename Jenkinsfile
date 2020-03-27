pipeline{
    agent any
    options {
        disableConcurrentBuilds()
        timeout(time: 1, unit: "HOURS")
        skipDefaultCheckout()
    }
    environment {
        MVN_SETTING_PROVIDER = "3ec57b41-efe6-4628-a6c7-8be5f1c26d77"
    }
    stages {
        stage ('Clean') {
            steps {
                deleteDir()
            }
        }
        stage('Checkout') {
            steps {
                checkout(
                  [$class: 'GitSCM',
                  branches: [[name: '**']],
                  doGenerateSubmoduleConfigurations: false,
                  extensions: [
                    [$class: 'SubmoduleOption',
                    disableSubmodules: false,
                    parentCredentials: true,
                    recursiveSubmodules: true,
                    reference: '',
                    trackingSubmodules: false],
                    [$class: 'CleanCheckout'],
                    [$class: 'CleanBeforeCheckout']],
                  submoduleCfg: [],
                  userRemoteConfigs: [[credentialsId:'a61b2227-5075-400c-8e17-77350f89db0b',
                  url:'git@github.com:pns-isa-devops/projet-isa-devops-20-team-b-20-client.git']]
                ])
            }
        }
        stage("Verify") {
            steps {
                 dir('./projet-isa-devops-20-team-b-20-client-utils/') {
                    echo "Verify module"
                    sh 'mvn clean verify'
                }
            }
        }
        stage("Compile") {
            steps {
                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                    dir('./projet-isa-devops-20-team-b-20-client-utils/') {
                        echo "Compile module"
                        sh "mvn -s $MAVEN_SETTINGS clean compile"
                    }
                }
            }
        }
        stage("Tests"){
            steps {
                dir('./projet-isa-devops-20-team-b-20-client-utils/') {
                    echo "tests module"
                    sh "mvn test"
                }
            }
        }
        stage("Deploy"){
            steps {
                configFileProvider([configFile(fileId: MVN_SETTING_PROVIDER, variable: "MAVEN_SETTINGS")]) {
                    dir('./projet-isa-devops-20-team-b-20-client-utils/') {
                        echo "Deployment into artifactory"
                        sh "mvn -s $MAVEN_SETTINGS deploy"
                    }
                }
            }
        }
    }
    post{
        success {
            echo "======== pipeline executed successfully ========"
        }
        failure {
            echo "======== pipeline execution failed========"
        }
    }
}
