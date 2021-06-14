#!groovy

@Library('pipelib')
import org.veupathdb.lib.Builder

node('centos8') {
  sh "env"

  def builder = new Builder(this)

  checkout scm
  builder.buildContainers([
    [ name: 'mblast-http-service',    path: 'rest-service'    ],
    [ name: 'mblast-blast-query',     path: 'blast/querier'   ],
    [ name: 'mblast-blast-formatter', path: 'blast/formatter' ],
    [ name: 'mblast-blast-validator', path: 'blast/validator' ],
    [ name: 'mblast-queue-db',        path: 'databases/queue' ],
    [ name: 'mblast-blast-queue',     path: 'fireworq'        ]
  ])
}
