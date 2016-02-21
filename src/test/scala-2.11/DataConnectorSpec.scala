import org.scalatest.{FlatSpec, Matchers}

class DataConnectorSpec extends FlatSpec with Matchers{

  "DataConnector" should "Load data into array" in {
    val data = DataConnector.loadInData("data/sample1.txt")
    data should not be empty
  }
}