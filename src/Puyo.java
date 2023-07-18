import java.util.EnumSet;

/**
 * ぷよオブジェクト
 */
public class Puyo extends GameObject
{
    private float speed = 1.0f;
    private int width = 32;
    private int height = 32;
    private DrawManager drawManager = DrawManager.getInstance();
    private boolean isDown = false;
    /**
     * ぷよの色の種類
     */
    public enum Color
    {
        Green,
        Red,
        Blue,
        Yellow;

        public static Color valueOf(final int value) 
        {
            for(Color m : EnumSet.allOf(Color.class))
            {
                if(m.ordinal() == value) 
                {
                    return m;
                }
            }
            return null;
        }
    }

    private Color color;

    public Puyo(float posX, float posY, Color color)
    {
        objectType = ObjectType.Puyo;
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    public void update()
    {
        if(isDown)
        {
            // 落下処理
            posY += speed;
        }
    }

    /**
     * 画像の表示を行う
     */
    public void draw()
    {
        switch(color)
        {
            case Green:
                drawManager.drawImage(DrawManager.GraphicsId.PuyoGreen, (int)posX, (int)posY, width, height);
                break;
            case Red:
                drawManager.drawImage(DrawManager.GraphicsId.PuyoRed, (int)posX, (int)posY, width, height);
                break;
            case Blue:
                drawManager.drawImage(DrawManager.GraphicsId.PuyoBlue, (int)posX, (int)posY, width, height);
                break;
            case Yellow:
                drawManager.drawImage(DrawManager.GraphicsId.PuyoYellow, (int)posX, (int)posY, width, height);
                break;
        }
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

    public boolean getIsDown()
    {
        return isDown;
    }

    public void setIsDown(boolean isDown)
    {
        this.isDown = isDown;
    }
}
