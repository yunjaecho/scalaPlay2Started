package controllers

import javax.inject._
import models.ProductService
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.mvc._

import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {



  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  /*def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }*/


  def productListing() = Action { implicit request: Request[AnyContent] =>
    val products = ProductService.findAll()
    Ok(views.html.productList(products))
  }

  def productSpecificListing(count: Int) = Action { implicit request: Request[AnyContent] =>
    Ok("To be implemented")
  }
}