package com.wrox.professionalscala.ch02

object SwapMutable extends App{
  var a = 1
  var b = 2
  // … other code here, but something happens, we need to swap
  var c = a // a = 1 b = 2
  a = b // a = 2 b = 2
  b = c // a = 2 b = 1
}

object SwapImmutable extends App{
  val a = 1
  val b = 2
  // something happens, we need to swap
  val c = a
  val d = b
}

object UsersMutable extends App{
  val dao = new UserDao

  var users = dao.findAll()
  // … some code that uses `users` variable so that the next line cannot be directly chained ...
  users = users.filter(_.age >= 18)
  // reusing the same `users` variable through the rest of the method
}

object UsersImmutable extends App{
  val dao = new UserDao

  val users = dao.findAll()
  // … some code that uses `users` variable so that the next line cannot be directly chained ...
  val adults = users.filter(_.age >= 18)
  // reusing `adults` that stays the same during the rest of the method
}

object MutableVal extends App{
  val string = new StringBuilder()
  string.append("ab")
  println(string) // "ab"
  string.append("cd")
  println(string) // "abcd"
}
