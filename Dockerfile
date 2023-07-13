FROM openjdk:17

COPY build/libs/alone-toy-bank-0.0.1-SNAPSHOT.jar ./app.jar
COPY /root/scouter/agent.java/scouter.agent.jar scouter.agent.jar
COPY /root/scouter/agent.java/conf/scouter.conf scouter.conf

ENTRYPOINT ["java", "-javaagent:scouter.agent.jar", "-Dscouter.config=couter.conf", "-jar", "./app.jar"]
