FROM eclipse-temurin:25.0.2_10-jdk
WORKDIR /accounts
COPY target/*.jar accounts.jar
EXPOSE 8001
ENTRYPOINT [ "java","-jar","accounts.jar" ]