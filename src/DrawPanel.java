import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * 描画を行うパネル
 */
public class DrawPanel extends JPanel
{
    DrawManager drawManager = DrawManager.getInstance();
    TextManager textManager = TextManager.getInstance();
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        //for(DrawManager.DrawData drawData: drawManager.getDrawList())
        var drawList = drawManager.getDrawList();
        for(int i = 0; i < drawList.size(); ++i)
        {
            var drawData = drawList.get(i);
            // 登録している描画情報通りに描画する
            g.drawImage(drawData.getImage(), drawData.getX(), drawData.getY(), drawData.getWidth(), drawData.getHeight(), this);
        }

        //for(TextManager.DrawData textDrawData: textManager.getDrawList())
        var textDrawList = textManager.getDrawList();
        for(int i = 0; i < textDrawList.size(); ++i)
        {
            var textDrawData = textDrawList.get(i);
            g.setFont(new Font(textDrawData.getFontName(), textDrawData.getStyle(), textDrawData.getSize()));
            g.setColor(textDrawData.getColor());
            g.drawString(textDrawData.getText(), textDrawData.getX(), textDrawData.getY());
        }
        
        clearDrawList();
    }

    public void clearDrawList()
    {
        drawManager.clear();
        textManager.clear();
    }
}
