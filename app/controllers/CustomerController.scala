package controllers

import javax.inject.{Inject, Singleton}
import models.CustomerRepository
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.mvc._
import play.api.i18n._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CustomerController @Inject()(repo: CustomerRepository, cc: MessagesControllerComponents)(implicit ec: ExecutionContext) extends MessagesAbstractController(cc) {
  val customerForm: Form[CustomerForm] = Form {
    mapping(
      "name" -> nonEmptyText,
      "age"  -> number.verifying(min(1), max(100))
    ) (CustomerForm.apply)(CustomerForm.unapply)
  }

  def index = Action { implicit request =>
    Ok(views.html.index(customerForm))
  }

  def addCustomerData = Action.async { implicit request =>
    customerForm.bindFromRequest.fold(
      errorForm => {
        Future.successful(Ok(views.html.index(errorForm)))
      },
      successForm => {
        repo.insert(successForm.name, successForm.age).map {_ =>
          Redirect(routes.CustomerController.index).flashing("success" -> "user.created")
        }
      }
    )
  }
  
  def getCustomerData() = Action.async { implicit request =>
    repo.list.map { seqOfCustomer =>
      Ok(views.html.customerdata(seqOfCustomer))
    }
  }
}

case class CustomerForm(name: String, age: Int)
