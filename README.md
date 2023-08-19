
『ぷよぷよみたいなゲーム』

■はじめに
このたびは私の制作物をご覧いただき、ありがとうございます。


■ソースコードで見て頂きたい点
src\GameLibrary以下のファイル
・DrawManager.java
・DrawPanel.java
・GameManager.java
・GameObject.java
・GameWindow.java
・InputManager.java
・KeyInput.java
・TextManager.java

こちらのファイルでは、他のゲーム制作ですぐに利用できるように
1.ゲーム特有の処理をクラスで切り分けている。
2.必要な機能別にまとまっている。
となっております。

具体的には
1.DrawManager.javaではimageDicに使用したい画像を登録して、
drawImageメソッドを呼び出すと画像を表示できます。
などとなるべく使いやすいような設計を心がけております。


■フォルダ構成
│  App.java エントリポイント
│
├─GameLibrary
│      DrawManager.java　画像表示管理クラス
│      DrawPanel.java　  swing Panelクラス継承
│      GameManager.java  ゲームを管理する
│      GameObject.java   ゲームオブジェクト抽象クラス
│      GameWindow.java   swing JFrameクラス継承
│      InputManager.java キーボード入力クラス
│      KeyInput.java     入力インターフェース
│      TextManager.java  文字表示管理クラス
│
└─PuyoPuyo
        ChainText.java       連鎖数表示クラス
        ControlText.java     操作方法表示クラス
        GameOverText.java    ゲームオーバークラス
        GameScoreText.java   ゲームスコア表示クラス
        ObjMap.java          ぷよを配置するマップクラス
        Puyo.java            ぷよ1つのクラス
        PuyoCreator.java     ぷよ生成クラス
        PuyoGameManager.java GameManagerクラス継承 このゲーム用
        PuyoPairs.java       ぷよが落ちてくるときのペアクラス

■ゲーム遊び方(Windows版)
1.javaをインストールして下さい。※javaをインストールされていない方のみ
  URL : https://www.java.com/ja/download/
2.Githubからソースコード一式をダウンロードしてください。
  2.1 「<> Code▼」ボタンをクリック
  2.2 「Download ZIP」ボタンをクリックするとダウンロード開始
  2.3 ダウンロードしたzipファイルを解凍して下さい。
3.GameStart.batをダブルクリックするとゲームが始まります。

■ゲームルール
1.一人用です。
2.同じ色のぷよが上下左右で4つ以上繋げる消えてスコアが増える。
3.繋げる数が多い方がスコアが増える。
4.ぷよが消えた後にぷよが消えると「連鎖」となりスコアにボースが入る。
5.ゲームスコア計算式 (消えたぷよの数 × 連鎖数)
6.×の位置までぷよを設置するとゲームオーバー。


■操作方法
・←キー : ぷよを左に移動
・→キー : ぷよを右に移動
・↓キー : ぷよの落下速度を上げる
・Aキー : ぷよを左回転する
・Sキー : ぷよを右回転する
