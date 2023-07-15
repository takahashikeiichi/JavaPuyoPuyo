public class PuyoGameManager extends GameManager
{
    private PuyoGameManager() {}

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
        insertGameObject(new ObjMap());      
        insertGameObject(new Puyo(32,0, Puyo.Color.Green));
        insertGameObject(new Puyo(64,0,Puyo.Color.Blue));

        super.start(drawPanel);
    }
}
