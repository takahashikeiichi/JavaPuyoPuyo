package PuyoPuyo;

import java.awt.event.KeyEvent;

import GameLibrary.GameObject;
import GameLibrary.InputManager;
import GameLibrary.KeyInput;

public class RetryController extends GameObject implements KeyInput {
    private InputManager inputManager = InputManager.getInstance();
    private PuyoGameManager gameManager = PuyoGameManager.getInstance();
    private boolean isEnable = false;

    RetryController()
    {
        inputManager.addKeyInput(this);
    }

    public void setEnable(boolean isEnable)
    {
        this.isEnable = isEnable;
    }

    public void init()
    {
        isEnable = false;
    }

    public void update()
    {

    }

    public void draw()
    {

    }

    public void keyPressed(KeyEvent e)
    {
        if (!isEnable) {return;}

        switch (e.getKeyCode()) 
        {
            case KeyEvent.VK_R:
                gameManager.restart();
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {

    }
}
