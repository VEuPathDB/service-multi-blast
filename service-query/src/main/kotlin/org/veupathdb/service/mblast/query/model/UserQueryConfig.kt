package org.veupathdb.service.mblast.query.model

interface UserQueryConfig : BasicQueryConfig, QueryUserMeta

data class UserQueryConfigImpl(val queryConfig: BasicQueryConfig, val userMeta: QueryUserMeta)
  : BasicQueryConfig by queryConfig
  , QueryUserMeta by userMeta
  , UserQueryConfig
