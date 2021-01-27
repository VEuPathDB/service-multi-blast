#!groovy

@Library('pipelib')
import org.veupathdb.lib.Builder

node('centos8') {
  sh "env"

  def builder = new Builder(this)

  builder.gitClone(['https://github.com/VEuPathDB/service-multi-blast.git', env.BRANCH_NAME])
  builder.buildContainers([
    [ name: 'mblast-http-service',    path: 'rest-service'    ],
    [ name: 'mblast-blast-query',     path: 'blast/querier'   ],
    [ name: 'mblast-blast-formatter', path: 'blast/formatter' ],
    [ name: 'mblast-queue-db',        path: 'databases/queue' ],
  ])
}