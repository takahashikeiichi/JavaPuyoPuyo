import java.util.Random;

/**
 * ぷよの生成を行う
 */
public class PuyoCreator extends GameObject
{
    private ObjMap objMap;
    private DrawManager drawManager = DrawManager.getInstance();
    private GameManager gameManager = GameManager.getInstance();
    private int puyoTypeNum = 4;

    // ストックしている一つ目の上のぷよ(表示でいう上から一番目のぷよ)
    private Puyo first1Puyo = null;
    // ストックしている一つ目の下のぷよ(表示でいう上から二番目のぷよ)
    private Puyo first2Puyo = null;
    // ストックしている二つ目の上のぷよ(表示でいう上から三番目のぷよ)
    private Puyo second1Puyo = null;
    // ストックしている二つ目の下のぷよ(表示でいう上から四番目のぷよ)
    private Puyo second2Puyo = null;

    public PuyoCreator(ObjMap map, float posX, float posY)
    {
        this.objMap = map;
        this.posX = posX + 32 * 6;
        this.posY = posY;
    }

    /** 次に落ちるぷよをストック */
    public void StockPuyo()
    {
        if(first1Puyo == null)
        {
            first1Puyo = CreateStockPuyo( posX, posY + 10);
        }

        if(first2Puyo == null)
        {
            first2Puyo = CreateStockPuyo( posX, posY + 10 + 32 * 1);
        }

        if(second1Puyo == null)
        {
            second1Puyo = CreateStockPuyo( posX, posY + 10 + 32 * 2 + 16);
        }

        if(second2Puyo == null)
        {
           second2Puyo = CreateStockPuyo( posX, posY + 10 + 32 * 3 + 16);
        }
    }

    /** 次に落ちるぷよを作成する */
    private Puyo CreateStockPuyo(float posX, float posY)
    {
        Puyo puyo = null;
        Puyo.Color puyoColor = RandomPuyoColor();
        if(puyoColor != null)
        {
            puyo = new Puyo(posX + 16, posY + 16, puyoColor);
            gameManager.insertGameObject(puyo);
        }
        return puyo;
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
        PuyoPairs pairs = new PuyoPairs(objMap, first1Puyo, first2Puyo);
        gameManager.insertGameObject(pairs);

        if(first1Puyo != null)
        {
            if(second1Puyo != null)
            {
                second1Puyo.setPosY(first1Puyo.getPosY());
            }

            // ぷよの初期位置に移動させる
            first1Puyo.setPosX(objMap.posX + 32 * 2);
            first1Puyo.setPosY(objMap.posY - 32);
            first1Puyo.SetIsDown(true);
            first1Puyo = second1Puyo;
            second1Puyo = null;
        }

        if(first2Puyo != null)
        {
            if(second2Puyo != null)
            {
                second2Puyo.setPosY(first2Puyo.getPosY());
            }
            first2Puyo.setPosX(objMap.posX + 32 * 2);
            first2Puyo.setPosY(objMap.posY);
            first2Puyo.SetIsDown(true);
            first2Puyo = second2Puyo;
            second2Puyo = null;
        }

        StockPuyo();
    }

    public void update()
    {

    }

    public void draw()
    {
        drawManager.drawImage(DrawManager.GraphicsId.Frame, (int)posX, (int)posY, 64, 64 * 3);
    }
}
