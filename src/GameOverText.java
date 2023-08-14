import java.awt.Color;
import java.awt.Font;

/** ゲームオーバーテキスト */
public class GameOverText extends GameObject
{
    private TextManager textManager = TextManager.getInstance();
    private boolean isEnable = true;
    public void setEnable(boolean isEnable)
    {
        this.isEnable = isEnable;
    }

    public GameOverText()
    {
        posX = 30;
        posY = 300;
    }

    public void update()
    {

    }

    public void draw()
    {
        if(isEnable)
        {
            textManager.drawString("GameOver", "Serif", Font.PLAIN, (int)posX, (int)posY, 50, Color.red);
        }
    }
}
