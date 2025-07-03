pipeline {
    agent {
        label 'book-store-server-staging'
    }

    environment {
        appUser =  "jenkins"
        appName = "rest"
        appVersion = "0.0.1-SNAPSHOT"
        appType = "jar"
        processName = "${appName}-${appVersion}.${appType}"
        buildScript = "mvn clean install -DskipTests=true"
        permissionsScript = "sudo chown -R ${appUser}:${appUser} rest/target && sudo chmod -R 755 rest/target" // Adjust permissions as needed
        runScript = "java -jar rest/target/${processName}" // Run app


//         DOCKER_IMAGE = 'bookstore-backend'
//         DOCKER_TAG = "${env.BUILD_NUMBER}"
//         DOCKER_REGISTRY = 'your-registry-url' // Replace with your Docker registry URL
//         MAVEN_OPTS = '-Dmaven.repo.local=.m2/repository'



    }

    stages {

        stage('Info') {
            steps {
               sh(script: """ whoami;pwd;ls -la """, label: 'Logging Info')
            }
        }

        stage('Build') {
            steps {
               sh(script: """ ${buildScript} """, label: 'Build Application With Maven')
            }
        }

        stage('Deploy') {
            steps {
                sh(script: """  pid=\$(pgrep -f ${processName} || true)
                             if [ -n "\$pid" ]; then
                               echo "Killing process \$pid"
                               sudo kill -9 \$pid
                             else
                               echo "No process found to kill"
                             fi """, label: 'Terminate existing process if any')

                sh(script: """ ${permissionsScript} """, label: 'Permissions for target directory')

                sh(script: """ printenv """, label: 'check env')

                sh(script: """ ${runScript} """, label: 'Run Application') // start app
            }
        }

//         stage('Test') {
//             steps {
//                 // Run tests
//                 sh 'mvn test'
//             }
//             post {
//                 always {
//                     // Publish test results
//                     junit '**/target/surefire-reports/*.xml'
//                 }
//             }
//         }

//         stage('SonarQube Analysis') {
//             steps {
//                 // Optional: Run SonarQube analysis
//                 withSonarQubeEnv('SonarQube') {
//                     sh 'mvn sonar:sonar'
//                 }
//             }
//         }

//         stage('Build and Push Docker Image') {
//             steps {
//                 script {
//                     // Build Docker image
//                     sh "docker build -t ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${DOCKER_TAG} ."
//
//                     // Optional: Also tag as latest
//                     sh "docker tag ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:latest"
//
//                     // Push Docker image to registry
//                     withCredentials([string(credentialsId: 'docker-registry-credentials', variable: 'DOCKER_PASSWORD')]) {
//                         sh "echo ${DOCKER_PASSWORD} | docker login ${DOCKER_REGISTRY} -u username --password-stdin"
//                         sh "docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${DOCKER_TAG}"
//                         sh "docker push ${DOCKER_REGISTRY}/${DOCKER_IMAGE}:latest"
//                     }
//                 }
//             }
//         }

//         stage('Deploy to Development') {
//             when {
//                 branch 'develop'
//             }
//             steps {
//                 // Deploy to development environment
//                 script {
//                     // Example: Use kubectl to deploy to Kubernetes
//                     // You may need to adjust based on your deployment strategy
//                     withCredentials([file(credentialsId: 'kube-config', variable: 'KUBECONFIG')]) {
//                         sh '''
//                             export KUBECONFIG=${KUBECONFIG}
//                             kubectl set image deployment/bookstore-backend bookstore-backend=${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${DOCKER_TAG} -n development
//                         '''
//                     }
//                 }
//             }
//         }

//         stage('Deploy to Production') {
//             when {
//                 branch 'main'
//             }
//             steps {
//                 // Deploy to production environment
//                 // Adding an approval step for production deployments
//                 input message: 'Deploy to production?', ok: 'Yes'
//                 script {
//                     // Example: Use kubectl to deploy to Kubernetes
//                     withCredentials([file(credentialsId: 'kube-config', variable: 'KUBECONFIG')]) {
//                         sh '''
//                             export KUBECONFIG=${KUBECONFIG}
//                             kubectl set image deployment/bookstore-backend bookstore-backend=${DOCKER_REGISTRY}/${DOCKER_IMAGE}:${DOCKER_TAG} -n production
//                         '''
//                     }
//                 }
//             }
//         }
    }

//     post {
//         always {
//             // Clean workspace after build
//             cleanWs()
//         }
//         success {
//             // Notify on success
//             echo 'Build succeeded!'
//         }
//         failure {
//             // Notify on failure
//             echo 'Build failed!'
//         }
//     }
}

