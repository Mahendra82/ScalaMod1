package org

import akka.actor.{Actor, ActorSystem, Props, ReceiveTimeout}

import scala.language.postfixOps
import scala.concurrent.duration._
import scala.io.StdIn

object MyActor {

  val arr = List.range(1, 11)//[1,2,3,4]
  def props: Props =
    Props(new MyActor(arr:List[Int]))
}
class MyActor(arr: List[Int]) extends Actor {
  // To set an initial delay
   context.setReceiveTimeout(1 milliseconds)
  def receive = {

    case "Sum" =>
      val sum=0
      // To set in a response to a message
     // val secondRef = context.actorOf(Props.empty, "second-actor")
      //println(s"Second: $secondRef")
      println("Summing actor!!!")
      println(arr.sum)
    case "FilterEven" =>
      val res1=arr.filter(x=>x%2==0)
      res1.foreach(res11=>println(res11))
    case Nil =>
      // To turn it off
      context.setReceiveTimeout(Duration.Undefined)
      println("Empty list not allowed!!!")
  }
}

//---list[list] ll
//---l1=[1,2,3,4]
//---l2=[5,6,7,8]
// ll.add(l1)
// ll.add(l2)
// rl= ll.flatMap
// rl[1,2,3,4,5,6,7,8]
object  Fun1 {
  def pfTest(): Unit ={
    println("partial function begin.....");
    def taxRate(s:String) :Double =  s match{
      case "EUR" => 9.1
      case "USD" =>7.3
      case _=>3.4
    }
    val cPf1:PartialFunction[CropInfo,Double]={case i if i.currency=="EUR" => i.price+(i.price*taxRate(i.currency)/100)}
    val cPf2:PartialFunction[CropInfo,Double]={case i if i.currency=="USD" => i.price+(i.price*taxRate(i.currency)/100)+i.price*0.01}
    val cPf3:PartialFunction[CropInfo,Double]={case i if i.currency=="INR" => i.price+(i.price*taxRate(i.currency)/100)+i.price*0.02}

    val tpf= cPf1 orElse cPf2 orElse cPf3
    case class CropInfo(id:Int, name:String, currency:String, price:Double)
    val c1=new CropInfo(1,"Corn","USD",127.58)
    val c2=new CropInfo(2,"Wheat","USD",177.52)
    val c3=new CropInfo(3,"Soya","USD",721.62)
    val c4=new CropInfo(4,"Almond","USD",621.55)
    val c5=new CropInfo(5,"Olive","EUR",541.82)
    var cList= List(c1,c2,c3,c4,c5)
    cList.foreach(x=>println("Crop "+x.name+" Price:"+x.price+" total price (incl tax "+taxRate(x.currency)+"%): "+f"${tpf(x)}%.2f"))
    println("partial function end");
  }
  def main(args : Array[String]) : Unit =
  {
  pfTest()
   /* val programmers = Array(
      new Person("Ted", "Neward", 37, 50000,
        Array("C++", "Java", "Scala", "Groovy", "C#", "F#", "Ruby")),
      new Person("Amanda", "Laucher", 27, 45000,
        Array("C#", "F#", "Java", "Scala")),
      new Person("Luke", "Hoban", 32, 45000,
        Array("C#", "Visual Basic", "F#")),
      new Person("Scott", "Davis", 40, 50000,
        Array("Java", "Groovy"))
    )
    val f1:(Int , Int)=>Int = _ * _ *2
    println(f1(3,4))

    val x = List.range(1,10)
    var sum=0
    //isSorted(Array(1, 3, 5, 7), (x: Int, y: Int) => x < y)
    val system = ActorSystem("testSystem")
    val firstRef = system.actorOf(MyActor.props, "first-actor")
    println(s"First: $firstRef")
    firstRef ! "Sum"
    firstRef ! "FilterEven"
    // Find all the Scala programmers ...
    val scalaProgs =
      programmers.filter((p) => p.skills.contains("Scala") )
    scalaProgs.foreach((p1)=>(print( p1.skills.foreach(x1=>print(x1+" ")))))
    println("\n>>> Press ENTER to exit <<<")
    try StdIn.readLine()
    finally system.terminate()
    x.foreach(sum += _);
    print(sum)
   var names=Seq("a","b","c","a1")
    var cNames=names.map(x1=>x1.toUpperCase)
    cNames.foreach(x=>println(x))
    val x6 = x flatMap { xi => List(xi - 0.1, xi + 0.1) }

    val numList1 = List(1,6,5)
    val numList2 = List(3,4)
    val res=numList1 flatMap { x => numList2 map {
      y => x + y
    }
    }
    res.foreach(x2=>println(x2));*/

  }


}

case class Person(value: Any, value1: Any, value2: Int, value3: Int, skills: Array[String])
