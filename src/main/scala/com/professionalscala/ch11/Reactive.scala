package com.professionalscala.ch11

import rx.lang.scala.Observable
import rx.lang.scala.schedulers.IOScheduler
import scala.concurrent.duration._

object HelloWorld extends App {
  println("Start")
  val observable = Observable.from(1 to 100)
  observable.subscribe(println(_))
  println("End")
}

object Asynchronous extends App {
  println("Start")
  val observable = Observable.from(1 to 100).observeOn(IOScheduler())
  observable.subscribe(println(_))
  println("End")
}

object BenchPreps extends App {
  val observable = Observable.from(1 to 1000000)
  observable.filter(_ % 2 == 0).sum.toBlocking.first

  val list = (1 to 1000000).toList
  list.filter(_ % 2 == 0).sum
}

object Intervals extends App {
  println(s"(Thread: ${Thread.currentThread().getId}) Start")

  val observable = Observable.interval(1 second).observeOn(IOScheduler()).take(5)
  observable.subscribe(x => println(s"(Thread: ${Thread.currentThread().getId}) $x"))

  Thread.sleep(6000)
  println(s"(Thread: ${Thread.currentThread().getId}) End")
}

object mergeStreams extends App {
  println(s"(Thread: ${Thread.currentThread().getId}) Start")
  val observable1 = Observable.interval(1 second).observeOn(IOScheduler()).take(3)
  val observable2 = Observable.interval(700 millis).observeOn(IOScheduler()).take(4)
  val observable3 = Observable.interval(300 millis).observeOn(IOScheduler()).take(6)

  val mainObservable = Observable.from(Array(observable1, observable2, observable3)).flatten
  mainObservable.subscribe(x => println(s"(Thread: ${Thread.currentThread().getId}) $x"))

  Thread.sleep(4000)
  println(s"(Thread: ${Thread.currentThread().getId}) End")
}


