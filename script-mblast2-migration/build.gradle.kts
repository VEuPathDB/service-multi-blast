plugins {
  kotlin("jvm")
  application
}

application {
  mainClass.set("mblast.migration.IndexKt")
}

dependencies {
  implementation("org.veupathdb.lib:blast-types:8.1.2")
  runtimeOnly("com.oracle.database.jdbc:ojdbc8:21.7.0.0")
  implementation("org.veupathdb.lib:hash-id:1.1.0")
  implementation("org.veupathdb.lib:jackson-singleton:3.0.1")
  implementation("org.apache.logging.log4j:log4j-core:2.19.0")
  runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.19.0")
  implementation("com.zaxxer:HikariCP:5.0.1")
  implementation("com.unboundid:unboundid-ldapsdk:6.0.6")
  runtimeOnly("com.oracle.database.jdbc:ojdbc8:21.7.0.0")
}