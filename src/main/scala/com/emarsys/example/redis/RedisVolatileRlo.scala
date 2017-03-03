package com.emarsys.example.redis

import akka.actor.{Actor, ActorSystem, Props}
import com.redis.RedisClient
import scala.concurrent.duration._

case class Add(key: String, value: String, index: Int)

class RedisActor extends Actor {
  import context.dispatcher

  override def receive = {
    case Add(key,value,index) =>
      addRedisTtl(key+index,value+index)
      self ! Add(key,value,index+1)
  }

  def addRedisTtl(key: String, value: String ) = {
    RedisClient.set(key,value,"nx","ex",600)
  }
}

object RedisClient extends RedisClient("localhost",6379)


object RedisVolatileRlu  extends App {

  val system = ActorSystem("test")

  val printer = system.actorOf(Props[RedisActor])

  for( idx <- 1 until 10000){
    RedisClient.set("hard"+idx,"hardvalue"+idx)
  }
  println("added hard key...")
  printer ! Add("key","value",10000)
}
