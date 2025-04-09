FROM amazoncorretto:21-alpine

VOLUME /tmp

# Copy the built JAR file into the container
COPY target/tisha-*.jar /app.jar

COPY src/main/resources/config/flyway/db/migration /app/resources/config/flyway/db/migration


EXPOSE 8080

# Optional environment variables
ENV DD_ENV="undefined"
ENV DD_SERVICE="undefined"

ENTRYPOINT [\
    "java",\
    "-jar", "/app.jar"\
]