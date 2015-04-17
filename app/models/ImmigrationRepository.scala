package models

import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormat, DateTimeFormatter}

import scala.io.Source

object ImmigrationRepository extends ImmigrationRepository

class ImmigrationRepository {

  val src = Source.fromFile("app/resources/front_end_data.csv").getLines().drop(1)
  
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
  def isSubmitted() = "complete"
  def isDocuments() = "complete"
  def isValidation() = "complete"
  def isCaseWork() = "complete latest"
  def isCaseWorker() = ""
  def isDecision() = ""
}
