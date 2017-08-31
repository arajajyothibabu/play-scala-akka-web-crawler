package actors.helpers

import java.net.URL

import actors.Crawler
import akka.actor.{Actor, ActorRef}
import org.jsoup.Jsoup
import utils.{Content, Scrape}

/**
  * Created by jyothi on 31/8/17.
  */
class Scraper(crawler: ActorRef) extends Actor {

  override def receive: Receive = {

    case Scrape(url) => {

    }

  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    //TODO: store the map
  }

  def parse(url: URL): Content = {
    val link: String = url.toString
    val response = Jsoup.connect(link).ignoreContentType(true)
      .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1").execute()

    val contentType: String = response.contentType
    if (contentType.startsWith("text/html")) {
      val doc = response.parse()
      val title: String = doc.getElementsByTag("title").first().toString
      val descriptionTag = doc.getElementsByTag("meta")
      val body = doc.getElementsByTag("body").first().toString
      val description = if (descriptionTag.isEmpty) "" else descriptionTag.attr("content")
      val links: List[URL] = List.empty
      Content(title, description, body, links)
    } else {
      Content(link, contentType, "", List())
    }
  }

}
