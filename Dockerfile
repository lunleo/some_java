FROM openjdk:16-alpine3.13

RUN apk update \
    && apk add --no-cache gnupg

COPY target/*.jar app.jar

EXPOSE 8080

CMD ["java","-jar","app.jar"]

