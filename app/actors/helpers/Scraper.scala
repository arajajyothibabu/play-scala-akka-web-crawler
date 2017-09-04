package actors.helpers

import java.net.URL

import actors.Crawler
import akka.actor.{Actor, ActorRef}
import org.jsoup.Jsoup
import utils.{Content, Index, Scrape}
import scala.collection.JavaConverters._

/**
  * Created by jyothi on 31/8/17.
  */
class Scraper(crawler: ActorRef) extends Actor {

  override def receive: Receive = {

    case Scrape(url) => {
      println("Scraping started for URL: ", url)
      crawler ! Index(url, parse(url))
      println("Scraping ended for URL: ", url)
    }

  }

  @throws[Exception](classOf[Exception])
  override def postStop(): Unit = {
    super.postStop()
    //TODO: store the map
  }

  def parse(url: URL): Content = {
    val link: String = url.toString
    val root = url.getProtocol + "://" + url.getHost
    val response = Jsoup.connect(link).ignoreContentType(true)
      .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1").execute()

    val contentType: String = response.contentType
    if (contentType.startsWith("text/html")) {
      val doc = response.parse()
      val title: String = doc.getElementsByTag("title").first().text()
      val description = doc.getElementsByAttributeValue("name", "description").attr("content")
      val body = doc.getElementsByTag("body").html()
      val links = doc.getElementsByTag("a").asScala.map(el => new URL(root + el.attr("href"))).toList
      Content(title, description, body, links)
    } else {
      Content(link, contentType, "", List())
    }
  }

}
