package com.wrox.professionalscala.ch2

object ServerWithRepetitions extends App {
  val authenticatedUsers = List("Alex", "Sam")

  // example url /hello/{userName}
  def hello(userName: String): String = {
    if (authenticatedUsers.contains(userName)) s"world $userName"
    else "Unauthorized access"
  }

  // example url /foo/{userName}
  def foo(userName: String): String = {
    if (authenticatedUsers.contains(userName)) s"bar $userName"
    else "Unauthorized access"
  }

  println(s"request to /hello/Alex: ${hello("Alex")}")
  println(s"request to /hello/David: ${hello("David")}")
  println(s"request to /foo/Alex: ${foo("Alex")}")
}

object ServerWithoutRepetitions extends App {
  def userAwareAction(userName: String, authUsers: List[String], f: String => String): String = {
    if (authUsers.contains(userName)) f(userName)
    else "Unauthorized access"
  }

  val authenticatedUsers = List("Alex", "Sam")

  def hello(userName: String): String = userAwareAction(userName, authenticatedUsers, userName => s"world $userName")

  def foo(userName: String): String = userAwareAction(userName, authenticatedUsers, userName => s"bar $userName")
}