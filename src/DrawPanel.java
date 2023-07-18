import java.awt.Graphics;

import javax.swing.JPanel;

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
