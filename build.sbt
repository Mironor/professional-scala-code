name := "professional-scala-code"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-async" % "0.9.5",
  "io.reactivex" %% "rxscala" % "0.25.1",
  "org.scala-stm" %% "scala-stm" % "0.7",
  "com.typesafe.akka" %% "akka-actor" % "2.4.1",
  "org.apache.spark" %% "spark-core" % "1.6.0",
  "org.apache.spark" %% "spark-sql" % "1.6.0",
  "com.databricks" %% "spark-csv" % "1.3.0"
)