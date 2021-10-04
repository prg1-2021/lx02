# lx02 の例題

## 交通信号の例: `trafficlight.scala`

### sbt からの実行方法

    runMain TrafficLightApp

Scala のプログラムを眺めて、その実行方法を知る方法：

1. `object` 定義を探す
1. そのオブジェクトに適切な`main`関数を定義していたら、`runMain そのオブジェクト名`
1. そのオブジェクトが`extends App`様式で定義されていたら、`runMain そのオブジェクト名`

`trafficlight.scala`を眺めると、`TrafficLightApp`オブジェクトが`extends App`様式で定義されています。このため、このプログラムは`runMain TrafficLightApp`を実行すればよいことがわかります。

## タイマーの例 (`timer1.scala` と `timer2.scala`)

お詫び：実行開始時にフォント関連のエレーメッセージが表示され、TimerApp1のフォントが異様にきたなかったとしても我慢してもらいたい。これは最近、Javaについて報告されているバグに由来するらしいです。

## 決定性オートマトンのシミュレータの例: `automaton.scala`

## 点滅間隔が指定された信号の例: `scheduledtrafficlight.scala`

## ポトンゲームの例: `poton.scala`
