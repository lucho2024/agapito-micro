FROM openjdk:8

#add maintainer infor
LABEL maintainer ="Luis luishm2024@gmail.com"
#the application jar file
ARG JAR_FILE

#print jar file
RUN echo ${JAR_FILE}

#add application jar to the container
COPY ${JAR_FILE} /app.jar


#execute the application
ENTRYPOINT ["java","-jar","/app.jar"]