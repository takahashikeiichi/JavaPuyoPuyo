package PuyoPuyo;
import java.awt.Font;

import GameLibrary.GameObject;
import GameLibrary.TextManager;

/** 操作テキスト */
public class ControlText extends GameObject
{
    private TextManager textManager = TextManager.getInstance();

    public ControlText()
    {
        posX = 10;
        posY = 500;
    }

    public void init()
    {

    }

    public void update()
    {

    }

    public void draw()
    {
        textManager.drawString("操作方法", "Serif", Font.PLAIN, (int)posX, (int)posY, 30);
        textManager.drawString("← : ぷよ左移動", "Serif", Font.PLAIN, (int)posX, (int)posY + 30, 30);
        textManager.drawString("→ : ぷよ右移動", "Serif", Font.PLAIN, (int)posX, (int)posY + 60, 30);
        textManager.drawString("↓ : ぷよ落下速度UP", "Serif", Font.PLAIN, (int)posX, (int)posY + 90, 30);
        textManager.drawString("A  : ぷよ左回転", "Serif", Font.PLAIN, (int)posX, (int)posY + 120, 30);
        textManager.drawString("S  : ぷよ右回転", "Serif", Font.PLAIN, (int)posX, (int)posY + 150, 30);
    }
}
