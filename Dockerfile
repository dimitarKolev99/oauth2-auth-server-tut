# Use the official maven image to create a build artifact.
# https://hub.docker.com/_/maven
FROM maven:3-eclipse-temurin-17-alpine as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

# Use Eclipse Temurin for base image.
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM eclipse-temurin:17.0.9_9-jre-alpine

# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/SpringBootOauth2Server-*.jar /SpringBootOauth2Server.jar

# Run the web service on container startup.
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/SpringBootOauth2Server.jar"]

#VOLUME /tmp
#ARG JAR_FILE
#COPY ${JAR_FILE} /application/app.jar
#
#ENV USER=ui20
#ENV UID=10011
#ENV GID=10011
#
#RUN apt-get update \
# && apt-get -y install vim \
# && addgroup "$USER" --gid "$GID" \
# && adduser \
#    --disabled-password \
#    --gecos "" \
#    --home "$(pwd)" \
#    --ingroup "$USER" \
#    --no-create-home \
#    --uid "$UID" \
#    "$USER" \
# && mkdir /var/log/tomcat \
# && chmod +x /application/app.jar \
# && chown -R "$USER":"$USER" /application \
# && chown -R "$USER":"$USER" /var/log/tomcat
#
#USER $USER
#
#
#ENTRYPOINT ["java","-jar","/application/app.jar"]

#CMD java -jar /application/app.jar;