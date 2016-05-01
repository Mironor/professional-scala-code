package com.wrox.professionalscala.ch02

object AddPartial extends App {
  def add(a: Int, b: Int) = a + b

  def add5 = add(5, _: Int)

  def add7 = add(7, _: Int)

  add5(3) // returns 8
  add7(2) // returns 9
}

object AddCurry extends App {

  def add(a: Int)(b: Int) = a + b

  def add5 = add(5) _

  def add7 = add(7) _

  add5(3) // returns 8
  add7(2) // returns 9
}

object FileRead extends App {
  def using[T](fileName: String)(f: List[String] => T): T = {
    val file = scala.io.Source.fromFile("file.txt").getLines().toList
    f(file)
  }

  val words = using("Hello.txt") { lines =>
    lines.flatMap(_.split(" "))
  }
}

class Config {
  val userNameSuffix = "user_"
}

object TransformUserNonCurry extends App {
  val users = List(User("Alex", 26), User("Sam", 24))
  val config = new Config

  def transform(config: Config, user: User): User = {
    user.copy(name = s"${user.name} ${config.userNameSuffix}")
  }

  val transformedUsers = users.map(user => transform(config, user))
}

object TransformUserCurry extends App {
  val users = List(User("Alex", 26), User("Sam", 24))
  val config = new Config

  def transform(config: Config)(user: User): User = {
    user.copy(name = user.name + config.userNameSuffix)
  }

  val transformedUsers = users.map(transform(config))
}

object PartialFunctionWithMap extends App {
  val usersWithId = Map(1 -> User("Alex", 27), 2 -> User("Sam", 23))
  val users = usersWithId.map { tup => s"${tup._1}: ${tup._2.name}" } // extracting user's name prepended with her id

  val test = usersWithId.map { user => user match {
      case (id, value) => s"$id ${value.name}"
    }
  }

  val test2 = usersWithId.map {
    case (id, user) => user.name
  }
}

































