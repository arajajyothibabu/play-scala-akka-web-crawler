package actors

import java.net.URL

import actors.helpers.{Indexer, Scraper}
import akka.actor.{Actor, ActorRef, Props, _}
import utils.{Content, Index, Scrape, Start}

/**
  * Created by jyothi on 31/8/17.
  */
class Crawler(system: ActorSystem) extends Actor{

  val indexer = context.actorOf(Props(new Indexer(self)))

  override def receive: Receive = {

    case Start(url) => {
      val scraper = context.actorOf(Props(new Scraper(self)))
      scraper ! Scrape(url)
    }

    case Index(url, content) => {
      println(s"indexing $url with title: ${content.title}")
      indexer ! Index(url, content)
    }

  }

}
