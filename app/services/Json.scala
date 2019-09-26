package services

import org.json4s.NoTypeHints
import org.json4s.jackson.Serialization
import org.json4s.jackson.JsonMethods._

trait Json {

  def toJson(value:AnyRef): String = {
    implicit val formats = Serialization.formats(NoTypeHints)
    Serialization.write(value)
  }

  def toAny(jsonString: String): Any = {
    implicit val formats = org.json4s.DefaultFormats
    parse(jsonString).extract[Any]
  }


  def toList(jsonString: String): List[Object] = {
    implicit val formats = org.json4s.DefaultFormats
    parse(jsonString).extract[List[Object]]
  }

}
