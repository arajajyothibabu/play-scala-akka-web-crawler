package controllers

import java.net.URL

import actors.Crawler
import akka.actor.{ActorSystem, PoisonPill, Props}
import play.api._
import play.api.mvc._
import utils.Start

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.language.postfixOps

class Application extends Controller {

  val system = ActorSystem()

  val crawler = system.actorOf(Props(new Crawler(system)))

  crawler ! Start(new URL("https://vach.in"))

  //Await.result(system.whenTerminated, 10 minutes)

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def killActor = Action.async {
    Future {
      crawler ! PoisonPill
      system.terminate
      Ok("true")
    }
  }

}