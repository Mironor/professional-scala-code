package com.wrox.professionalscala

package object ch2 {
  case class User(name: String, age: Int)

  class UserDao{
    def findAll() = List(User("Alex", 26), User("Sam", 24))
    def findUserById(id: Int): Option[User] = Some(User("Alex", 26))
  }

}
