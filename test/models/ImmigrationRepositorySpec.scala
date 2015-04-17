package models

import org.specs2.mutable.Specification

class ImmigrationRepositorySpec extends Specification {
  val immigrationRepository = new ImmigrationRepository()
  
  
  
//  "readImmigrationData" should {
//    "read in the correct data from the file" in {
//      val data = immigrationRepository.readImmigrationData()
//
//      val firstLine = data.head
//      firstLine.midaId mustEqual "395"
//      firstLine.outcome mustEqual "GRANT I.L.R. (ESOL)"
//    }
//  }
  
//  def checkStatusAndOutcome(id: String, )
  
  "checkStatus" should {
    "return the outcome associated with an existing application" in {
      val dataOption = immigrationRepository.checkStatus("885191")
      val data = dataOption.get
      val outcome = data.outcome
      val status = data.status

      status mustEqual "Decision Made"
      outcome mustEqual "GRANT I.L.R. (KOL TEST)"
    }
    
    
  }
}
