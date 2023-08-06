import java.util.ArrayList;

/** テキスト管理 */
public class TextManager 
{
    private static TextManager instance = null;
    /** ゲームに表示する画像情報を入れる配列 */
    private ArrayList<DrawData> drawList = new ArrayList<>();
    private TextManager() {}

    /**描画に必要な情報 */
    public class DrawData
    {
        private String text;
        private String fontName;
        private int x;
        private int y;
        private int size;

        public String getText() { return text; }
        public String getFontName() { return fontName; }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getSize() { return size; }

        public DrawData(String text, String fontName, int x, int y, int size)
        {
            this.text = text;
            this.fontName = fontName;
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    // インスタンスを返す
    public static TextManager getInstance()
    {
        if(instance == null)
        {
            instance = new TextManager();
        }
        return instance;
    }

    public ArrayList<DrawData> getDrawList()
    {
        return drawList;
    }
}
