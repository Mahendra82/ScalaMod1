package com.example

import java.util.Date

import akka.actor.{Actor, ActorSystem, Props}

import scala.io.StdIn

object DatePrinter {
  def props: Props =
    Props(new PrintMyActorRefActor)
}

object PrintMyActorRefActor {
  def props: Props =
    Props(new PrintMyActorRefActor)
}

class PrintMyActorRefActor extends Actor {
  override def preStart(): Unit = println("1 started")
  override def postStop(): Unit = println("1 stopped")

  override def receive: Receive = {
    case "ref1" =>
      val secondRef = context.actorOf(DatePrinter.props, "second-actor")
      println(s"Second: $secondRef")
      secondRef ! "ref2"
    case "ref2" =>
       println(""+new Date())
  }
}

object ActorHierarchyExperiments extends App {
  val system = ActorSystem("testSystem")

  val firstRef = system.actorOf(PrintMyActorRefActor.props, "first-actor")
  println(s"First: $firstRef")
  firstRef ! "ref1"

  println("Test...")
  try StdIn.readLine()
  finally system.terminate()
}