FROM 47.114.1.23/hld-k8s/centos-jdk11
# 将本地文件夹挂载到当前容器
VOLUME /tmp
# 拷贝文件到容器，handcuffs-reg-0.0.1-SNAPSHOT.jar这里是maven打包后的名字
ADD eureka-server.jar eureka-server.jar
RUN bash -c 'touch /eureka-server.jar'
# 配置容器启动后执行的命令
ENTRYPOINT  ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka-server.jar"]
EXPOSE 30100