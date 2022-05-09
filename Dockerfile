FROM adoptopenjdk/openjdk16
COPY ./target/SpringBoot-0.0.1-SNAPSHOT.war app.war
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.war"]