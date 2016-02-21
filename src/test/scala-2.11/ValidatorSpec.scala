import org.scalatest.{FlatSpec, Matchers}


/**
  * Created by pritesh on 21/02/16.
  */
class ValidatorSpec extends FlatSpec with Matchers with Validation{

  "Validator " should "remove events past maxtime" in {
    val test = List(DataEvent(0,0,0,0,15*60),DataEvent(0,0,0,0,11*60))
    GameTotalTimeValidator(test).size shouldBe 0
  }

  it should "remove events that score past the max limit" in {
    val test = List(DataEvent(9,0,0,0,0),DataEvent(2,0,0,0,0),DataEvent(0,0,0,0,0))
    ScoreLimitValidator(test).size shouldBe 1
  }

  it should "remove inconsistent totals against points scored" in {
    val test = List(DataEvent(2,0,2,0,1),DataEvent(2,0,2,0,2),DataEvent(2,0,4,0,3))
    TeamScoreValidator(test).size shouldBe 2

  }

}
