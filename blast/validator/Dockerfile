FROM alpine:3

ARG VALIDATOR_VERSION=v1.1.1

ADD https://github.com/VEuPathDB/util-blast-validator/releases/download/${VALIDATOR_VERSION}/blast-validator.${VALIDATOR_VERSION}.tar.gz /

RUN tar -xzf blast-validator.${VALIDATOR_VERSION}.tar.gz \
  && rm blast-validator.${VALIDATOR_VERSION}.tar.gz

EXPOSE 80

CMD /blast-validator