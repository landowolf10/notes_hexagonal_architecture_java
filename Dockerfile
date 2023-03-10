FROM openjdk:17
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=build/libs/app.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
