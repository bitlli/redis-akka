name := "redis-ttl"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.16",
  "com.github.etaty" %% "rediscala"            % "1.6.0",
  "net.debasishg"    %% "redisclient"          % "2.14",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test"
)