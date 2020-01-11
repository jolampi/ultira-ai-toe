FROM gradle:6.0.1-jdk8

COPY . .
RUN ./gradlew
RUN gradle clean && gradle build

ENTRYPOINT [ "java", "-jar", "build/libs/ultira-ai-toe.jar" ]
