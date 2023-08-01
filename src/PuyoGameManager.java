public class PuyoGameManager extends GameManager
{
    private PuyoGameManager() {}

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
        // ゲームの初期配置を行う
        ObjMap objMap = new ObjMap();
        insertGameObject(objMap);
        objMap.init();

        super.start(drawPanel);
    }
}
