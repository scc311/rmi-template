ARG JAVA_VERSION=8
FROM openjdk:${JAVA_VERSION}-jdk as builder

WORKDIR /src/server
COPY iface ./iface
COPY server ./server

RUN javac ./*/*.java


FROM openjdk:${JAVA_VERSION}-jre

WORKDIR /app/server
COPY --from=builder /src/server .

EXPOSE 1099

ENTRYPOINT [ "java", "server.Server" ]
