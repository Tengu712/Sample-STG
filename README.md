# Sample STG

## Outline

STGのサンプルプログラム。以下のレギュレーションに従う。

- 古いJavaでも動く
- GUIフレームワークにJavaFXを使う
- タイル要素を含む
- アニメーションを含む

このレギュレーションに従う理由は、東京理科大学創域理工学部情報計算科学科の二年生後期必修授業の最終課題のレギュレーションがそうであるから。

## Attention

当コードはCC0 1.0 Universalで配布されている。
従って、すべてをコピペしても構わないが、万一何かしらの学校課題等で行う場合は、やめた方がいい。

## Build

Linuxユーザはよしなに。

Windowsユーザは、

1. [JavaFX SDK](https://gluonhq.com/products/javafx/)をインストール
2. SDK内のbinフォルダを環境変数`PATH`に追加
3. SDK内のlibフォルダを環境変数`PATH_TO_FX`に追加

をした後、build.batを実行してビルド、run.batを実行して実行。

## OOP

ある程度オブジェクト指向に則っている。

オブジェクト指向とは、問題をオブジェクト単位に切り分けるパラダイムである。
オブジェクトとは、データあるいはロジックあるいはその組である。

オブジェクト指向の本質はポリモーフィズムである。
ポリモーフィズムとは、複数種類のオブジェクトを一種類のオブジェクトと見なして扱える性質である。
考え方として重要なのは、プログラム中で切り替えたいものを、切り替わらないものに置き換える、ということである。

### Importance of interface

例えば、自機・敵機・自弾・敵弾・障害物の当たり判定を実装するとする。
ポリモーフィズムを用いない場合や、オブジェクト指向の本質をカプセル化だと思っている者は、次のメソッドを実装することになる。

- Player#isHit(Enemy)
- Player#isHit(Bullet)
- Player#isHit(Obstacle)
- Enemy#isHit(Bullet)
- Bullet#isHit(Obstacle)

ここに物体の多相性という流動性がある。
これを例えばHitableインターフェースという非流動的なオブジェクトで置き換える。
重要なのは、多重定義が減ったことよりも「相手のオブジェクトの種類に依存せず、操作を同じくできる」という点である。

- Player#isHit(Hitable)
- Enemy#isHit(Hitable)
- Bullet#isHit(Hitable)

### State/Strategy Pattern

例えば、自機は、プレイヤーの入力によって動かしたいし、リプレイデータによっても動かしたい。
愚直に実装してしまうと、次のようになる。

```java
public class Player {
    private boolean isReplay;
    public void update() {
        if (this.isReplay) this.updateWithReplay();
        else this.updateWithInput();
    }
    private void updateWithReplay() {
    }
    private void updateWithInput() {
    }
}
```

ここに動き方の多相性という流動性がある。
これを例えばMoverインターフェースという非流動的なオブジェクトで置き換える。
外部からMoverを実装したインスタンスを与えれば、Playerはどのような動き方か意識せずに動くことができる。

```java
public interface Mover {
    void move();
}
public class Player {
    private Mover mover;
    public void update() {
        this.mover.move();
    }
}
```

似た事例に、シーン切替えがある。
愚直に実装すると次のように書いてしまう。

```java
    SceneID sceneId = SceneID.Title;
    TitleScene titleScene = new TitleScene();
    GameScene gameScene = new GameScene();
    GameOverScene gameOverScene = new GameOverScene();
    switch (sceneId) {
        case SceneID.Title: sceneId = titleScene.update(); break;
        case SceneID.Game: sceneId = gameScene.update(); break;
        case SceneID.GameOver: sceneId = gameOverScene.update(); break;
    }
```

ここにシーンの状態とロジックの多相性という流動性がある。
これを例えばSceneインターフェースという非流動的なオブジェクトで置き換える。
今がどのシーンであるかを意識せず、一様にSceneインターフェースを介して操作することができる。

```java
    Scene scene = new TitleScene();
    scene = scene.update();
```

このようなロジックの切替えはState/Strategyパターンとして知られている（状態を持つものがState、状態を持たないものがStrategy）。
関数型があれば、Strategyパターンは必要ないが、ない場合は個人的に最も有用でわかりやすいデザインパターンだと思っている。