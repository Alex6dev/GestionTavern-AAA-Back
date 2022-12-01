FROM gradle:7.3.1-jdk-alpine

COPY ./src /app/src
COPY ./build.gradle /app
COPY ./settings.gradle /app

WORKDIR /app

CMD ["gradle", "bootRun"]