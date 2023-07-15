import java.util.Random;

/**
 * ぷよの生成を行う
 */
public class PuyoCreator extends GameObject
{
    private DrawManager drawManager = DrawManager.getInstance();
    private GameManager gameManager = GameManager.getInstance();
    private int puyoTypeNum = 4;

    // ストックしている一つ目の上のぷよ(表示でいう上から一番目のぷよ)
    private Puyo First1Puyo = null;
    // ストックしている一つ目の下のぷよ(表示でいう上から二番目のぷよ)
    private Puyo First2Puyo = null;
    // ストックしている二つ目の上のぷよ(表示でいう上から三番目のぷよ)
    private Puyo Second1Puyo = null;
    // ストックしている二つ目の下のぷよ(表示でいう上から四番目のぷよ)
    private Puyo Second2Puyo = null;

    public PuyoCreator(float posX, float posY)
    {
        this.posX = posX + 32 * 6;
        this.posY = posY;
    }

    public void update()
    {

    }

    /** 次に落ちるぷよをストック */
    public void StockPuyo()
    {
        if(First1Puyo == null)
        {
            Puyo.Color puyoColor = RandomPuyoColor();
            if(puyoColor != null)
            {
                First1Puyo = new Puyo(posX + 16, posY + 16, puyoColor);
                gameManager.insertGameObject(First1Puyo);
            }
        }

        if(First2Puyo == null)
        {
            //Puyo.Color puyoColor = 
        }
    }

    /** 次に落ちるぷよを作成する */
    private void CreateStockPuyo(Puyo puyo, float posX, float posY)
    {
        Puyo.Color puyoColor = RandomPuyoColor();
        if(puyoColor != null)
        {
            First1Puyo = new Puyo(posX + 16, posY + 16, puyoColor);
            gameManager.insertGameObject(First1Puyo);
        }
    }

    /** ランダムでぷよの色を決める */
    private Puyo.Color RandomPuyoColor()
    {
        Random rand = new Random();
        int puyoColor = rand.nextInt(puyoTypeNum);
        return Puyo.Color.valueOf(puyoColor);
    }

    public void CreatePuyo()
    {
        
    }

    public void draw()
    {
        drawManager.drawImage(DrawManager.GraphicsId.Frame, (int)posX, (int)posY, 64, 64 * 3);
    }
}
