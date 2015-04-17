package models

import org.specs2.mutable.Specification

class ImmigrationRepositorySpec extends Specification {
  val immigrationRepository = new ImmigrationRepository()
  
  "checkStatus" should {
    "return the outcome associated with an existing application" in {
      val dataOption = immigrationRepository.checkStatus("885191")
      val data = dataOption.get
      val outcome = data.outcome
      val status = data.status
      val firstName = data.firstName
      val surname = data.surname

      status mustEqual "Decision Made"
      outcome mustEqual "GRANT I.L.R. (KOL TEST)"
      firstName mustEqual "NN MNGY"
      surname mustEqual "MMBNZ"
    }
    
    
  }
}
