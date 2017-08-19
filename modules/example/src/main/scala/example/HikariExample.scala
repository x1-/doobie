package doobie.example

import cats.effect.IO
import doobie.imports._
import doobie.hikari.imports._

object HikariExample {

  def tmain: IO[Unit] =
    for {
      xa <- HikariTransactor[IO]("org.h2.Driver", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "")
      _  <- FreeUsage.examples.transact(xa)
      _  <- xa.shutdown
    } yield ()

  def main(args: Array[String]): Unit =
    tmain.unsafeRunSync

}