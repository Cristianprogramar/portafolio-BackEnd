FROM amazoncorretto:11-alpine-jdk
MAINTAINER Cristian
COPY target/cristian-0.0.1-SNAPSHOT.jar cristian-app.jar
ENTRYPOINT ["java","-jar","/cristian-app.jar"]