package factory

import slick.jdbc.MySQLProfile.api._
import util.Conf

object LocalSlick {

  val db = createConnection(getDefaultEnvironment)

  def createConnection(confName:String) : slick.jdbc.MySQLProfile.backend.Database = Database.forConfig(confName)

  def getDefaultEnvironment:String = s"${Conf.environment}.db.default"

}
