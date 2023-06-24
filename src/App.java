public class App
{
    /** ウインドウのタイトル */
    private static final String TITLE = "ぷよぷよ";
    /** 画面サイズX */
    private static final int DISPLAY_SIZE_X = 300;
    /** 画面サイズY */
    private static final int DISPLAY_SIZE_Y = 500;
    
    /**
     * メインメソッド
     */
    public static void main(String[] args) throws Exception 
    {
        System.out.println("Hello, World!");

        // GUI生成
        Swing swing = new Swing(TITLE, DISPLAY_SIZE_X, DISPLAY_SIZE_Y);
    }
}
