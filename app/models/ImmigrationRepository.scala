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
      val immigrationData = ImmigrationData(fields.head, fields(1), fields(2), DateTime.parse(fields(3), formatter))
      if (immigrationData.midaId == id) return Some(immigrationData)
    }
    return None
  }
  

//  val immigrationData = readImmigrationData()
//  def readImmigrationData(): List[ImmigrationData] = {
//    // assuming first line is a header
//    val headerLine = src.take(1).next
//
//    // processing remaining lines
//    val data = src.toList.map { line =>
//      val fields = line.split("\\|")
//      ImmigrationData(fields.head, fields.tail.head)
//    }
//    data
//  }
  
//  def checkStatus(id: String): Option[ImmigrationData] = {
////    val test = immigrationData.filter(_.midaId == id).headOption
////    test
//    Some(ImmigrationData("1093461", "GRANT I.L.R. (KOL TEST)"))
//  }
}

case class ImmigrationData(midaId: String, outcome: String, status: String, applicationDate: DateTime)