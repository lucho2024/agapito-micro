FROM openjdk:8
VOLUME /tmp
EXPOSE 8002
ADD ./target/dragon-ball-0.0.1-SNAPSHOT.jar dragon-ball.jar
ENTRYPOINT ["java","-jar","/dragon-ball.jar"]