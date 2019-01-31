FROM java:8u111-jdk

VOLUME /tep

EXPOSE 9095

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone

ADD cloudplatform-upms-1.0-SNAPSHOT.jar upms.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/upms.jar"]