import javax.swing.JFrame;

/**
 * GUIクラス
 */
public class Swing
{
    /**
     * GUIを生成
     * @param title ウインドウのタイトル
     * @param displaySizeX ウィンドウのサイズX
     * @param displaySizeY ウィンドウのサイズY
     */
    public Swing(String title, int displaySizeX, int displaySizeY)
    {
        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(displaySizeX, displaySizeY);
        frame.setVisible(true);
    }
    
    
}
