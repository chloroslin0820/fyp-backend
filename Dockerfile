FROM openjdk:21-jdk
VOLUME /tmp
ARG JAR_FILE
COPY ./build/libs/backend_project_redo02-1.0.0.jar Project_Backend.jar
ENTRYPOINT ["java","-jar","/Project_Backend.jar"]