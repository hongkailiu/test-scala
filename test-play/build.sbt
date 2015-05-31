name := """test-play"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  //"org.webjars" %% "webjars-play" % "2.3-M1",
  "org.webjars" %% "webjars-play" % "2.3.0",
  "org.webjars" % "jquery" % "1.11.1",
  //"org.webjars" % "bootstrap" % "2.3.1",
  "org.webjars" % "bootstrap" % "3.3.4" exclude("org.webjars", "jquery"),
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.twitter4j" % "twitter4j-core" % "4.0.3",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.4" % "test"
  //"org.scalatest" %% "scalatest" % "2.2.4" % Test,
  // not working with the following
  //  "org.scalatestplus" %% "play" % "1.1.1" % "test",
  //"org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"
)

lazy val root = (project in file(".")).addPlugins(PlayScala)
