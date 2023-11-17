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

例えば、タイトルシーンやゲームシーンやはプログラム中に切り替えたいものである。
ポリモーフィズムを用いない場合や、オブジェクト指向の本質をカプセル化だと思っている者は、次のように書いてしまう。

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

これらタイトルシーン等はシーンという抽象オブジェクトで置き換えられる。
これは、Stateパターンというよく知られた設計である(各シーンが状態を持たないならばただの関数切替え。Strategyパターンとして知られる)。

```java
    Scene scene = new TitleScene();
    scene = scene.update();
```

例えば、