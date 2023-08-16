package PuyoPuyo;
import java.awt.Point;
import java.util.ArrayList;

import GameLibrary.DrawManager;
import GameLibrary.GameObject;

/**
 * ぷよぷよのマップ情報
 */
public class ObjMap extends GameObject
{
    private final int PUYO_DELETE_COUNT = 4;
    public final int OBJECT_WIDTH = 32;
    public final int OBJECT_HEIGHT = 32;
    // ぷよが落ちているときに横にあるぷよに重なって配置できる不具合対応
    public final float OBJECT_COLLISION_OFFSET_HEIGHT = (float)OBJECT_HEIGHT - 0.5f;
    private final int MAP_X_NUM = 6;
    private final int MAP_Y_NUM = 12;
    private Puyo[] map = new Puyo[MAP_X_NUM * MAP_Y_NUM];

    private DrawManager drawManager = DrawManager.getInstance();
    private PuyoGameManager gameManager = PuyoGameManager.getInstance();
    private PuyoCreator puyoCreator = null;
    private ChainText chainText = null;
    private GameScoreText gameScoreText = null;
    private GameOverText gameOverText = null;
    // ぷよが削除後の移動中か
    private boolean isPuyoMovingAgain = false;
    private int chainNum = 0;
    private int score = 0;

    public ObjMap()
    {
        posX = 32;
        posY = 82;
    }

    /** 初期化 */
    public void init()
    {
        puyoCreator = new PuyoCreator(this, posX, posY);
        gameManager.insertGameObject(puyoCreator);
        puyoCreator.StockPuyo();
        puyoCreator.CreatePuyo();

        chainText = new ChainText(40, 250);
        gameManager.insertGameObject(chainText);

        gameScoreText = new GameScoreText();
        gameManager.insertGameObject(gameScoreText);

        gameOverText = new GameOverText();
        gameManager.insertGameObject(gameOverText);
    }

    /** ぷよ移動後の更新処理 */
    public void updateMap()
    {
        // ぷよを消す
        // ぷよが消えることでぷよが動く(動かない場合もあり)
        isPuyoMovingAgain = checkDeletePuyo();

        // バツ印の所にぷよを設置するとゲームオーバー
        if(map[2] != null)
        {
            // ゲームオーバーテキスト表示
            gameOverText.setEnable(true);
            return;
        }

        // ぷよが消えたことによる落下チェック
        if(isPuyoMovingAgain)
        {
            for(Puyo puyo : map)
            {
                if(puyo == null)
                {
                    continue;
                }

                puyo.checkPuyoDown();
            }
        }

        if(!isPuyoMovingAgain)
        {
            // 新しいぷよを出す
            puyoCreator.CreatePuyo();
        }
    }

    /** 削除するぷよを調べる */
    private boolean checkDeletePuyo()
    {
        ArrayList<Point> deletePuyoPosList = new ArrayList<>();
        Puyo puyo = null;
        // ぷよを削除するか
        boolean isDeletePuyo = false;
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

                // 再起関数で同色ぷよの数と情報を取得
                int count = nearPuyoCounter(x, y, puyo.getPuyoColor(), deletePuyoPosList);
                if(count >= PUYO_DELETE_COUNT)
                {
                    for (Point deletePuyoPos : deletePuyoPosList)
                    {
                        // 繋がったぷよを消す
                        deleteMapPuyo(deletePuyoPos.x, deletePuyoPos.y);
                    }
                    isDeletePuyo = true;

                    // ぷよを削除によるスコア更新 
                    // 連鎖数は更新前なので+1
                    updateScore(count, chainNum + 1);
                }
                deletePuyoPosList.clear();
            }
        }

        if(isDeletePuyo)
        {
            // 連鎖数更新
            chainNum++;
            chainText.setChainNum(chainNum);
        }
        else
        {
            chainNum = 0;
        }
        
        return isDeletePuyo;
    }

    // 消したぷよからスコアを更新
    private void updateScore(int deletePuyoCount, int chainNum)
    {
        score += deletePuyoCount * chainNum;
        gameScoreText.setScore(score);
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

    /** ゲームマップにぷよを設置 */
    public void setPuyo(Puyo puyo, int x, int y)
    {
        if(IsOutsideRange(x, y))
        {
            // 範囲外
            return;
        }

        if(puyo != null)
        {
            // ぷよの位置を修正する(ぷよを設置する場合のみ)
            puyo.setPosX(posX + x * OBJECT_WIDTH);
            puyo.setPosY(posY + y * OBJECT_HEIGHT);
            puyo.setSpeed(gameManager.HIGH_FALLING_SPPED);
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

    /**
     *  範囲外チェック 
     * @return true:範囲外 false:範囲内
     * */
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
        if(isPuyoMovingAgain)
        {
            if(hasAllPuyoFallen())
            {
                updateMap();
            }
        }
    }

    /** 全てのぷよが落ちたか */
    private boolean hasAllPuyoFallen()
    {
        var objects = gameManager.getGameObjects(ObjectType.Puyo);

        for(var object : objects)
        {
            Puyo puyo = (Puyo)object;
            if(puyo == null)
            {
                continue;
            }

            if(puyo.getIsDown())
            {
                return false;
            }
        }
        return true;
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
