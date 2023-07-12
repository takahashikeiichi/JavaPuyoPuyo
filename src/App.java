import javax.swing.JLabel;

public class App
{
    /** ウインドウのタイトル */
    private static final String TITLE = "ぷよぷよみたいなゲーム";
    /** 画面幅 */
    private static final int DISPLAY_WIDTH = 300;
    /** 画面高さ */
    private static final int DISPLAY_HEIGHT = 500;
    
    /**
     * メインメソッド
     */
    public static void main(String[] args) throws Exception 
    {
        System.out.println("Hello, World!");

        // GUI生成
        GameWindow gameWindow = new GameWindow(TITLE, DISPLAY_WIDTH, DISPLAY_HEIGHT);
        DrawPanel drawPanel = new DrawPanel();
        gameWindow.add(drawPanel);
        //gameWindow.DrawGraphic("img\\PuyoGreen.png");
        
        //DrawPanel drawPanel = new DrawPanel();
        //JLabel label = drawPanel.CreateGraphics("PuyoGreen.png");
        //drawPanel.Draw(label);

        //gameWindow.add(drawPanel);

        PuyoGameManager gameManager = PuyoGameManager.getInstance();
        gameManager.start(drawPanel);
    }
}
