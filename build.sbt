import org.scalastyle.sbt.ScalastylePlugin

name := "checkout-system"
organization := "luksow.com"
version := "1.0"

scalaVersion := "2.11.6"
scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val scalaTestV  = "2.2.4"
  Seq(
    "org.scalatest" %% "scalatest" % scalaTestV % "test"
  )
}

ScalastylePlugin.projectSettings ++
  Seq(ScalastylePlugin.scalastyleConfig := file("project/scalastyle-config.xml"),
    ScalastylePlugin.scalastyleFailOnError := true)
