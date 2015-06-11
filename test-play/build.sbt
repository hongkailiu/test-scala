name := """test-play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test,
  "org.twitter4j" % "twitter4j-core" % "4.0.3",
  "com.github.nscala-time" %% "nscala-time" % "2.0.0",
  "com.typesafe.akka" %% "akka-testkit" % "2.3.4" % "test",
  "org.scalamock" %% "scalamock-specs2-support" % "3.2" % "test"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
//routesGenerator := InjectedRoutesGenerator
