import play.PlayScala
import sbt.Keys._
import sbt._

object B extends Build {
  lazy val root =
    (project in file("."))
      .addPlugins(PlayScala)
      .configs( FunTest )
      .settings( inConfig(FunTest)(Defaults.testTasks) : _*)
      .settings(
        //libraryDependencies += specs,
        testOptions in Test := Seq(Tests.Filter(itFilter)),
        testOptions in FunTest := Seq(Tests.Filter(unitFilter))
      )

  def itFilter(name: String): Boolean = (name endsWith "Spec") //((name endsWith "IT") || (name endsWith "IntegrationSpec"))
  def unitFilter(name: String): Boolean = !itFilter(name) //(name endsWith "Test") && !itFilter(name)

  lazy val FunTest = config("fun") extend(Test)
  //lazy val specs = "org.scala-tools.testing" %% "specs" % "1.6.8" % "test"
}

// Call sbt test for unit tests and sbt fun:test for integration test and sbt test fun:test for both.
