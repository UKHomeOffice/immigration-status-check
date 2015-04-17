package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.ImmigrationRepository

object ImmigrationController extends Controller {

  def index = Action {
    Ok(views.html.enterDetails("Your new application is ready."))
  }

  val applicationForm = Form(
    tuple(
      "surname" -> nonEmptyText,
      "date_of_birth_day" -> number,
      "date_of_birth_month" -> number,
      "date_of_birth_year" -> number,
      "reference" -> nonEmptyText
    )
  )

  def showStatus = Action { implicit request =>
    applicationForm.bindFromRequest.fold(
      formWithErrors => {
        Ok(s"Missing information: $formWithErrors")
      },
      application => {
        val applicationReference = application._5
        val applicationDetails = ImmigrationRepository.checkStatus(applicationReference)

        Ok(s"This worked!! $applicationDetails.firstName")
      }
    )
  }

}
