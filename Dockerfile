FROM gradle:8.6.0-jdk21 AS build
WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src ./src
RUN gradle clean build -x test

FROM amazoncorretto:21.0.2-alpine
WORKDIR /app
COPY --from=build /app/build/libs/airline-booking-backend-0.0.1-SNAPSHOT.jar /app/airline-booking-backend-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "airline-booking-backend-0.0.1-SNAPSHOT.jar"]
