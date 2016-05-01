package com.wrox.professionalscala.ch02


object OptionMatch extends App{
  val dao = new UserDao

  dao.findUserById(10) match {
    case Some(user) => user.name
    case None => "anonymous"
  }

  // or
  val user = dao.findUserById(10).getOrElse(User("anonymous", 0))

}

object OptionMap extends App{
  val dao = new UserDao
  val userName = dao.findUserById(10).map(_.name)
}
