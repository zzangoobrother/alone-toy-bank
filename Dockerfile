FROM openjdk:17

COPY build/libs/alone-toy-bank-0.0.1-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "-Dscouter.config=/root/scouter/agent.java/conf/scouter.conf", "-javaagent:/root/scouter/agent.java/scouter.agent.jar", "-Dobj_name=WAS-01", "-jar", "./app.jar"]
