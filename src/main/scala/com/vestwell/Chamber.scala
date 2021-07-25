package com.vestwell

sealed abstract case class Chamber private(value: String) {

  def animate(step: Int): String = {
    require(step > 0 && step < 11)
    val particles = value.zipWithIndex.filter(x => Movement.dirChar.contains(x._1)).map(x => Particle(x._1, x._2))
    val arr = Array.fill(value.length)('.')
    for (ele <- particles) yield {
      arr(ele.pos) = 'X'
    }
    val result = LazyList.iterate((arr.mkString, particles)) { case (_, particles) => {
      val newParticles = particles.map { p =>
        p match {
          case Particle(chr, pos) if chr == 'R' => p.copy(pos = pos + step)
          case Particle(chr, pos) if chr == 'L' => p.copy(pos = pos - step)
          case _ => p
        }
      }
      val particlesAfterMoved = newParticles.filterNot { p => p.pos > value.length - 1 || p.pos < 0 }
      val arr = Array.fill(value.length)('.')
      for (ele <- particlesAfterMoved) yield {
        arr(ele.pos) = 'X'
      }
      val newChamber = arr.mkString
      (newChamber, particlesAfterMoved)
    }
    }.takeWhile(t => !t._2.isEmpty).map(t => t._1).toList :+ "." * value.length
    result.mkString("\n")
  }
}

object Chamber {

  //this is the Smart Constructors
  def fromString(value: String): Either[String, Chamber] = {
    if (checkChamber(value)) Right(new Chamber(value) {})
    else Left(s"Invalid init argument: $value")
  }

  private[this] def checkChamber(str: String): Boolean =
    str.matches("""[.LR]{1,50}$""")
}


