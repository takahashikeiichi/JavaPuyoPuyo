import java.awt.Color;
import java.awt.Font;

/** 連鎖テキスト */
public class ChainText extends GameObject
{
    private TextManager textManager = TextManager.getInstance();

    /** 連鎖数 */
    private String chainText = "";
    private int chainNum = 0;
    public ChainText(float posX, float posY)
    {
        this.posX = posX;
        this.posY = posY;
        setChainNum(chainNum);
    }

    public void setChainNum(int chainNum)
    {
        this.chainNum = chainNum;
        Integer interger = Integer.valueOf(chainNum); 
        chainText = interger.toString() + "連鎖!!!";
    } 

    public void update()
    {

    }

    public void draw()
    {
        if(chainNum > 0)
        {
            textManager.drawString(chainText, "Serif", Font.PLAIN, (int)posX, (int)posY, 50, Color.BLUE);
        }
    }
}
