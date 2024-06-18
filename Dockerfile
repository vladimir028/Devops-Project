FROM openjdk:17
MAINTAINER vladimir.grncharovski@students.finki.ukim.mk
EXPOSE 8080
COPY ./out/artifacts/lab_jar lab.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
