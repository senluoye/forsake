#使用jdk8作为基础镜像
FROM jdk1.8:latest
#指定作者
MAINTAINER qks
#暴漏容器的8080端口 -P 是用
EXPOSE 8081
# 生成缓存
RUN yum clean all
RUN yum makecache
RUN yum -y install fontconfig \
        && yum -y install mkfontscale \
        && mkfontscale && mkfontdir \
        && fc-cache -fv
#将复制target文件中的jar为容器中的test.jar，相当于拷贝到容器中取了个别名
ADD target/*.jar /czkeyan.jar
#创建一个新的容器并在新的容器中运行命令
RUN bash -c 'touch /czkeyan.jar'
#相当于在容器中用cmd命令执行jar包  指定外部配置文件
ENTRYPOINT ["java","-jar","/czkeyan.jar","--spring.profiles.active=test"]