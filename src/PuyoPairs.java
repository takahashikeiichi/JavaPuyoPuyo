import java.awt.event.KeyEvent;

public class PuyoPairs extends GameObject implements KeyInput
{
    public enum Moving 
    {
        Up,
        Left,
        Right,
        Down
    }

    private ObjMap objMap; 
    private Puyo puyo1;
    private Puyo puyo2;
    private Moving puyo1move = Moving.Up;
    private InputManager inputManager = InputManager.getInstance();
    
    PuyoPairs(ObjMap map, Puyo puyo1, Puyo puyo2)
    {
        objMap = map;
        this.puyo1 = puyo1;
        this.puyo2 = puyo2;
        inputManager.addKeyInput(this);
    }

    public void update()
    {
        //objMap.get(puyo1.posX, puyo1.posY);
    }

    public void draw() {}

    public void keyPressed(KeyEvent e)
    {
        if(puyo1 == null) {return;}
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                // 左キー
                // 左に移動
                if(puyo2 == null) {return;}
                puyo1.setPosX(puyo1.getPosX() - 32);
                puyo2.setPosX(puyo2.getPosX() - 32);
                break;
            case KeyEvent.VK_RIGHT:
                // 右キー
                // 右に移動
                if(puyo2 == null) {return;}
                puyo1.setPosX(puyo1.getPosX() + 32);
                puyo2.setPosX(puyo2.getPosX() + 32);
                break;
            case KeyEvent.VK_DOWN:
                // 下キー
                // 落下速度UP
                break;
            case KeyEvent.VK_A:
                // Aキー
                // 左回転
                switch(puyo1move)
                {
                    case Up:
                        puyo1move = Moving.Left;
                        puyo1.setPosX(puyo1.getPosX() - 32);
                        puyo1.setPosY(puyo1.getPosY() + 32);
                        break;
                    case Left:
                        puyo1move = Moving.Down;
                        puyo1.setPosX(puyo1.getPosX() + 32);
                        puyo1.setPosY(puyo1.getPosY() + 32);
                        break;
                    case Down:
                        puyo1move = Moving.Right;
                        puyo1.setPosX(puyo1.getPosX() + 32);
                        puyo1.setPosY(puyo1.getPosY() - 32);
                        break;
                    case Right:
                        puyo1move = Moving.Up;
                        puyo1.setPosX(puyo1.getPosX() - 32);
                        puyo1.setPosY(puyo1.getPosY() - 32);
                        break;
                }
                break;
            case KeyEvent.VK_S:
                // Sキー
                switch(puyo1move)
                {
                    case Up:
                        puyo1move = Moving.Right;
                        puyo1.setPosX(puyo1.getPosX() + 32);
                        puyo1.setPosY(puyo1.getPosY() + 32);
                        break;
                    case Left:
                        puyo1move = Moving.Up;
                        puyo1.setPosX(puyo1.getPosX() + 32);
                        puyo1.setPosY(puyo1.getPosY() - 32);
                        break;
                    case Down:
                        puyo1move = Moving.Left;
                        puyo1.setPosX(puyo1.getPosX() - 32);
                        puyo1.setPosY(puyo1.getPosY() - 32);
                        break;
                    case Right:
                        puyo1move = Moving.Down;
                        puyo1.setPosX(puyo1.getPosX() - 32);
                        puyo1.setPosY(puyo1.getPosY() + 32);
                        break;
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e){}
}
