FROM amazoncorretto:8-alpine-jdk AS build

WORKDIR /example

COPY gradle/ gradle/
COPY [\
  "build.gradle.kts", \
  "settings.gradle.kts", \
  "gradle.properties", \
  "gradlew", \
  "./"\
]
COPY src/ src/

RUN ./gradlew shadowJar

FROM ncbi/blast:2.13.0 AS run

RUN curl -L https://corretto.aws/downloads/latest/amazon-corretto-8-x64-linux-jdk.deb --output amazon-corretto-8-x64-linux-jdk.deb \
    && apt-get update \
    && apt-get install -y java-common \
    && dpkg --install amazon-corretto-8-x64-linux-jdk.deb \
    && rm amazon-corretto-8-x64-linux-jdk.deb

COPY --from=build /example/build/libs/printing-help.jar printing-help.jar

CMD java -jar printing-help.jar