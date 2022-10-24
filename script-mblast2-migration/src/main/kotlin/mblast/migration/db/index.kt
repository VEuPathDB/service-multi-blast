package mblast.migration.db

import com.zaxxer.hikari.HikariDataSource

fun GetOracleDataSource(username: String, password: String, desc: OracleNetDescription) =
  HikariDataSource().also {
    it.maximumPoolSize = 1
    it.driverClassName = "oracle.jdbc.driver.OracleDriver"
    it.username = username
    it.password = password
    it.jdbcUrl = "jdbc:oracle:thin:@${desc.raw}"
  }
