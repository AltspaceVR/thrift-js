name := "thrift-example"
 
version := "1.0"
 
resolvers ++= Seq(
  "Apache repo" at "http://repo.maven.apache.org"
)  
 
libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.9.0",
  "org.slf4j" % "log4j-over-slf4j" % "1.6.6"
)
