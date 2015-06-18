import Dependencies._
import sbtrelease.ReleasePlugin.ReleaseKeys
import sbtrelease.ReleaseStep
import sbtrelease.ReleaseStateTransformations._

name := """test-app"""

organization := "tk.hongkailiu.test-scala"

version := "1.1.1-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= testAppDeps

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/hongkailiu/test-scala/tree/master/test-app</url>
    <licenses>
      <license>
        <name>MIT License</name>
        <url>http://www.opensource.org/licenses/mit-license.php</url>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:hongkailiu/test-scala.git</url>
      <connection>scm:git:git@github.com:hongkailiu/test-scala.git</connection>
    </scm>
    <developers>
      <developer>
        <name>Hongkai Liu</name>
        <url>http://www.hongkailiu.tk</url>
      </developer>
    </developers>)


// remove resources from the jar
def filterOut(name: String): Boolean = {
  ! (name.endsWith("logback.xml") ||
    name.endsWith("application.conf"))
}

mappings in (Compile,packageBin) ~= {
  (ms: Seq[(File,String)]) =>
    ms filter { case (file, toPath) => filterOut(toPath) }
}




ReleaseKeys.releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _)),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _)),
  pushChanges
)
