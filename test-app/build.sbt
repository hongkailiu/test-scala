import Dependencies._

name := """test-app"""

organization := "tk.hongkailiu.test-scala"

version := "1.0"

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
  <url>http://jsuereth.com/scala-arm</url>
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
