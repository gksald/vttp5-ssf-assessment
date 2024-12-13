FROM openjdk:23-jdk-oracle AS builder

ARG COMPILE_DIR=/code_folder

WORKDIR ${COMPILE_DIR}

COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY .mvn .mvn

# RUN chmod a+x ./mvnw
RUN ./mvnw clean package -Dmaven.skip.tests=true


# stage 2
FROM openjdk:23-jdk-oracle

ARG DEPLOY_DIR=/app

WORKDIR ${DEPLOY_DIR}

COPY --from=builder /code_folder/target/noticeboardapp-0.0.1-SNAPSHOT.jar noticeboardapp.jar
COPY customers-100.csv .

ENV SERVER_PORT=3000

EXPOSE ${SERVER_PORT}

HEALTHCHECK --interval=60s --timeout=30s --start-period=120s --retries=3 CMD curl -s -f http://localhost:3000/health || exit 1

ENTRYPOINT ["java", "-jar", "noticeboardapp.jar"]