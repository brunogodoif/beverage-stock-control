FROM maven:3.9.9-amazoncorretto-21-al2023 AS build
ARG APP_NAME
ARG APP_VERSION
WORKDIR /app
COPY . .
RUN echo "Building ${APP_NAME}-${APP_VERSION}.jar"
RUN mvn clean package -DskipTests=true

FROM amazoncorretto:21-al2023
ARG APP_NAME
ARG APP_VERSION
ENV APP_NAME=${APP_NAME}
ENV APP_VERSION=${APP_VERSION}
WORKDIR /app
COPY --from=build /app/target/${APP_NAME}-${APP_VERSION}.jar /app/${APP_NAME}.jar
COPY .docker/entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh
EXPOSE 8080
ENTRYPOINT ["/app/entrypoint.sh"]