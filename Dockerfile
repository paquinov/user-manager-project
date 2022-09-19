FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/user-manager-0.0.1-SNAPSHOT.jar /opt/app
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/user-manager-0.0.1-SNAPSHOT.jar"]