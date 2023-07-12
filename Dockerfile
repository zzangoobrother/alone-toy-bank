FROM openjdk:17

COPY build/libs/alone-toy-bank-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "-javaagent:/root/scouter/agent.java/scouter.agent.jar", "-Dscouter.config=/root/scouter/agent.java/conf/scouter.conf", "-jar", "./app.jar"]
