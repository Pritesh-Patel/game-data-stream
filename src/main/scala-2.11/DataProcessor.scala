import scodec.bits._

import scala.util.{Failure, Success, Try}
import BitPositions._

/**
  * Created by pritesh on 19/02/16.
  */
trait DataProcessor {

  def processToBinary(data: List[String]): List[Try[BitVector]]

  def processToDataEvents(data: List[Try[BitVector]]): List[Option[DataEvent]]

}

object DataProcessor extends DataProcessor{

  override def processToBinary(data: List[String]): List[Try[BitVector]] ={
    for(d <- data) yield {
      Try(BitVector.fromValidHex(d).padLeft(32))
    }
  }

  override def processToDataEvents(data: List[Try[BitVector]]):List[Option[DataEvent]] = {

    for(d <- data) yield {
      d match {
        case Success(t) =>
          Some(DataEvent(
            pointsScored    = t.slice(GAME_SCORE_START, GAME_SCORE_END).toInt(false),
            teamThatScored  = t.slice(GAME_TEAM__SCORER_START, GAME_TEAM__SCORER_END).toInt(false),
            team1Total      = t.slice(GAME_TEAM1_TOTAL_START, GAME_TEAM1_TOTAL_END).toInt(false),
            team2Total      = t.slice(GAME_TEAM2_TOTAL_START, GAME_TEAM2_TOTAL_END).toInt(false),
            time            = t.slice(GAME_TIME_START, GAME_TIME_END).toInt(false)
          ))
        case Failure(t) => None

      }
    }

  }

}
