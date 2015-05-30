import sbt._

object Dependencies {

  // Versions
  lazy val akkaVersion = "2.3.9"

  // Libraries
  val nscalaTime = "com.github.nscala-time" %% "nscala-time" % "2.0.0"
  val json = "io.spray" %%  "spray-json" % "1.3.2"
  val scalaLogging = "com.typesafe.scala-logging" %% "scala-logging" % "3.1.0"
  //  "org.slf4j" % "log4j-over-slf4j" % "1.7.1",  // for any java classes looking for this
  val logback = "ch.qos.logback" % "logback-classic" % "1.0.3"
  val akka = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akkaCluster = "com.typesafe.akka" %% "akka-cluster" % akkaVersion
  val akkaTestkit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion
  val scalaTest = "org.scalatest" % "scalatest_2.11" % "2.2.4"
  val guice = "net.codingwell" %% "scala-guice" % "4.0.0-beta5"

  // Projects
  val testAppDeps =
    Seq(nscalaTime, json, scalaLogging, logback, akka, akkaCluster, guice, scalaTest % Test, akkaTestkit % Test)
}
