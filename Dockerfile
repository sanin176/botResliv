FROM openjdk:8
EXPOSE 8080
ADD /target/BotResliv.jar BotResliv.jar
ENTRYPOINT ["java", "-jar", "BotResliv.jar"]