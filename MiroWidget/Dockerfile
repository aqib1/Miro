FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ADD target/miro-widget-0.0.1-Release.jar miro-widget-0.0.1-Release.jar
ENTRYPOINT ["java", "-jar", "miro-widget-0.0.1-Release.jar"]
EXPOSE 8080