/**
  * Created by pritesh on 19/02/16.
  */
case class DataEvent(
                      pointsScored :Int,
                      teamThatScored:Int,
                      team1Total:Int,
                      team2Total:Int,
                      time:Int
                    ){
  override def toString() = {
    val team = if (teamThatScored == 0) 1 else 2
    val timeFormat:BigDecimal = BigDecimal(time).setScale(2) / BigDecimal(60).setScale(2)
    s"Team ${team} scored: ${pointsScored}. Team 1 total score: ${team1Total} Team 2 total score: ${team2Total}." +
      s" Time in game: ${timeFormat.setScale(5,BigDecimal.RoundingMode.UP)}"
  }
}
