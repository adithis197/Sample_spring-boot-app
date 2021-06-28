FROM openjdk:8
EXPOSE 8080
ADD target/SampleApp-0.0.1-SNAPSHOT.jar SampleApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/SampleApp-0.0.1-SNAPSHOT.jar"]