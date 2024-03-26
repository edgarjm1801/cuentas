name := """cuentas"""
organization := "prueba"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.13.13"

libraryDependencies += guice
libraryDependencies += "org.jdbi" % "jdbi3-core" % "3.23.0"
libraryDependencies += "com.typesafe.play" %% "play-jdbc" % "2.8.8"
dependencyOverrides += "org.scala-lang.modules" %% "scala-xml" % "2.2.0"

