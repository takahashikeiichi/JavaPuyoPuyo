import java.awt.event.KeyEvent;
import java.awt.Point;

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
    private PuyoGameManager gameManager = PuyoGameManager.getInstance();
    
    PuyoPairs(ObjMap map, Puyo puyo1, Puyo puyo2)
    {
        objMap = map;
        this.puyo1 = puyo1;
        this.puyo2 = puyo2;
        inputManager.addKeyInput(this);
    }

    public void update()
    {
        if(!puyo1.getIsDown() && !puyo2.getIsDown())
        {
            objMap.updateMap();
            inputManager.removeKeyInput(this);
            gameManager.deleteGameObject(this);
        }
    }

    private boolean confirmMovable(float posX, float posY)
    {
        Point puyoMapPos = objMap.getMap(posX, posY);
        if(objMap.IsOutsideRange(puyoMapPos.x, puyoMapPos.y))
        {
            // 範囲外
            return false;
        }
        
        if(objMap.PuyoExists(puyoMapPos.x, puyoMapPos.y))
        {
            // ぷよがすでにある
            return false;
        }
        return true;
    }

    public void draw() {}

    public void keyPressed(KeyEvent e)
    {
        if(puyo1 == null || !puyo1.getIsDown()) {return;}
        if(puyo2 == null || !puyo2.getIsDown()) {return;}
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                // 左キー
                // 左に移動
                if(confirmMovable(puyo1.posX - objMap.OBJECT_WIDTH, puyo1.posY + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                {
                    if(confirmMovable(puyo2.posX - objMap.OBJECT_WIDTH, puyo2.posY + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                    {
                        puyo1.setPosX(puyo1.getPosX() - objMap.OBJECT_WIDTH);
                        puyo2.setPosX(puyo2.getPosX() - objMap.OBJECT_WIDTH);
                    }
                }
                break;
            case KeyEvent.VK_RIGHT:
                // 右キー
                // 右に移動
                if(confirmMovable(puyo1.posX + objMap.OBJECT_WIDTH, puyo1.posY + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                {
                    if(confirmMovable(puyo2.posX + objMap.OBJECT_WIDTH, puyo2.posY + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                    {
                        puyo1.setPosX(puyo1.getPosX() + objMap.OBJECT_WIDTH);
                        puyo2.setPosX(puyo2.getPosX() + objMap.OBJECT_WIDTH);
                    }
                }
                
                break;
            case KeyEvent.VK_DOWN:
                // 下キー
                // 落下速度UP
                puyo1.setSpeed(gameManager.HIGH_FALLING_SPPED);
                puyo2.setSpeed(gameManager.HIGH_FALLING_SPPED);
                break;
            case KeyEvent.VK_A:
                // Aキー
                // 左回転
                switch(puyo1move)
                {
                    case Up:
                        if(confirmMovable(puyo1.posX - objMap.OBJECT_WIDTH, puyo1.posY + objMap.OBJECT_HEIGHT + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                        {
                            puyo1move = Moving.Left;
                            puyo1.setPosX(puyo1.getPosX() - objMap.OBJECT_WIDTH);
                            puyo1.setPosY(puyo1.getPosY() + objMap.OBJECT_HEIGHT);
                        }
                        else if(confirmMovable(puyo2.posX + objMap.OBJECT_WIDTH, puyo2.getPosY() - objMap.OBJECT_HEIGHT + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                        {
                            // 左端で左回転できないときは軸ぷよを反時計回りに移動する
                            // |●        | ←の時に
                            // |○        |

                            // |         | ←にする
                            // |●○       |
                            puyo1move = Moving.Left;
                            puyo2.setPosX(puyo2.getPosX() + objMap.OBJECT_WIDTH);
                            puyo2.setPosY(puyo2.getPosY() - objMap.OBJECT_HEIGHT);
                        }
                        break;
                    case Left:
                        if(confirmMovable(puyo1.posX + objMap.OBJECT_WIDTH, puyo1.posY + objMap.OBJECT_HEIGHT + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                        {
                            puyo1move = Moving.Down;
                            puyo1.setPosX(puyo1.getPosX() + objMap.OBJECT_WIDTH);
                            puyo1.setPosY(puyo1.getPosY() + objMap.OBJECT_HEIGHT);
                        }
                        break;
                    case Down:
                        if(confirmMovable(puyo1.posX + objMap.OBJECT_WIDTH, puyo1.posY - objMap.OBJECT_HEIGHT + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                        {
                            puyo1move = Moving.Right;
                            puyo1.setPosX(puyo1.getPosX() + objMap.OBJECT_WIDTH);
                            puyo1.setPosY(puyo1.getPosY() - objMap.OBJECT_HEIGHT);
                        }
                        break;
                    case Right:
                        if(confirmMovable(puyo1.posX - objMap.OBJECT_WIDTH, puyo1.posY - objMap.OBJECT_HEIGHT + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                        {
                            puyo1move = Moving.Up;
                            puyo1.setPosX(puyo1.getPosX() - objMap.OBJECT_WIDTH);
                            puyo1.setPosY(puyo1.getPosY() - objMap.OBJECT_HEIGHT);
                        }
                        break;
                }
                break;
            case KeyEvent.VK_S:
                // Sキー
                switch(puyo1move)
                {
                    case Up:
                        if(confirmMovable(puyo1.posX + objMap.OBJECT_WIDTH, puyo1.posY + objMap.OBJECT_HEIGHT + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                        {
                            puyo1move = Moving.Right;
                            puyo1.setPosX(puyo1.getPosX() + objMap.OBJECT_WIDTH);
                            puyo1.setPosY(puyo1.getPosY() + objMap.OBJECT_HEIGHT);
                        }
                        else if(confirmMovable(puyo1.posX - objMap.OBJECT_WIDTH, puyo1.posY - objMap.OBJECT_HEIGHT + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                        {
                            // |         ●| ←の時に
                            // |        〇|

                            // |          | ←にする
                            // |       〇●|
                            puyo1move = Moving.Right;
                            puyo2.setPosX(puyo2.getPosX() - objMap.OBJECT_WIDTH);
                            puyo2.setPosY(puyo2.getPosY() - objMap.OBJECT_HEIGHT);
                        }
                        break;
                    case Left:
                        if(confirmMovable(puyo1.posX + objMap.OBJECT_WIDTH, puyo1.posY - objMap.OBJECT_HEIGHT + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                        {
                            puyo1move = Moving.Up;
                            puyo1.setPosX(puyo1.getPosX() + objMap.OBJECT_WIDTH);
                            puyo1.setPosY(puyo1.getPosY() - objMap.OBJECT_HEIGHT);
                        }
                        break;
                    case Down:
                        if(confirmMovable(puyo1.posX - objMap.OBJECT_WIDTH, puyo1.posY - objMap.OBJECT_HEIGHT + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                        {
                            puyo1move = Moving.Left;
                            puyo1.setPosX(puyo1.getPosX() - objMap.OBJECT_WIDTH);
                            puyo1.setPosY(puyo1.getPosY() - objMap.OBJECT_HEIGHT);
                        }
                        break;
                    case Right:
                        if(confirmMovable(puyo1.posX - objMap.OBJECT_WIDTH, puyo1.posY + objMap.OBJECT_HEIGHT + objMap.OBJECT_COLLISION_OFFSET_HEIGHT))
                        {
                            puyo1move = Moving.Down;
                            puyo1.setPosX(puyo1.getPosX() - objMap.OBJECT_WIDTH);
                            puyo1.setPosY(puyo1.getPosY() + objMap.OBJECT_HEIGHT);
                        }
                        break;
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e)
    {
        if(puyo1 == null) {return;}
        if(puyo2 == null) {return;}
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_DOWN:
                // 下キー
                // 落下速度DOWN
                puyo1.setSpeed(gameManager.NORMAL_FALLING_SPPED);
                puyo2.setSpeed(gameManager.NORMAL_FALLING_SPPED);
                break;
        }
    }
}
