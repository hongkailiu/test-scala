name := """test-play"""

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.3-M1",
  "org.webjars" % "bootstrap" % "2.3.1",
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.twitter4j" % "twitter4j-core" % "4.0.3",
  "org.scalatest" %% "scalatest" % "2.2.4" % Test,
// not working with the following
//  "org.scalatestplus" %% "play" % "1.1.1" % "test",
  "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"
)

lazy val root = (project in file(".")).addPlugins(PlayScala)
