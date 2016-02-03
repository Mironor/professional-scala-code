package com.professionalscala.ch2

case class Database()

object NonLazyDatabase extends App{
  class foo{
    var database: Database = _

    def bar() = {
      database = initDb()
      // … the rest of the code that uses database here ...
    }

    def initDb() = Database()
  }
}

object LazyDatabase extends App{
  class foo{
    lazy val database = initDb()

    def bar() = {
      // … the rest of the code that uses configuration here ...
    }

    def initDb() = Database()
  }
}

object NanoTimeMeasure extends App{
  def measure[T](code: => T): String = {
    val start = System.nanoTime
    code
    val end = System.nanoTime
    s"It took ${end - start} nanoseconds to execute the code"
  }

  measure(1 + 1)
}
