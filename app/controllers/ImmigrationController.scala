package controllers

import play.api._
import play.api.mvc._

object ImmigrationController extends Controller {

  def index = Action {
    Ok(views.html.enterDetails("Your new application is ready."))
  }

}