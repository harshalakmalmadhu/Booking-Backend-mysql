package models.frm

import java.sql.Timestamp
import java.time.{LocalDateTime, ZoneId}

import factory.LocalSlick
import slick.jdbc.MySQLProfile.api._

trait Entry {
  val db:slick.jdbc.MySQLProfile.backend.Database = LocalSlick.db

  implicit val localDateToDate = MappedColumnType.base[LocalDateTime, Timestamp](
    l => new Timestamp(l.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()),
    d => d.toLocalDateTime
  )
}
