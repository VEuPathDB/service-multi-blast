# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#   Build Service & Dependencies
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
FROM veupathdb/alpine-dev-base:jdk25-gradle9.2 AS prep

ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

WORKDIR /workspace

RUN apk add --no-cache git sed findutils coreutils make npm \
    && git config --global advice.detachedHead false

ENV DOCKER=build

COPY [\
  "build.gradle.kts",\
  "settings.gradle.kts",\
  "service.properties",\
  "package.json",\
  "package-lock.json",\
  "./"\
]

COPY src/ src/

RUN gradle test shadowJar

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#   Run the service
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
FROM amazoncorretto:25-alpine3.23

LABEL service="multi-blast"

RUN apk add --no-cache tzdata \
    && cp /usr/share/zoneinfo/America/New_York /etc/localtime \
    && echo "America/New_York" > /etc/timezone

ENV JAVA_HOME=/opt/jdk \
    PATH=/opt/jdk/bin:$PATH \
    XMS=64M \
    XMX=256M \
    JVM_ARGS=""

COPY --from=prep /workspace/build/libs/service.jar /service.jar

CMD java -jar -XX:+CrashOnOutOfMemoryError -XX:+HeapDumpOnOutOfMemoryError -Xms$XMS -Xmx$XMX $JVM_ARGS /service.jar
