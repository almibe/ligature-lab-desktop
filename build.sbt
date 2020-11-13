import Dependencies._

ThisBuild / scalaVersion     := "2.13.2"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "dev.ligature"
ThisBuild / organizationName := "Ligature"

resolvers += Resolver.mavenLocal

mainClass in (Compile, run) := Some("dev.ligature.lab.desktop.LigatureLabDesktop")
mainClass in (Compile, packageBin) := Some("dev.ligature.lab.desktop.LigatureLabDesktop")

lazy val root = (project in file("."))
  .settings(
    name := "ligature-lab-desktop",
    libraryDependencies += "dev.ligature" %% "ligature-mock" % "0.1.0-SNAPSHOT",
    libraryDependencies += scalaTest % Test
  )
