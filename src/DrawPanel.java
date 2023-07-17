import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 描画を行うパネル
 */
public class DrawPanel extends JPanel
{
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        DrawManager drawManager = DrawManager.getInstance();
        for(DrawManager.DrawData drawData: drawManager.getDrawList())
        {
            g.drawImage(drawData.getImage(), drawData.getX(), drawData.getY(), drawData.getWidth(), drawData.getHeight(), this);
        }

        drawManager.clear();
    }
}
