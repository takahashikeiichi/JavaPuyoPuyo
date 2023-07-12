import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUIゲーム画面
 */
public class GameWindow extends JFrame
{
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
    }

    /*
    public void DrawGraphic(String fileName)
    {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        //JLabel label = new JLabel("Name");
        //JTextField text = new JTextField(20);
        //panel.add(label);
        //panel.add(text);

        ImageIcon icon = new ImageIcon(fileName);
        JLabel label = new JLabel(icon);
        panel.add(label);

        label.setBounds(0, 0, 200, 200);

        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.CENTER);
    }
    */
}
