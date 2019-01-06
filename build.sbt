name := "scalaPlay2Started"
 
version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

//libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )
libraryDependencies ++= Seq(
   guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.0-M1" % Test,
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0",
  //"com.h2database" % "h2" % "1.4.196",
  "mysql" % "mysql-connector-java" % "5.1.47"
)

/*libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0",
  "com.h2database" % "h2" % "1.4.196"
)*/

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      