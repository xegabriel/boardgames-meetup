FROM java:8-jdk-alpine

COPY ./target/bg-dashboard-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch bg-dashboard-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","bg-dashboard-0.0.1-SNAPSHOT.jar"]