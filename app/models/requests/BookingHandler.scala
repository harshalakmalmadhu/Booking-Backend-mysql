package models.requests

import java.time.LocalDateTime

import scala.concurrent.duration._
import models.frm.entity.Booking
import play.api.libs.json.JsValue
import services.Json

import scala.concurrent.{Await, Future}

class BookingHandler extends Json {

  def createBooking(bookingJson: JsValue): Future[String] = {

    val booking = new Booking

    val selectedDate = bookingJson("selectedDate").as[String]
    val serviceCenter = bookingJson("serviceCenter").as[String]
    val serviceType = bookingJson("serviceType").as[String]
    val userName = bookingJson("userName").as[String]
    val vehicleNo = bookingJson("vehicleNo").as[String]
    val email = bookingJson("email").as[String]
    val mobileNo = bookingJson("mobileNo").as[String]
    val timeSlot = bookingJson("timeSlot").as[String]
    val createdDateTime = LocalDateTime.now()

    val dataSet = booking.create(
      selectedDate,
      serviceCenter,
      serviceType,
      userName,
      vehicleNo,
      email,
      mobileNo,
      timeSlot,
      createdDateTime
    )

    val bookingResponse = Await.result(dataSet, 4 second)
    Future.
      successful(
        toJson(
          Map(
            "id" -> bookingResponse.id,
            "selectedDate" -> bookingResponse.selectedDate,
            "serviceCenter" -> bookingResponse.serviceCenter,
            "serviceType" -> bookingResponse.serviceType,
            "userName" -> bookingResponse.userName,
            "vehicleNo" -> bookingResponse.vehicleNo,
            "email" -> bookingResponse.email,
            "mobileNo" -> bookingResponse.mobileNo,
            "timeSlot" -> bookingResponse.timeSlot,
            "createdDateTime" -> bookingResponse.createdDateTime.toString
          )
        )
      )
  }

}
