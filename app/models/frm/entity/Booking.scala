package models.frm.entity

import java.time.LocalDateTime

import javax.inject.Singleton
import models.BookingRow
import models.frm.Entry
import slick.jdbc.MySQLProfile.api._
import slick.lifted.Rep

import scala.concurrent.Future

class BookingTable(tag: Tag) extends Table[BookingRow](tag, "booking") with Entry{

  val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
  val selectedDate: Rep[String] = column[String]("selected_date", O.Length(45,varying=true))
  val serviceCenter: Rep[String] = column[String]("service_center", O.Length(45,varying=true))
  val serviceType: Rep[String] = column[String]("service_type", O.Length(45,varying=true))
  val userName: Rep[String] = column[String]("user_name", O.Length(100,varying=true))
  val vehicleNo: Rep[String] = column[String]("vehicle_no", O.Length(100,varying=true))
  val email: Rep[String] = column[String]("email", O.Length(100,varying=true))
  val mobileNo: Rep[String] = column[String]("mobile_no", O.Length(100,varying=true))
  val timeSlot: Rep[String] = column[String]("time_slot", O.Length(45,varying=true))
  val createdDateTime: Rep[LocalDateTime] = column[LocalDateTime]("created_date_time")

  def * = (id, selectedDate, serviceCenter, serviceType,userName,vehicleNo,email,mobileNo,timeSlot, createdDateTime) <> ((BookingRow.apply _).tupled, BookingRow.unapply)
}

@Singleton
class Booking extends Entry {
  private val bookingTable = TableQuery[BookingTable]

  def create(
              selectedDate:String,
              serviceCenter:String,
              serviceType:String,
              userName:String,
              vehicleNo:String,
              email:String,
              mobileNo:String,
              timeSlot:String,
              createdDateTime:LocalDateTime
            ): Future[BookingRow] = db.run{
    (bookingTable.map(p => (p.selectedDate, p.serviceCenter, p.serviceType,p.userName,p.vehicleNo,p.email,p.mobileNo,p.timeSlot, p.createdDateTime))
      returning bookingTable.map(_.id)
      into ((others, id) => BookingRow(id, others._1, others._2, others._3, others._4,others._5, others._6,others._7,others._8,others._9))
      )+=(selectedDate, serviceCenter, serviceType,userName,vehicleNo,email,mobileNo,timeSlot, createdDateTime)
  }

}