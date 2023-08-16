package PuyoPuyo;
import java.awt.Color;
import java.awt.Font;

import GameLibrary.GameObject;
import GameLibrary.TextManager;

/** ゲームスコアテキスト */
public class GameScoreText extends GameObject
{
    private TextManager textManager = TextManager.getInstance();

    private int score = 0;
    private String scoreText = "";
    /** ゲームスコア */
    public GameScoreText()
    {
        posX = 150;
        posY = 60;
        setScore(0);
    }

    public void setScore(int score)
    {
        this.score = score;
        Integer interger = Integer.valueOf(score);
        scoreText = "Score : " + interger.toString();
    } 

    public void update()
    {

    }

    public void draw()
    {
        textManager.drawString(scoreText, "Serif", Font.PLAIN, (int)posX, (int)posY, 30, Color.BLACK);   
    }
}
