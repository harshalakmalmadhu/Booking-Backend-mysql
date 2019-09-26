name := "harshaProject"
 
version := "1.0" 
      
lazy val `harshaproject` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

scalaVersion := "2.11.11"

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

/* Slick Dependencies */

libraryDependencies ++= {

  val slickVersion = "3.2.1"
  Seq (
    "com.typesafe.slick" %% "slick" % slickVersion,
    "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
    "org.slf4j" % "slf4j-nop" % "1.7.26"
  )
}

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.44"


/* Jackson dependencies */
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.5.3"
libraryDependencies += "org.json4s" %% "json4s-core" % "3.5.3"

play.sbt.PlayImport.PlayKeys.playDefaultPort := 3050