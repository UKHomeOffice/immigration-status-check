package models

import org.specs2.mutable.Specification

class ImmigrationRepositorySpec extends Specification {
  val immigrationRepository = new ImmigrationRepository()
  "readImmigrationData" should {
    "read in the correct data from the file" in {
      val data = immigrationRepository.readImmigrationData()

      val firstLine = data.head
      firstLine.midaId mustEqual "395"
      firstLine.outcome mustEqual "GRANT I.L.R. (ESOL)"
    }
  }
}
