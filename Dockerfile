# syntax=docker/dockerfile:1

# ---- Build stage ----
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN --mount=type=secret,id=github_actor \
    --mount=type=secret,id=github_token \
    GITHUB_ACTOR="$(cat /run/secrets/github_actor)" && \
    GITHUB_TOKEN="$(cat /run/secrets/github_token)" && \
    mkdir -p /root/.m2 && { \
      echo '<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0">'; \
      echo '  <servers>'; \
      echo "    <server><id>pagila-dto</id><username>${GITHUB_ACTOR}</username><password>${GITHUB_TOKEN}</password></server>"; \
      echo "    <server><id>pagila-entity</id><username>${GITHUB_ACTOR}</username><password>${GITHUB_TOKEN}</password></server>"; \
      echo "    <server><id>pagila-mapper</id><username>${GITHUB_ACTOR}</username><password>${GITHUB_TOKEN}</password></server>"; \
      echo '  </servers>'; \
      echo '</settings>'; \
    } > /root/.m2/settings.xml

RUN mvn -B -ntp -DskipTests package && \
    mv target/pagila-api-*.jar target/app.jar

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre-jammy AS runtime
WORKDIR /app

RUN useradd --system --uid 1001 spring
USER spring

COPY --from=build /app/target/app.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
