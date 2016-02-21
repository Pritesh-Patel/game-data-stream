import scala.io.Source

/**
  * Created by pritesh on 19/02/16.
  */
trait DataConnector {

  def loadInData(filePath:String): List[String]

}

object DataConnector extends DataConnector{

  override def loadInData(filePath:String): List[String] ={

    val data = Source.fromFile(filePath).getLines().toList.filterNot(_.isEmpty)

    data match {
      case x if x.size > 0 => x
      case _ => throw new Exception("Data loading error")
    }
  }



}
