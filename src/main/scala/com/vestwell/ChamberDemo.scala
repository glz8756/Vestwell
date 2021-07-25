package com.vestwell

object ChamberDemo extends App {
  val chamber0 = Chamber.fromString("..R....").fold(err => println(err), x => x.animate(2))
  val chamber1 = Chamber.fromString("RR..LRL").fold(err => println(err), x => x.animate(3))
  val chamber2 = Chamber.fromString("LRLR.LRLR").fold(err => println(err), x => x.animate(2))
  val chamber3 = Chamber.fromString("RLRLRLRLRL").fold(err => println(err), x => x.animate(10))
  val chamber4 = Chamber.fromString("...").fold(err => println(err), x => x.animate(1))
  val chamber5 = Chamber.fromString("LRRL.LR.LRR.R.LRRL.").fold(err => println(err), x => x.animate(1))


  println(chamber0)
  println(chamber1)
  println(chamber2)
  println(chamber3)
  println(chamber4)
  println(chamber5)


}
