/**
  * Created by pritesh on 21/02/16.
  */
object Main {

  def main(args: Array[String]) {

    println ("Running through all valid data in sample1 data stream:")

    DataMaintainer.getAllValidDataEvents("data/sample1.txt").foreach{
      x => println(x)
    }

    println ("Running through all valid data in sample 2 data stream:")

    DataMaintainer.getAllValidDataEvents("/home/pritesh/IdeaProjects/game-data-stream/data/sample2.txt").foreach{
      x => println(x)
    }

  }

}
