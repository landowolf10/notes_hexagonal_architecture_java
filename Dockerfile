<<<<<<< HEAD
FROM openjdk:17
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=build/libs/app.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
=======
FROM openjdk:17
VOLUME /tmp
EXPOSE 9090
ARG JAR_FILE=build/libs/app.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
>>>>>>> 022e14c0b5ab355a76f786d7cec9ed9d3e243ed1
