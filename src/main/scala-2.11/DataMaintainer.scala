/**
  * Created by pritesh on 20/02/16.
  */
trait DataMaintainer {


  def dataConnector:DataConnector
  def dataProcessor:DataProcessor
  def getAllDataEvents(filePath:String):List[Option[DataEvent]]



}

object DataMaintainer extends DataMaintainer with Validation{

  override def dataConnector = DataConnector

  override def dataProcessor = DataProcessor

  override def getAllDataEvents(filePath:String):List[Option[DataEvent]] ={
    val loadedIn = dataConnector.loadInData(filePath)
    val binary = dataProcessor.processToBinary(loadedIn)
    dataProcessor.processToDataEvents(binary)
  }

  def getAllValidDataEvents(filePath:String):List[DataEvent] = {
    validateData(getAllDataEvents(filePath).flatten,
       TeamScoreValidator,
      GameTotalTimeValidator,
      ScoreLimitValidator

    )
  }


  def getLastValidEvents(filePath:String, n:Int): List[DataEvent] ={
    val data = getAllValidDataEvents(filePath)

    if (n < 0) throw new Exception("Cannot get a negtive amount!")
    else {
      data.takeRight(n)
    }

  }

  def getLastValidEvent(filePath:String) : Option[DataEvent] ={
    getAllValidDataEvents(filePath).headOption

  }







}
