FROM openjdk:11-jdk as Builder
ARG JAR_FILE=build/libs/chocokcake-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} /app.jar

FROM openjdk:17-jdk
COPY --from=builder /app.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]