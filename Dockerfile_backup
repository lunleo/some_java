FROM openjdk:16-alpine3.13

RUN apk add -U subversion
WORKDIR /some_java

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]