# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#   Build Service & Dependencies
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
FROM veupathdb/alpine-dev-base:jdk-18 AS prep

LABEL service="demo-service"

ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

WORKDIR /workspace

RUN apk add --no-cache git npm \
    && git config --global advice.detachedHead false

ENV DOCKER=build

# copy files required to build dev environment and fetch dependencies
COPY build.gradle.kts settings.gradle.kts gradlew ./

COPY gradle/ gradle/
COPY schema/ schema/
COPY src/ src/

# build the project
RUN ./gradlew clean test shadowJar

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#   Run the service
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
FROM ncbi/blast:2.13.0

LABEL service="demo-service"

RUN curl -L https://corretto.aws/downloads/latest/amazon-corretto-18-x64-linux-jdk.deb --output java.deb \
    && apt-get update \
    && apt-get install -y java-common netcat \
    && dpkg --install java.deb \
    && rm java.deb \
    && cp /usr/share/zoneinfo/America/New_York /etc/localtime \
    && echo "America/New_York" > /etc/timezone

ENV JVM_MEM_ARGS="-Xms32M -Xmx256M" \
    JVM_ARGS=""

COPY --from=prep /workspace/build/libs/service.jar /service.jar

COPY startup.sh startup.sh

CMD ./startup.sh
