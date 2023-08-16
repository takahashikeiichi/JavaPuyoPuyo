package GameLibrary;

import java.awt.event.KeyEvent;

/** キー入力受付用インターフェース */
public interface KeyInput
{
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
}
