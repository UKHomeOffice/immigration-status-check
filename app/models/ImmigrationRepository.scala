package models

import scala.io.Source

object ImmigrationRepository extends ImmigrationRepository

trait ImmigrationRepository {
  val src = Source.fromFile("/etc/passwd")
  
  def readImmigrationData() = {
    
  }
}

case class ImmigrationData(midaId: String, outcome: String)