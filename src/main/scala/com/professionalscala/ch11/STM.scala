package com.professionalscala.ch11

import java.util.concurrent.atomic.AtomicInteger

import scala.concurrent.stm.Ref
import scala.concurrent.stm._

object AtomicNotThreadSafe extends App{
  var a = new AtomicInteger()
  var b = new AtomicInteger()

  class CustomThread extends Thread {
    override def run(): Unit = {
      for (_ <- 1 to 30) {
        a.getAndIncrement()
        b.addAndGet(a.get())
      }
    }
  }

  val thread1 = new CustomThread
  val thread2 = new CustomThread

  thread1.start()
  thread2.start()

  thread1.join()
  thread2.join()

  println(a)
  println(b)
}

object ThreadSafe extends App {
  var a = Ref(0)
  var b = Ref(0)

  class CustomThread extends Thread {
    override def run(): Unit = {
      for (_ <- 1 to 30) {
        atomic{ implicit txn =>
          a() = a() + 1
          b() = b() + a()
        }
      }
    }
  }

  val thread1 = new CustomThread
  val thread2 = new CustomThread

  thread1.start()
  thread2.start()

  thread1.join()
  thread2.join()

  println(a.single())
  println(b.single())
}


object SideEffects extends App{
  var a = Ref(0)
  var b = Ref(0)

  class CustomThread extends Thread {
    override def run(): Unit = {
      for (_ <- 1 to 30) {
        atomic{ implicit txn =>
          println(s"The value of a is ${a()}")
          a() = a() + 1
          b() = b() + a()
        }
      }
    }
  }

  val thread1 = new CustomThread
  val thread2 = new CustomThread

  thread1.start()
  thread2.start()

  thread1.join()
  thread2.join()

  println(a.single())
  println(b.single())

}

object Afters extends App{
  var a = Ref(0)
  var b = Ref(0)

  class CustomThread extends Thread {
    override def run(): Unit = {
      for (_ <- 1 to 10) {
        atomic { implicit txn =>
          Txn.afterRollback { _ => println(s"Rollback!") }
          a() = a() + 1
          b() = b() + a()
          val aValue = a()
          Txn.afterCommit { _ => println(s"The value of a after commit is $aValue") }
        }
      }
    }
  }

  val thread1 = new CustomThread
  val thread2 = new CustomThread

  thread1.start()
  thread2.start()

  thread1.join()
  thread2.join()

  println(a.single())
  println(b.single())
}
