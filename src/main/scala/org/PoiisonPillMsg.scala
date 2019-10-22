package org

import akka.actor._

class TestActor extends Actor {
  override def preRestart(reason: Throwable, message: Option[Any]): Unit = {
    super.preRestart(reason,message) // stops all children, calls postStop( ) for crashing actor
    println(s"actor restarting...")
  }
  override def postRestart(reason: Throwable): Unit = println(s"actor restarted...")

  def receive = {
    case s:String => println("Message Received: " + s)
    case _ => println("TestActor got an unknown message")
  }
  override def postStop { println("TestActor::postStop called") }
}

object PoisonPillTest extends App {
  val system = ActorSystem("PoisonPillTest")
  val actor = system.actorOf(Props[TestActor], name = "test")

  // a simple message
  actor ! "before PoisonPill"

  // the PoisonPill
  actor ! PoisonPill

  // these messages will not be processed
  actor ! "after PoisonPill"
  actor ! "hello?!"

  system.terminate()
}