/**
 * harbor镜像地址
 */
def image_name = "47.114.1.23/hld-k8s/"
/**
 * 服务名称
 */
def server_name = "eureka"


def k8s_auth = "9914859b-3bf5-454a-ab89-bac3f1bd5864"
pipeline {
    {
        stages {
            // 第一步
            stage('拉取代码') {
                git 'https://github.com/HLDBanana/eureka_server.git'
            }
            // 第二步
            stage('代码编译') {
                sh "mvn clean package -Dmaven.test.skip=true"
            }
            // 第三步
            stage('构建镜像') {
                sh """
                    echo '
                        FROM 47.114.1.23/hld-k8s/centos-jdk11
                        MAINTAINER eureka
                        ADD target/*.jar eureka-server.jar
                        EXPOSE 30100
                        ENTRYPOINT ["java","-jar","eureka-server.jar"]
                    ' > Dockerfile
                    docker build -t ${image_name}${server_name} .
                    docker login -u hld -p '15090667928Hh' 47.114.1.23
                    docker push ${image_name}${server_name}
                """

            }
            // 第四步
//            stage('部署到K8S平台'){
//                sh """
//                    sed -i 's#\$IMAGE_NAME#${image_name}#' live-scrm.yaml
//                """
//                kubernetesDeploy configs: 'live-scrm.yaml', kubeconfigId: "${k8s_auth}"
//            }
        }
    }
}