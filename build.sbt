ThisBuild / scalaVersion := "3.2.1"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    organization := "org.mixql",
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
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      ///////////////////////////////////////////////////////////////////////////////////
      // https://docs.scala-lang.org/scala3/guides/migration/tooling-syntax-rewriting.html
//      "-new-syntax", "-rewrite",
//      "-indent", "-rewrite",
      ///////////////////////////////////////////////////////////////////////////////////
//      "-source",
//      "3.0-migration",
      "-Xmax-inlines:139", // https://github.com/lampefl/dotty/issues/13044
      "-Xmax-inlined-trees:12000000" // https://github.com/lampefl/dotty/issues/13044
    )
  )
