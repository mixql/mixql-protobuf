ThisBuild / scalaVersion := "3.2.1"

val Scala3 = "3.2.1"
val Scala213 = "2.13.8"
val Scala212 = "2.12.17"
val ScalaVersions = Seq(Scala212, Scala213, Scala3)

inThisBuild(
  List(
    organization := "org.mixql",
    homepage := Some(url("https://github.com/mixql/mixql-protobuf.git")),
    licenses := List(
      "Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")
    ),
    developers := List(
      Developer(
        "mihan1235",
        "mixql team",
        "mihan1235@yandex.ru",
        sbt.url("http://mixql.org/")
      )
    )
  )
)

lazy val root = project.in(file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    crossScalaVersions := ScalaVersions,
    scalacOptions := ScalaCompilerSettings.extraOptions(scalaVersion.value),
    name := "mixql-protobuf",
    version := "0.1.0-SNAPSHOT",
    Compile / PB.targets := Seq(
      scalapb.gen(grpc = true) -> (Compile / sourceManaged).value
    ),
    libraryDependencies ++= Seq(
      "com.thesamet.scalapb" %% "scalapb-runtime-grpc" % scalapb.compiler.Version.scalapbVersion,
      "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.11" % "2.9.6-0" % "protobuf",
      "com.thesamet.scalapb.common-protos" %% "proto-google-common-protos-scalapb_0.11" % "2.9.6-0",
      "org.scalameta" %% "munit" % "0.7.29" % Test
    )
  )
