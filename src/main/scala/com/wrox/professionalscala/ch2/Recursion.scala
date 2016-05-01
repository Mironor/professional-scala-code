package com.wrox.professionalscala.ch2

import scala.annotation.tailrec

object While extends App{
  var flag = true

  while (flag){
    println(flag)
    flag = false
  }
}

object RecursiveWhile extends App{
  def whileFunc[T](block: => T, condition: => Boolean): Unit = {
    if (condition) {
      block
      whileFunc(block, condition)
    }
  }

  // and an example of usage:
  var i = 0

  whileFunc({
    println(i)
    i = i + 1
  }, i < 10)
}

object ClassicFactorial extends App{
  def fact(n: Int): Int = {
    if (n < 1) 1
    else n * fact(n - 1)
  }

  println(fact(6))
}

object TailrecFactorial extends App{
  @tailrec
  def fact(n: Int, acc: Int = 1): Int = {
    if (n < 1) acc
    else fact(n - 1, acc * n)
  }

  println(fact(6))
}

object RandomIdIterative extends App{
  val ids = List(0, 3, 4, 7, 9) // generally those Ids are stored in the database
  var generatedId = scala.util.Random.nextInt(10)

  while (ids.contains(generatedId)){
    generatedId = scala.util.Random.nextInt(10)
  }

  println(generatedId)
}

object RandomIdRecursive extends App {
  val ids = List(0, 3, 4, 7, 9) // generally those Ids are stored in the database

  @tailrec
  def generateId(currentIds: List[Int]): Int = {
    val generatedId = scala.util.Random.nextInt(10)

    if (currentIds.contains(generatedId)) generateId(currentIds)
    else generatedId
  }

  println(generateId(ids))
}

object Fibo extends App{
  def fibo( n : Int): Int = {
    if (n < 2) n
    else fibo(n-1) + fibo(n-2)
  }

  println(fibo(13))
}

object FiboTailrec extends App{
  @tailrec
  def fibo(n: Int, a: Int = 0, b: Int = 1): Int = {
    if (n < 1) a
    else fibo(n-1, b, a+b)
  }

  println(fibo(13))
}

object FiboWhile extends App{
  def fiboWhile( n : Int ) : Int = {
    var first = 0
    var second = 1
    var i = 0

    while( i < n ) {
      val result = first + second
      first = second
      second = result
      i = i + 1
    }
    first
  }

  println(fiboWhile(13))
}



























