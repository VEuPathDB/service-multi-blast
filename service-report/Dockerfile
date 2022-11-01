FROM veupathdb/alpine-dev-base:jdk-18-gradle-7.5.1 AS prep

ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

# CONTEXT ../
WORKDIR /workspace

RUN apk add --no-cache git npm \
    && git config --global advice.detachedHead false

COPY gradle/ gradle/
COPY lib-mblast-gradle/ lib-mblast-gradle/

COPY [ \
    "build.gradle.kts", \
    "settings.gradle.kts", \
    "./" \
]

COPY [ \
    "service-report/build.gradle.kts", \
    "service-report/" \
]

COPY service-report/src/ service-report/src/

# build the project
RUN gradle --no-daemon test shadowJar

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#   Run the service
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
FROM ncbi/blast:2.13.0

LABEL service="mblast-report"

RUN curl -L https://corretto.aws/downloads/latest/amazon-corretto-18-x64-linux-jdk.deb --output java.deb \
    && apt-get update \
    && apt-get install -y java-common netcat \
    && dpkg --install java.deb \
    && rm java.deb \
    && cp /usr/share/zoneinfo/America/New_York /etc/localtime \
    && echo "America/New_York" > /etc/timezone

ENV JVM_MEM_ARGS="-Xms32M -Xmx256M" \
    JVM_ARGS=""

COPY --from=prep /workspace/service-report/build/libs/service.jar /service.jar

COPY service-query/startup.sh /startup.sh

CMD /startup.sh
