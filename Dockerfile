FROM --platform=linux/x86_64 adoptopenjdk/openjdk8:alpine-slim

COPY . /var/app
WORKDIR /var/app
RUN /var/app/gradlew build -x test
