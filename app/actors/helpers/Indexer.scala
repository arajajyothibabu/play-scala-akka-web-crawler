package actors.helpers

import java.net.URL

import akka.actor.Actor
import utils.{Content, Index}

/**
  * Created by jyothi on 31/8/17.
  */
class Indexer extends Actor {

  var indexMap = Map.empty[URL, Content]

  override def receive: Receive = {
    case Index(url, content) => {
      indexMap += (url -> content)
    }
  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    //TODO: store the map
  }

}
