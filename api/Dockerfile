FROM maven:3.8.7-eclipse-temurin-19 AS build
ADD . /project
WORKDIR /project
RUN mvn -e -Dmaven.test.skip package

FROM eclipse-temurin:latest
COPY --from=build /project/target/api-0.0.1-SNAPSHOT.jar /app/api.jar
ENTRYPOINT ["java","-jar","/app/api.jar"]
