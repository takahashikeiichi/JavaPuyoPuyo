package GameLibrary;
import java.util.ArrayList;
import java.util.List;
/**
 * ゲームの管理を行う
 */
public class GameManager
{
    private List<GameObject> gameObjectList = new ArrayList<>();
    protected static GameManager instance = null;
    private boolean isExit = false;

    protected GameManager() {}

    /** 自身のインスタンスを返す */
    public static GameManager getInstance()
    {
        if(instance == null)
        {
            instance = new GameManager();
        }
        return instance;
    }

    /** ゲーム開始 */
    public void start(DrawPanel drawPanel)
    {
        // ゲームループ
        while(!isExit)
        {
            drawPanel.repaint();
            drawPanel.clearDrawList();
            //for(GameObject obj : gameObjectList)
            for(int i = 0; i < gameObjectList.size(); ++i)
            {
                GameObject obj = gameObjectList.get(i);
                obj.update();
                obj.draw();
            }

            try
            {
                // 60FPS
                Thread.sleep((long)1000 / 60);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }      
        }
    }

    /** 指定の種類のゲームオブジェクト群を取得する */
    public List<GameObject> getGameObjects(GameObject.ObjectType type)
    {
        List<GameObject> result = new ArrayList<>();
        for(GameObject obj : gameObjectList)
        {
            if(obj.getObjectType() == type)
            {
                result.add(obj);
            }
        }

        return result;
    }

    /**
     * ゲームオブジェクトを追加する
     * @param obj ゲームオブジェクト
     */
    public void insertGameObject(GameObject obj)
    {
        gameObjectList.add(obj);
    }

    /** ゲームオブジェクトを削除する */
    public void deleteGameObject(GameObject obj)
    {
        gameObjectList.remove(obj);
    }
}
