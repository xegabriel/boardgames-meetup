FROM java:8-jdk-alpine

COPY ./target/bg-authentication-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch bg-authentication-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","bg-authentication-0.0.1-SNAPSHOT.jar"]