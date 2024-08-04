package PuyoPuyo;
import GameLibrary.DrawPanel;
import GameLibrary.GameManager;

/** ぷよぷよゲームマネージャー */
public class PuyoGameManager extends GameManager
{
    private PuyoGameManager() {}
    private ObjMap objMap;

    public final float NORMAL_FALLING_SPPED = 2.0f;
    public final float HIGH_FALLING_SPPED = 5.0f;

    /** 自身のインスタンスを返す */
    public static PuyoGameManager getInstance()
    {
        if(instance == null)
        {
            instance = new PuyoGameManager();
        }
        return (PuyoGameManager)instance;
    }

    public void start(DrawPanel drawPanel)
    {
        // ゲームマップを生成
        objMap = new ObjMap();
        insertGameObject(objMap);
        objMap.create();

        // 操作方法テキスト
        ControlText controlText = new ControlText();
        insertGameObject(controlText);

        super.start(drawPanel);
    }

    public void restart() 
    {
        objMap.restart();
    }
}
