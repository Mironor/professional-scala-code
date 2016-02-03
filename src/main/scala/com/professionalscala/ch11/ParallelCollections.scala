package com.professionalscala.ch11

import scala.util.Random

object FilterEven extends App{
  (1 to 10000000).filter(_ % 2 == 0)
  (1 to 10000000).par.filter(_ % 2 == 0)
}

object NanotimeMeasurements extends App{
  var start = 0L

  for (i <- 1 to 10){
    start = System.nanoTime
    (1 to 10000000).par.filter(_ % 2 == 0)
    println(((System.nanoTime - start) / 1000000) + " milliseconds")
  }
}

object ListMax extends App{
  val list = Random.shuffle(Vector.tabulate(5000000)(i => i)).toList

  val max = list.max
  val maxPar = list.par.max
}

object VectorMax extends App{
  val vector = Random.shuffle(Vector.tabulate(5000000)(i => i))

  val max = vector.max
  val maxPar = vector.par.max
}

object SideEffect extends App{
  var a = 0

  (1 to 100).par.foreach(_ => a += 1)

  println(a)
}

