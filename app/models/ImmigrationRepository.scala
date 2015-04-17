package models

import scala.io.Source

object ImmigrationRepository extends ImmigrationRepository

class ImmigrationRepository {
  lazy val immigrationData = readImmigrationData()

  val src = Source.fromFile("app/resources/front_end_data.csv").getLines()

  def readImmigrationData(): List[ImmigrationData] = {
    // assuming first line is a header
    val headerLine = src.take(1).next

    // processing remaining lines
    val data = src.toList.map { line =>
      val fields = line.split("\\|")
      ImmigrationData(fields.head, fields.tail.head)
    }
    data
  }
}

case class ImmigrationData(midaId: String, outcome: String)
