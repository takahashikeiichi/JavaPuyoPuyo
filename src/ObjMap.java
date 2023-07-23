import java.awt.Point;
import java.util.ArrayList;

/**
 * ぷよぷよのマップ情報
 */
public class ObjMap extends GameObject
{
    private final int PUYO_DELETE_COUNT = 4;
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
        // ぷよを消す
        checkDeletePuyo();

        // 新しいぷよを出す
        puyoCreator.CreatePuyo();
    }

    /** 削除するぷよを調べる */
    private void checkDeletePuyo()
    {
        ArrayList<Point> deletePuyoPosList = new ArrayList<>();
        Puyo puyo = null;
        for(int y = 0; y < MAP_Y_NUM; y++)
        {
            for(int x = 0; x < MAP_X_NUM; x++)
            {
                puyo = map[y * MAP_X_NUM + x];
                if(puyo == null)
                {
                    // ぷよがない場所は何もしない
                    continue;
                }

                int count = nearPuyoCounter(x, y, puyo.getPuyoColor(), deletePuyoPosList);
                if(count >= PUYO_DELETE_COUNT)
                {
                    for (Point deletePuyoPos : deletePuyoPosList)
                    {
                        // 繋がったぷよを消す
                        deleteMapPuyo(deletePuyoPos.x, deletePuyoPos.y);
                    }
                }
            }
        }
    }

    /** ぷよの繋がりから削除するぷよを調べる */
    private int nearPuyoCounter(int mapX, int mapY, Puyo.Color puyoColor, ArrayList<Point> searchPuyoPosList)
    {
        int count = 0;
        Puyo puyo = null;
        ArrayList<Point> directionList = new ArrayList<Point>(4);
        directionList.add(new Point(0, 1)); // 下
        directionList.add(new Point(-1, 0)); // 左
        directionList.add(new Point(1, 0)); // 右
        directionList.add(new Point(0, -1)); // 上

        if(IsOutsideRange(mapX, mapY))
        {
            // 範囲外
            return count;
        }

        for (Point searchPuyoPos : searchPuyoPosList)
        {
            if(mapX == searchPuyoPos.x && mapY == searchPuyoPos.y)
            {
                // 既に確認済みの場所
                return count;
            }
        }

        puyo = map[mapY * MAP_X_NUM + mapX];
        if(puyo == null || puyo.getPuyoColor() != puyoColor)
        {
            // 探している種類のぷよではない
            return count;
        }
        count++;
        searchPuyoPosList.add(new Point(mapX, mapY));

        for(Point direction : directionList)
        {
            count += nearPuyoCounter(mapX + direction.x, mapY + direction.y, puyoColor, searchPuyoPosList);
        }
        return count;
    }

    /** マップ上にあるぷよを削除する */
    private void deleteMapPuyo(int x, int y)
    {
        Puyo puyo = map[y * MAP_X_NUM + x];
        if(puyo == null)
        {
            return;
        }

        gameManager.deleteGameObject(puyo);
        map[y * MAP_X_NUM + x] = null;
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
