FROM java:8u111-jdk

VOLUME /tep

EXPOSE 9095

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone

ADD feiyu-upms-biz.jar upms.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/upms.jar"]