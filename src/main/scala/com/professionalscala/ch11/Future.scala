package com.professionalscala.ch11



import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future, blocking}
import scala.util.{Failure, Success}
import scala.async.Async.{async, await}

object FutureHelloWorld{
  Future {
    print("World ")
  }

  print("Hello ")
}

object FutureUnpredictable extends App{
  for(i <- 1 to 30){
    Future{
      print("World ")
    }

    print("Hello ")
  }
}

object Blocking extends App{
  for (i <- 1 to 30){
    Future{
      blocking {
        Thread.sleep(10000)
        println("Done")
      }
    }
  }

  Thread.sleep(11000)
}

object Dao{
  def findUserById(id: Int): Future[User] = Future {
    User("Alex", 26)
  }
}

object Callback extends App {
  Dao.findUserById(1).onComplete {
    case Success(user) => println(s"User's name is ${user.name}")
    case Failure(ex) => println("An error has occurred!")
  }
  Thread.sleep(1000)
}

object UserService {
  def retrieveUserNameById(id: Int): Future[String] =
    Dao.findUserById(id).map { user =>
      user.name
    }
}

object FutureTransformation extends App{
  UserService.retrieveUserNameById(1).onComplete {
    case Success(name) => println(s"Hello $name !")
    case Failure(ex) => println("An error has occurred!")
  }

  UserService.retrieveUserNameById(1).foreach { name  =>
    println(s"Hello $name !")
  }

  Thread.sleep(1000)
}

object Filter extends App{
  Dao.findUserById(1).filter(_.age > 18) // Successful, with the user
  Dao.findUserById(1).filter(_.name == "Sam") // Failed Future
}

object FutureAwait extends App{
  val userName = Await.result(UserService.retrieveUserNameById(1), 1 second)
}

object SumWithoutAsync extends App {
  def calculate: Future[Int] = {
    val future1 = Future(1 + 2)
    val future2 = Future(3 + 4)
    for {
      result1 <- future1
      result2 <- future2
    } yield result1 + result2
  }
}

object SumWithAsync extends App {
  def calculate: Future[Int] = async {
    val future1 = Future(1 + 2)
    val future2 = Future(3 + 4)

    await(future1) + await(future2)
  }
}
