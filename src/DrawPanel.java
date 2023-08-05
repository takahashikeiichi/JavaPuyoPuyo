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
            // 登録している描画情報通りに描画する
            g.drawImage(drawData.getImage(), drawData.getX(), drawData.getY(), drawData.getWidth(), drawData.getHeight(), this);
        }

        TextManager textManager = TextManager.getInstance();
        //for(TextManager.)
        //g.drawString(null, ALLBITS, ABORT);

        //g.drawString(null, ALLBITS, ABORT);

        drawManager.clear();
    }
}
