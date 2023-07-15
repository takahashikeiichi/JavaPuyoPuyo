/**
 * 
 */
public class ObjMap extends GameObject
{
    private DrawManager drawManager = DrawManager.getInstance();
    private GameManager gameManager = GameManager.getInstance();
    private PuyoCreator puyoCreator = null;

    public ObjMap()
    {
        posX = 32;
        posY = 32;
        puyoCreator = new PuyoCreator(posX, posY);
        gameManager.insertGameObject(puyoCreator);
        puyoCreator.StockPuyo();
    }

    public void update()
    {
        
    }

    /**
     * 画像の表示を行う
     */
    public void draw()
    {
        drawManager.drawImage(DrawManager.GraphicsId.Frame, (int)posX, (int)posY, 32*6, 32*12);
        drawManager.drawImage(DrawManager.GraphicsId.Cross, (int)posX + 32*2, (int)posY, 32, 32);
    }
}
