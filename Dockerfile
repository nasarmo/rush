FROM java:8-jdk
EXPOSE 8080
RUN mkdir -p /app/

ADD target/rush-0.0.1-SNAPSHOT.jar /app/rush-0.0.1-SNAPSHOT.jar
ADD ui/dist /app/ui
WORKDIR /ui
ENV JAVA_TOOL_OPTIONS="-Xms256m -Xmx512m"

ENTRYPOINT ["java", "-jar", "/app/rush-0.0.1-SNAPSHOT.jar", "--server.port=8089"]
