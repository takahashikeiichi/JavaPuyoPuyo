/**
 * ぷよオブジェクト
 */
public class Puyo
{
    /**
     * ぷよの色の種類
     */
    public enum Color
    {
        Green,
        Red,
        Blue,
        Yellow
    }

    private Color color;

    public Puyo(Color color)
    {
        this.color = color;
    }

    /**
     * 画像の表示を行う
     */
    public void draw()
    {
        switch(color)
        {
            case Green:
                break;
            case Red:
                break;
        }
    }
}
