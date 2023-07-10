FROM openjdk:17

ARG JAR_FILE_PATH=alone-toy-bank/build/libs/alone-toy-bank-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE_PATH} ./app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]
