import scala.math.{max, min}

import sgeometry.Pos
import sdraw.{World, Blue, White, Green}

case class PotonWorld(centerX: Int, centerY: Int) extends World() {
  val A = PotonApp

  val BallRadius = A.BallRadius                    // ボールの半径
  val MinX = BallRadius + 10                       // X座標の最小値（これ以上左に寄ると左の壁に衝突）
  lazy val MaxX = A.WorldWidth  - BallRadius - 10  // X座標の最大値（これ以上、右に寄ると右の壁に衝突）
  lazy val MaxY = A.WorldHeight - BallRadius * 2   // Y座標の最大値（これ以上、落ちると画面から消える）
  val HoleX = 200                                  // 穴の中心のX座標

  def draw(): Boolean = {
    // 画面消去
    canvas.drawRect(Pos(0, 0), canvas.width, canvas.height, White) &&
    // 左方の草原の描画
    canvas.drawRect(Pos(10, MaxY - 10), HoleX - 10, 20, Green) &&
    // 右方の草原の描画
    canvas.drawRect(
      Pos(HoleX + (BallRadius * 2 + 10), MaxY - 10),   // 矩形の位置: Pos(x, y)
      canvas.width - (HoleX + (BallRadius * 2 + 10)),  // 矩形の幅
      20,                                              // 矩形の高さ
      Green                                            // 塗り潰し色
    ) &&
    // ボール
    canvas.drawDisk(Pos(centerX, centerY), 50, Blue)
  }

  def click(p: Pos): World = this

  def keyEvent(key: String): World = {
    println(key)
    key match {
      case "SPACE"       => PotonWorld(centerX, MaxY)
      case "LEFT" | "h"  => PotonWorld(max(MinX, centerX - 5), centerY)
      case "RIGHT" | "l" => PotonWorld(min(centerX + 5, MaxX), centerY)
      case _             => this
    }
  }

  def tick(): World = {
    PotonWorld(centerX, min(centerY + 5, MaxY))
  }
}

// Run this app from sbt: [project lxz; runMain poton.A]
object PotonApp extends App {
  val BallRadius = 50
  val WorldWidth = 800
  val WorldHeight = 600

  val world = PotonWorld(WorldWidth / 2, BallRadius * 2)
  world.bigBang(WorldWidth, WorldHeight, 0.1)
}
