import java.awt.Color;
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
        private int style;
        private int x;
        private int y;
        private int size;
        private Color color;

        public String getText() { return text; }
        public String getFontName() { return fontName; }
        public int getStyle() { return style; }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getSize() { return size; }
        public Color getColor() { return color; }

        public DrawData(String text, String fontName, int style, int x, int y, int size, Color color)
        {
            setDrawData(text, fontName, style, x, y, size, color);
        }

        public DrawData(String text, String fontName, int style, int x, int y, int size)
        {
            setDrawData(text, fontName, style, x, y, size, Color.BLACK);
        }

        private void setDrawData(String text, String fontName, int style, int x, int y, int size, Color color)
        {
            this.text = text;
            this.fontName = fontName;
            this.style = style;
            this.x = x;
            this.y = y;
            this.size = size;
            this.color = color;
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

    public void clear()
    {
        drawList.clear();
    }

    /**
     *  文字を表示させる GameObjectのDraw関数で呼ぶ 
     */
    public void drawString(String text, String fontName, int style, int x, int y, int size)
    {
        DrawData drawData = new DrawData(text, fontName, style, x, y, size);
        drawList.add(drawData);
    }

    public void drawString(String text, String fontName, int style, int x, int y, int size, Color color)
    {
        DrawData drawData = new DrawData(text, fontName, style, x, y, size, color);
        drawList.add(drawData);
    }
}
