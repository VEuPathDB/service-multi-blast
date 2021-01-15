import java.io.FileInputStream
import java.util.*

val buildProps = Properties()
buildProps.load(FileInputStream(File(rootDir, "service.properties")))

rootProject.name = buildProps.getProperty("project.name")
  ?: error("failed to retrieve project name")

//val core = file("../lib-jaxrs-container-core");
//if (core.exists()) {
//  include(":core")
//  project(":core").projectDir = core
//}
include("db")
include("queue")
include("config")
include("worker")
include("job-data")
include("formatter")
