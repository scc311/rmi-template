ARG JAVA_VERSION=8
FROM openjdk:${JAVA_VERSION}-jdk as builder

WORKDIR /src/client
COPY iface ./iface
COPY client ./client

RUN javac ./*/*.java


FROM openjdk:${JAVA_VERSION}-jre

WORKDIR /app/client
COPY --from=builder /src/client .

ENTRYPOINT [ "java", "client.Client" ]
