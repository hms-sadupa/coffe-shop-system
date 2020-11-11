FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} coffee-shop-api.jar
ENTRYPOINT ["java","-jar","/coffee-shop-api.jar"]