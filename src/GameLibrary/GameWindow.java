package GameLibrary;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

/**
 * GUIゲーム画面
 */
public class GameWindow extends JFrame implements KeyListener
{
    InputManager inputManager = InputManager.getInstance();
    /**
     * GUIを生成
     * @param title ウインドウのタイトル
     * @param width 
     * @param height
     */
    public GameWindow(String title, int width, int height)
    {
        setTitle(title);
        setVisible(true);
        setSize(width, height);
        // ×印を押すとアプリを落とす
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
    }

    @Override
	public void keyTyped(KeyEvent e)
    {
		//使用しないので空にしておきます。
	}
 
	@Override
	public void keyPressed(KeyEvent e)
    {
        inputManager.keyPressed(e);
	}
 
	@Override
	public void keyReleased(KeyEvent e)
    {
        inputManager.keyReleased(e);
	}
}
