package com.wrox.professionalscala.ch03

import com.wrox.professionalscala.ch03.java.{CompatibilityClass, CompatibilityInterface}

object CompatibilityJavaInterface extends App {
  class ScalaClass extends CompatibilityInterface {
    override def print() = println("lorem ipsum")
  }

  (new ScalaClass).print()
}

object CompatibilityJavaClass extends App {
  class ScalaClass extends CompatibilityClass

  (new ScalaClass).print()
}

trait ScalaBaseTrait{
  def print(): Unit
}

trait ScalaTrait extends ScalaBaseTrait{
  override def print() = println("lorem ipsum")
}

trait ScalaTraitBis extends ScalaBaseTrait{
  def say() = println("say Hello!")
  override def print() = println("lorem ipsum two")
}

class ScalaTwoTraitsClass extends ScalaTrait with ScalaTraitBis

class ScalaObject {
  def say() = println("Hello")
}

object ScalaObject{
  def print() = println("World!")
}
