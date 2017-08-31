package utils

import java.net.URL

/**
  * Created by jyothi on 31/8/17.
  */
case class Start(url: URL)
case class Scrape(url: URL)
case class Index(url: URL, content: Content)
case class Content(title: String, meta: String, body: String, urls: List[URL])
case class ScrapeFinished(url: URL)
case class IndexFinished(url: URL, urls: List[URL])
case class ScrapeFailure(url: URL, reason: Throwable)