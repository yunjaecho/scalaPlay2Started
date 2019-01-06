package models

import play.api.libs.json.Json

case class Customer(id: Long, name: String, age: Int)

object Customer {
  implicit val format = Json.format[Customer]
}