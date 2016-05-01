package com.wrox.professionalscala.ch11

import java.util.concurrent.atomic.AtomicInteger

object BasicThread extends App{
  class CustomThread extends Thread {
    override def run(): Unit = {
      println("Custom thread is running.")
    }
  }
  val thread = new CustomThread

  thread.start()
  thread.join()
  println("Custom thread has joined.")
}

object ConcurrentAccess extends App{
  var a = 0

  class CustomThread extends Thread {
    override def run(): Unit = {
      a += 1
    }
  }

  val thread1 = new CustomThread
  val thread2 = new CustomThread

  thread1.start()
  thread2.start()

  thread1.join()
  thread2.join()

  println(a)
}

object Synchronized extends App{
  var a = 0
  val obj = new Object

  class CustomThread extends Thread {
    override def run(): Unit = {
      obj.synchronized {
        a += 1
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
}

object Deadlock extends App {
  var a = 0
  val obj1 = new Object
  val obj2 = new Object

  class CustomThread1 extends Thread {
    override def run(): Unit = {
      obj1.synchronized {
        obj2.synchronized {
          a += 1
        }
      }
    }
  }

  class CustomThread2 extends Thread {
    override def run(): Unit = {
      obj2.synchronized {
        obj1.synchronized {
          a += 1
        }
      }
    }
  }

  (1 to 100).foreach(i => {
    println(s"current iteration: $i")
    val thread1 = new CustomThread1
    val thread2 = new CustomThread2

    thread1.start()
    thread2.start()

    thread1.join()
    thread2.join()
  })

  println(a)
}

object Atomic extends App{
  var a = new AtomicInteger()

  class CustomThread extends Thread {
    override def run(): Unit = {
      a.getAndIncrement()
    }
  }

  val thread1 = new CustomThread
  val thread2 = new CustomThread

  thread1.start()
  thread2.start()

  thread1.join()
  thread2.join()

  println(a)
}

object Volatile extends App{
  class CustomThread extends Thread {
    @volatile var flag = true // remove volatile to break the application

    override def run(): Unit = {
      while(flag) { }
      println("Thread terminated")
    }
  }
  val thread = new CustomThread
  thread.start()

  Thread.sleep(2000)
  thread.flag = false
  println("App terminated")
}
