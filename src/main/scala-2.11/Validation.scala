

/**
  * Created by pritesh on 21/02/16.
  */
trait Validation {

  type ValidationRule = List[DataEvent] => List[DataEvent]

  def GameTotalTimeValidator:ValidationRule = (data:List[DataEvent]) =>
    data.filter(x => x.time > 0 && x.time <= 60*10)

  def ScoreLimitValidator:ValidationRule = (data:List[DataEvent]) =>
    data.filter(x => x.pointsScored > 0 && x.pointsScored <=3)

  def TeamScoreValidator:ValidationRule = (data:List[DataEvent])  => {
    val t1Data = data.filter( d => d.teamThatScored == 0).sortBy( d => d.time)
    val t2Data = data.filter(d => d.teamThatScored == 1).sortBy( d => d.time)
    val validt1 = t1Data.foldLeft((List[DataEvent](),0)){
      case ((Nil,_),next) => (List(next),next.team1Total)
      case ((prev,runningScore),next) =>
        val score = runningScore + next.pointsScored
        if (score == next.team1Total) (prev:+next,score)
        else (prev,runningScore)
    }._1
    val validt2 = t2Data.foldLeft((List[DataEvent](),0)){
      case ((Nil,_),next) => (List(next),next.team2Total)
      case ((prev,runningScore),next) =>
        val score = runningScore + next.pointsScored
        if (score == next.team2Total) (prev:+next,score)
        else (prev,runningScore)
    }._1

    (validt1++validt2).sortBy( d => d.time)

  }


  def validateData(data:List[DataEvent],v:ValidationRule*) = {
    v.foldLeft(data)((old,rule) => rule(old))
  }



}

