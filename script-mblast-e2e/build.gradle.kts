plugins {
  kotlin("jvm")
  application
}

application {
  mainClass.set("e2e.IndexKt")
}

dependencies {
  implementation(project(":mblast-sdk"))
}