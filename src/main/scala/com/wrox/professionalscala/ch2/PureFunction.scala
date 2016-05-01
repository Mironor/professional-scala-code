package com.wrox.professionalscala.ch2

object HelloWorldNotPure extends App{
  def helloWorld(): Unit = {
    println("Hello")
    println("World")
  }
  helloWorld()
}

object PureFunction extends App{
  def add(a: Int, b: Int) = a + b
  add(1, 2)
}

object DatabaseNotPure extends App{
  def extractAdultUserNames(): List[String] = {
    val dao = new UserDao
    val users = dao.findAll()
    users.filter(_.age >= 18).map(_.name)
  }
}

object DatabasePure extends App{
  def extractAdultUserNames(users: List[User]): List[String] =
    users.filter(_.age >= 18).map(_.name)

  val dao = new UserDao
  val users = dao.findAll()
  extractAdultUserNames(users)
}
