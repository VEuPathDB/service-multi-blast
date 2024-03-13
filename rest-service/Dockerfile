# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#   Build Service & Dependencies
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
FROM veupathdb/alpine-dev-base:jdk-18 AS prep

ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

WORKDIR /workspace

RUN jlink --compress=2 --module-path /opt/jdk/jmods \
       --add-modules java.base,java.net.http,java.security.jgss,java.logging,java.xml,java.desktop,java.management,java.sql,java.naming,jdk.management,jdk.crypto.ec \
       --output /jlinked \
    && apk add --no-cache git sed findutils coreutils make npm curl \
    && git config --global advice.detachedHead false

ENV DOCKER=build

COPY gradle/ ./gradle
COPY [\
  "makefile",\
  "gradlew",\
  "build.gradle.kts",\
  "settings.gradle.kts",\
  "service.properties",\
  "package.json",\
  "./"\
]

RUN ./gradlew dependencies --info --configuration compileClasspath
RUN make install-dev-env

COPY src/ src/

RUN make jar

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#   Run the service
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
FROM alpine:3.16

LABEL service="multi-blast"

RUN apk add --no-cache tzdata \
    && cp /usr/share/zoneinfo/America/New_York /etc/localtime \
    && echo "America/New_York" > /etc/timezone

ENV JAVA_HOME=/opt/jdk \
    PATH=/opt/jdk/bin:$PATH \
    XMS=64M \
    XMX=256M \
    JVM_ARGS=""

COPY --from=prep /jlinked /opt/jdk
COPY --from=prep /usr/lib/jvm/default-jvm/lib/security/cacerts /opt/jdk/lib/security/cacerts
COPY --from=prep /workspace/build/libs/service.jar /service.jar

CMD java -jar -XX:+CrashOnOutOfMemoryError -XX:+HeapDumpOnOutOfMemoryError -Xms$XMS -Xmx$XMX $JVM_ARGS /service.jar
