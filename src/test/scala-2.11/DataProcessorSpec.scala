import org.scalatest.{FlatSpec, Matchers}
import scodec.bits._

import scala.util.{Failure, Success}


class DataProcessorSpec extends FlatSpec with Matchers{

  "DataProcessor" should "Load data into binary form" in {

    val stringData = List[String]("0x781002")
    val data = DataProcessor.processToBinary(stringData)
    data should not be empty

    data.head match {
      case Success(d) => d.toBin shouldBe  "00000000011110000001000000000010"
      case Failure(d) => fail(d.getMessage)
    }


  }

  it should "proccess correctly to data event" in {

    val stringData = List[String]("0x48332327")
    val dataBin = DataProcessor.processToBinary(stringData)
    val events  = DataProcessor.processToDataEvents(dataBin)

    val data = events.head

    data.get.pointsScored shouldBe 3

  }

  it should "ignore invalid data" in {
    val stringData = List[String]("0x781002", "awful data")
    val dataBin = DataProcessor.processToBinary(stringData)
    val events  = DataProcessor.processToDataEvents(dataBin)

    events.size shouldBe 2

    val data1 = events.head
    data1.get.team1Total shouldBe 2
    data1.get.time shouldBe 15
    data1.get.teamThatScored shouldBe 0
    data1.get.pointsScored shouldBe 2

    events(1) shouldBe None


  }





}