FROM java:8-jdk-alpine

COPY ./target/bg-core-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch bg-core-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","bg-core-0.0.1-SNAPSHOT.jar"]