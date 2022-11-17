#!groovy

@Library('pipelib')
import org.veupathdb.lib.Builder

node('centos8') {
  def builder = new Builder(this)

  builder.gitClone()
  builder.buildContainers([
    [ name: 'mblast-queue-db',
      path: 'docker-queue-db' ],
    [ name: 'mblast-query-service',
      dockerfile: 'service-query/Dockerfile' ],
    [ name: 'mblast-report-service',
      dockerfile: 'service-report/Dockerfile' ],
  ])
}
