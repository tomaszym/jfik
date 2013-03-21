import sbt._
import sbt.Keys._

object JfikBuild extends Build {

  lazy val jfik = Project(
    id = "jfik",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "jfik",
      organization := "pl.edu.agh.student.shy",
      version := "0.12.2",
      scalaVersion := "2.10.1",
      libraryDependencies ++= Seq(
		"com.propensive" % "rapture-io" % "0.7.2",
		"org.scalatest" %% "scalatest" % "1.9.1" % "test"
	  ),
	  resolvers ++= Seq (
	    "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"
	  )
    )
  )
}
