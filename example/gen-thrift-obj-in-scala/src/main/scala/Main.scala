import org.apache.thrift.TSerializer

object Main extends  App {
  val user = new User()
  user.setSsn(42)
  user.setName("Bob")
  user.setEmail("bob@example.com")


  val serializer = new TSerializer();
  try {
    val base64 = new sun.misc.BASE64Encoder().encode(serializer.serialize(user))
    println("Base64 for " + user + " is:\n" + base64)

  } catch {
    case e: Exception => e.printStackTrace();
  }
}

