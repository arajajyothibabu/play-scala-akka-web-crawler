package actors

import akka.actor.{Actor, ActorRef, Props, _}

/**
  * Created by jyothi on 31/8/17.
  */
class Crawler(system: ActorSystem) extends Actor{

  override def receive: Receive = {
    case (x, y) => {

    }
  }

}
