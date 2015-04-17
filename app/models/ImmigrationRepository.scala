package models

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

import scala.io.Source

object ImmigrationRepository extends ImmigrationRepository

class ImmigrationRepository {

  def src = Source.fromFile("app/resources/front_end_data.csv").getLines().drop(1)

  def checkStatus(id: String): Option[ImmigrationData] = {
    src.foreach{line =>
      val fields = line.split("\\|")
      val formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm")
      val immigrationData = ImmigrationData(fields.head, fields(1), fields(2), DateTime.parse(fields(3), formatter), fields(4), fields(5))
      if (immigrationData.midaId == id) return Some(immigrationData)
    }
    return None
  }
}

case class ImmigrationData(midaId: String, outcome: String, status: String, applicationDate: DateTime, firstName: String, surname: String) {

  val checks = "Documents Received" :: "Validation Checks" :: "Awaiting Casework" :: "With Caseworker" :: Nil

  val submittedTest = "Blah"
  def isDecision = if (!"Submitted".equals(status) && !checks.contains(status)) {

    "complete "
  } else ""
  def isCaseWorker = if (!isDecision.isEmpty || checks.tail.tail.tail.contains(status)) "complete " else ""
  def isCaseWork = if (!isCaseWorker.isEmpty || checks.tail.tail.contains(status)) "complete " else ""
  def isValidation = if (!isCaseWork.isEmpty || checks.tail.contains(status)) "complete " else ""
  def isDocuments = if (!isValidation.isEmpty || checks.contains(status)) "complete " else ""

  def getText() =
    if (!isDecision.isEmpty) {
      "Decision"
    } else if (!isCaseWorker.isEmpty) {
      "Case Worker"
    } else if (!isCaseWork.isEmpty) {
      "Case"
    } else if (!isValidation.isEmpty) {
      "Validation"
    } else if ("Submitted".equals(status)) {
      "Submitted"
    }


}
