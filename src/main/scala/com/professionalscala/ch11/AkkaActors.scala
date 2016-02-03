package com.professionalscala.ch11

import akka.actor.{ActorSystem, Props, Actor, ActorLogging}

import scala.concurrent.Await
import scala.concurrent.duration._


class CountingActor extends Actor with ActorLogging {

  var counter = 0

  def receive = {
    case "+1" =>
      counter += 1
      log.info(s"Current count is $counter")
      if (counter == 3) context.system.terminate()
  }
}

object AkkaCounter extends App {
  val system = ActorSystem("MyActorSystem")
  val countingActor = system.actorOf(Props[CountingActor], "pingActor")
  countingActor ! "+1"
  countingActor ! "+1"
  countingActor ! "+1"
  Await.result(system.whenTerminated, 10 seconds)
}

