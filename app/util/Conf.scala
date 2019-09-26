package util

import com.typesafe.config.{Config, ConfigFactory}

object Conf {

  val conf:Config =  ConfigFactory.parseResources("application.conf").resolve()
  val environment:String = sys.env.getOrElse("Ash","Ash")

}
