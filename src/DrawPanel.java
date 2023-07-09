import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 描画を行うパネル
 */
public class DrawPanel extends JPanel
{
    /** */
    public JLabel CreateGraphics(String fileName)
    {
        ImageIcon imageIcon = new ImageIcon(fileName);
        return new JLabel(imageIcon);
    }

    public void Draw(JLabel label)
    {
        add(label);
    }
}
