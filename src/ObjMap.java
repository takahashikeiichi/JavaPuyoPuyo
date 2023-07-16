/**
 * ぷよぷよのゲーム情報
 */
public class ObjMap extends GameObject
{
    private final int MAP_X_NUM = 6;
    private final int MAP_Y_NUM = 12;
    private int[] map = new int[MAP_X_NUM * MAP_Y_NUM]; 

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

    /** ぷよ移動後のマップ更新 */
    private void updateMap()
    {

    }

    private boolean PuyoExists(int x, int y)
    {
        return map[y * MAP_X_NUM + x] > 0;
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
