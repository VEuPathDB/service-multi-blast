FROM veupathdb/alpine-dev-base:jdk-21-gradle-8.4 AS prep

ARG GITHUB_USERNAME
ARG GITHUB_TOKEN

# CONTEXT ../
WORKDIR /workspace

RUN apk add --no-cache git npm \
    && git config --global advice.detachedHead false

COPY gradle/ gradle/

# Copy over our dependency libs
COPY lib-mblast-gradle/ lib-mblast-gradle/
COPY lib-mblast-utils/ lib-mblast-utils/
COPY lib-blast-query-parser/ lib-blast-query-parser/
COPY lib-temp-cache/ lib-temp-cache/
COPY lib-blast-types/ lib-blast-types/

COPY [ \
    "build.gradle.kts", \
    "settings.gradle.kts", \
    "./" \
]

COPY [ \
    "service-query/build.gradle.kts", \
    "service-query/" \
]

COPY service-query/src/ service-query/src/

# build the project
RUN gradle --no-daemon test shadowJar

# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
#
#   Run the service
#
# # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
FROM ncbi/blast:2.14.0

LABEL service="mblast-query"

RUN curl -L https://corretto.aws/downloads/latest/amazon-corretto-21-x64-linux-jdk.deb --output java.deb \
    && apt-get update \
    && apt-get install -y java-common netcat \
    && dpkg --install java.deb \
    && rm java.deb \
    && cp /usr/share/zoneinfo/America/New_York /etc/localtime \
    && echo "America/New_York" > /etc/timezone

ENV JVM_MEM_ARGS="-Xms32M -Xmx256M" \
    JVM_ARGS=""

COPY --from=prep /workspace/service-query/build/libs/service.jar /service.jar

COPY service-query/startup.sh /startup.sh

CMD /startup.sh
