FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
ADD target/user-manager-0.0.1-SNAPSHOT.jar /opt/app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]