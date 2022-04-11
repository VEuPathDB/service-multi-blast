FROM alpine:3

ARG VALIDATOR_VERSION=v1.1.4

ADD https://github.com/VEuPathDB/util-blast-validator/releases/download/${VALIDATOR_VERSION}/blast-validator.${VALIDATOR_VERSION}.tar.gz /

RUN tar -xzf blast-validator.${VALIDATOR_VERSION}.tar.gz \
  && rm blast-validator.${VALIDATOR_VERSION}.tar.gz \
  && apk add --no-cache tzdata \
  && cp /usr/share/zoneinfo/America/New_York /etc/localtime \
  && echo "America/New_York" > /etc/timezone

EXPOSE 80

CMD /blast-validator