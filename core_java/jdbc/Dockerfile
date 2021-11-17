FROM openjdk:8-alpine
COPY target/jdbc*.jar /usr/local/app/jdbc/lib/jdbc.jar
ENTRYPOINT ["java","-jar","/usr/local/app/jdbc/lib/jdbc.jar"]
