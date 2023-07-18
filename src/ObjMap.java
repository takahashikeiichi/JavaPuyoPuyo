import java.awt.Point;

/**
 * ぷよぷよのマップ情報
 */
public class ObjMap extends GameObject
{
    public final int OBJECT_WIDTH = 32;
    public final int OBJECT_HEIGHT = 32;
    // ぷよが落ちているときに横にあるぷよにかさって配置できる不具合対応
    public final float OBJECT_COLLISION_OFFSET_HEIGHT = (float)OBJECT_HEIGHT - 0.5f;
    private final int MAP_X_NUM = 6;
    private final int MAP_Y_NUM = 12;
    private Puyo[] map = new Puyo[MAP_X_NUM * MAP_Y_NUM];

    private DrawManager drawManager = DrawManager.getInstance();
    private GameManager gameManager = GameManager.getInstance();
    private PuyoCreator puyoCreator = null;

    public ObjMap()
    {
        posX = 32;
        posY = 32;
    }

    public void init()
    {
        puyoCreator = new PuyoCreator(this, posX, posY);
        gameManager.insertGameObject(puyoCreator);
        puyoCreator.StockPuyo();
        puyoCreator.CreatePuyo();
    }

    /** ぷよ移動後の更新処理 */
    public void UpdateMap()
    {
        puyoCreator.CreatePuyo();
    }

    public void setPuyo(Puyo puyo, int x, int y)
    {
        if(IsOutsideRange(x, y))
        {
            // 範囲外
            return;
        }

        map[y * MAP_X_NUM + x] = puyo;
    }

    /** 位置情報からマップ上場所のx,yを取得 */
    public Point getMap(float posX, float posY)
    {
        int mapX = 0;
        int mapY = 0;
        float x = posX - this.posX;
        float y = posY - this.posY;
        if(x != 0.0f)
        {
            mapX = (int)x / OBJECT_WIDTH;
        }
        if(y != 0.0f)
        {
            mapY = (int)y / OBJECT_HEIGHT;
        }

        return new Point(mapX, mapY);
    }

    public boolean IsOutsideRange(int x, int y)
    {
        if(x < 0 || x >= MAP_X_NUM 
        || y < 0 || y >= MAP_Y_NUM)
        {
            return true;
        }

        return false;
    }

    public boolean PuyoExists(int x, int y)
    {
        if(IsOutsideRange(x, y))
        {
            return false;
        }

        return map[y * MAP_X_NUM + x] != null;
    }

    public void update()
    {
        
    }

    /**
     * 画像の表示を行う
     */
    public void draw()
    {
        drawManager.drawImage(DrawManager.GraphicsId.Frame, (int)posX, (int)posY, 32 * MAP_X_NUM, 32 * MAP_Y_NUM);
        drawManager.drawImage(DrawManager.GraphicsId.Cross, (int)posX + 32*2, (int)posY, 32, 32);
    }
}
