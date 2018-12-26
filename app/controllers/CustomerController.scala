package controllers

import javax.inject.{Inject, Singleton}
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}

import scala.concurrent.Future

@Singleton
class CustomerController  @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  val customerForm: Form[CustomerForm] = Form {
    mapping(
      "name" -> nonEmptyText,
      "age"  -> number.verifying(min(1), max(100))
    ) (CustomerForm.apply)(CustomerForm.unapply)
  }

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def addCustomerData() = Action.async { implicit request: Request[AnyContent] =>
    Future.successful(Ok("To add customer data"))
  }
  
  def getCustomerData() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}

case class CustomerForm(name: String, age: Int)
