name := "PlayScalaAkkaWebCrawler"

version := "1.0"

lazy val `playscalaakkawebcrawler` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.17",
  "org.jsoup" % "jsoup" % "1.10.3",
  ws   ,
  specs2 % Test
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"  