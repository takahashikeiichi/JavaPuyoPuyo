package PuyoPuyo;
import java.awt.Color;
import java.awt.Font;

import GameLibrary.GameObject;
import GameLibrary.TextManager;

/** ゲームオーバーテキスト */
public class GameOverText extends GameObject
{
    private TextManager textManager = TextManager.getInstance();
    private boolean isEnable = false;
    public void setEnable(boolean isEnable)
    {
        this.isEnable = isEnable;
    }

    public GameOverText()
    {
        posX = 30;
        posY = 300;
    }

    public void init()
    {
        isEnable = false;
    }

    public void update()
    {

    }

    public void draw()
    {
        if(isEnable)
        {
            textManager.drawString("GameOver", "Serif", Font.PLAIN, (int)posX, (int)posY, 50, Color.red);
            textManager.drawString("R  : リトライ", "Serif", Font.PLAIN, (int)posX, (int)posY + 30, 30);
        }
    }
}
