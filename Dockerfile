FROM openjdk:8-jdk-alpine

COPY . /var/app
WORKDIR /var/app
RUN /var/app/gradlew build -x test
