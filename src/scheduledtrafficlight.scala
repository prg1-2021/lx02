import sgeometry.Pos
import sdraw.{World, Color, Red, Green, Yellow}

case class ScheduledLight(color: Color, waitFor: Int) extends World() {

  val SECS_FOR_RED    = 7
  val SECS_FOR_GREEN  = 5
  val SECS_FOR_YELLOW = 2

  /**
   * 信号を描画する。円を塗り潰しているだけ。
   **/
  def draw(): Boolean = {
    canvas.drawDisk(Pos(150, 150), 120, color)
    true
  }

  /**
   * 信号の状態の遷移
   **/
  def tick(): World = {
    (waitFor, color) match {
      case (0, Red) => ScheduledLight(Green, SECS_FOR_GREEN)
      case (0, Green)  => ScheduledLight(Yellow, SECS_FOR_YELLOW)
      case (0, Yellow) => ScheduledLight(Red, SECS_FOR_RED)
      case _ => ScheduledLight(color, waitFor -1)
    }
  }

  /**
   * clickとkeyEventはそれぞれマウスクリックとキーボード入力に対応するための関数だが、
   * この例では積極的には用いない。
   **/
  def click(p: Pos):         World = { ScheduledLight(color, waitFor) }
  def keyEvent(key: String): World = { ScheduledLight(color, waitFor) }
}

object ScheduledTrafficLightApp extends App {
  ScheduledLight(Red, 7).bigBang(300, 300, 1)
}
