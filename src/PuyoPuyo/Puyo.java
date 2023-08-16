package PuyoPuyo;
import java.awt.Point;
import java.util.EnumSet;

import GameLibrary.DrawManager;
import GameLibrary.GameObject;

/**
 * ぷよオブジェクト
 */
public class Puyo extends GameObject
{
    private float speed = 1.0f;
    private int width = 32;
    private int height = 32;
    private DrawManager drawManager = DrawManager.getInstance();
    // ぷよが落ちているか
    private boolean isDown = false;
    private ObjMap objMap = null;
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

    public Puyo(ObjMap map, float posX, float posY, float speed, Color color)
    {
        objectType = ObjectType.Puyo;
        this.objMap = map;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
        this.color = color;
    }

    public void update()
    {
        if(isDown)
        {
            // 落下処理
            posY += speed;
        }

        checkPuyoDown();
    }

    public void checkPuyoDown()
    {
        Point puyo1MapPos = objMap.getMap(posX, posY);
        if(objMap.IsOutsideRange(puyo1MapPos.x, puyo1MapPos.y + 1)
        || objMap.PuyoExists(puyo1MapPos.x, puyo1MapPos.y + 1))
        {
            if(getIsDown())
            {
                objMap.setPuyo(this, puyo1MapPos.x, puyo1MapPos.y);
            }

            setIsDown(false);
        }
        else
        {
            if(!getIsDown())
            {
                objMap.setPuyo(null, puyo1MapPos.x, puyo1MapPos.y);
            }

            setIsDown(true);
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

    public Color getPuyoColor()
    {
        return color;
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
