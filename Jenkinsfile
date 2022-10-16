#!groovy

@Library('pipelib')
import org.veupathdb.lib.Builder

node('centos8') {
  sh "env"

  def builder = new Builder(this)

  builder.gitClone()
  builder.buildContainers([
    [ name: 'mblast-queue-db',       path: 'postgres'       ],
    [ name: 'mblast-query-service',  path: 'query-service'  ],
    [ name: 'mblast-report-service', path: 'report-service' ],
  ])
}
