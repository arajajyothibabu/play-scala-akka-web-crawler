package actors.helpers

import java.net.URL

import akka.actor.{Actor, ActorRef, PoisonPill}
import utils.{Content, Index, Start}

/**
  * Created by jyothi on 31/8/17.
  */
class Indexer(crawler: ActorRef) extends Actor {

  var indexMap = Map.empty[URL, Content]

  override def receive: Receive = {

    case Index(url, content) => {
      if (!indexMap.contains(url)) {
        indexMap += (url -> content)
        println(indexMap.keys.size, "Indexed the URL: ", url)
        printIndexedContent(url)
      }else{
        println(url, "URL already indexed")
      }
    }

  }

  def printIndexedContent(url: URL) = {
    val content = indexMap.apply(url)
    println("Title ", content.title)
    //println("Meta ", content.meta)
    //println("Body ", content.body)
    //println("Urls ", content.urls.mkString(", "))
  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    println(indexMap.values.mkString("-"))
    //TODO: store the map
  }

}
