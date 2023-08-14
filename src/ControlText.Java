import java.awt.Font;

/** 操作テキスト */
public class ControlText extends GameObject
{
    private TextManager textManager = TextManager.getInstance();

    public ControlText()
    {
        posX = 50;
        posY = 500;
    }

    public void update()
    {

    }

    public void draw()
    {
        textManager.drawString("操作方法", "Serif", Font.PLAIN, (int)posX, (int)posY, 30);
        textManager.drawString("← : ぷよ左回転", "Serif", Font.PLAIN, (int)posX, (int)posY + 30, 30);
        textManager.drawString("→ : ぷよ左回転", "Serif", Font.PLAIN, (int)posX, (int)posY + 60, 30);
        textManager.drawString("A  : ぷよ左回転", "Serif", Font.PLAIN, (int)posX, (int)posY + 90, 30);
        textManager.drawString("S  : ぷよ右回転", "Serif", Font.PLAIN, (int)posX, (int)posY + 120, 30);
    }
}
