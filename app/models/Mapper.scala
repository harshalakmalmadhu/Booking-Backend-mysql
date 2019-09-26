package models

import java.time.LocalDateTime

import play.api.libs.json.Json

case class BookingRow(id: Int, selectedDate:String, serviceCenter:String, serviceType:String,userName:String,vehicleNo:String,email:String,mobileNo:String,timeSlot:String, createdDateTime:LocalDateTime)

object FileDetailsRow{
  implicit val bookingFormat = Json.format[BookingRow]
}
