/**
 * ゲームで使用するオブジェクト抽象クラス
 */
public abstract class GameObject
{
    /**オブジェクトの種類 */
    public enum ObjectType 
    {
        None,
        Puyo,
        
    }

    protected ObjectType objectType = ObjectType.None;
    public ObjectType getObjectType()
    {
        return objectType;
    }

    protected float posX;
    public float getPosX()
    {
        return posX;
    }
    public void setPosX(float x)
    {
        posX = x;
    } 

    protected float posY;
    public float getPosY()
    {
        return posY;
    }
    public void setPosY(float y)
    {
        posY = y;
    }

    /**オブジェクト更新メソッド */
    public abstract void update();

    /**オブジェクト描画メソッド*/
    public abstract void draw();
}
