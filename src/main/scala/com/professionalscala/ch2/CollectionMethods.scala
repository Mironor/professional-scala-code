package com.professionalscala.ch2

object ForIteration extends App {
  val users = List(User("Alex", 26), User("Sam", 24))

  var names = List[String]()

  for (user <- users) {
    names = names :+ user.name
  }

  println(users)
}

object MapOverUsers extends App {
  val users = List(User("Alex", 26), User("Sam", 24))

  var names = users.map(user => user.name)
  // or, same as above
  names = users.map(_.name)
}

object ForFilter extends App {
  val users = List(User("Alex", 12), User("Sam", 22))

  var adults = List[User]()

  for (user <- users) {
    if (user.age >= 18) {
      adults = adults :+ user
    }
  }

  println(users)
}

object FilterOverUsers extends App {
  val users = List(User("Alex", 12), User("Sam", 22))
  val adults = users.filter(_.age >= 18)
}

object FlatMap extends App {
  val shakespeare = List(
    "Words are easy",
    "like the wind",
    "faithful friends",
    "are hard to find"
  )

  val words: List[String] = shakespeare.flatMap(line => line.split(" "))
}

object Flatten extends App {
  val shakespeare = List(
    "Words are easy",
    "like the wind",
    "faithful friends",
    "are hard to find"
  )

  val wordsNotFlattened: List[Array[String]] = shakespeare.map(_.split(" "))
  val words: List[String] = wordsNotFlattened.flatten
}

object Distinct extends App {
  val listWithRepetitions = List("Alex", "Alex", "Sam")

  println(listWithRepetitions.distinct) // prints List(Alex, Sam)
  println(listWithRepetitions.toSet) // prints Set(Alex, Sam)
}

object GroupBy extends App {

  case class User(identifier: String, likes: String)

  val users = List(User("Alex", "Kiwi"), User("Sam", "Banana"), User("Alex", "Apple"))
  val likesByUser = users.groupBy(_.identifier)

  /* likesByUser contains:
  Map(
    Alex -> List(User2(Alex,Kiwi), User2(Alex,Apple)),
    Sam -> List(User2(Sam,Banana))
  )*/
}

object Partition extends App {
  val (moreThanTwo, lessOrEqualThanTwo) = List(1, 2, 3, 4).partition(_ > 2)
  println(moreThanTwo)
  println(lessOrEqualThanTwo)
}

object Sort extends App {
  List(3, 4, 1, 2).sorted

  val users = List(User("Alex", 26), User("Sam", 24), User("David", 25))

  users.sortBy(_.age)
  users.sortWith((a, b) => a.age < b.age) // same as sortBy example, but more flexible
}

object Reverse extends App {
  List(3, 4, 1, 2).sorted.reverse // returns List(4, 3, 2, 1)
}

object Foreach extends App {
  List(1, 2, 3).foreach(println)
}

object BooleanMethods extends App {
  List(1, 2, 3).contains(1) // returns true
  List(1, 3, 5).exists(_ % 2 == 0) // returns false
  List(2, 4, 6).forall(_ % 2 == 0) // returns true
}

object Find extends App{
  List(1, 2, 4, 6).find(_ % 2 == 0) // returns Some(2)
}

object Count extends App{
  List(1, 2, 4, 6).count(_ % 2 == 0) // returns 3
}

object String extends App{
  List(1, 2, 4, 6).mkString("|") // returns 1|2|4|6
}

object MoreFunctional extends App{
  List(1, 2, 3).reduce(_ / _)
  List(1, 2, 3).fold(1)(_ / _)
}

object ForVsMap extends App{
  val listX = List(1, 2, 3)
  val listY = List(1, 2, 3)

  for {
    x <- listX
    y <- listY
  } yield (x, y)

  listX.flatMap(x => listY.map(y => (x, y)))
}




































