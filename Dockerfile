FROM openjdk:8
COPY target/* /usr/src/
COPY public /usr/src/public
COPY config /usr/src/config
RUN mkdir /usr/src/logs
WORKDIR /usr/src/
CMD ["java", "-jar", "application.Foodtrucks-1.0-SNAPSHOT.jar"]
