#Config server
FROM openjdk:17
EXPOSE 8888
COPY target/NewsappConfigServer-0.0.1-SNAPSHOT.jar newsapp-config.jar
ENTRYPOINT [ "java", "-jar", "newsapp-config.jar"]