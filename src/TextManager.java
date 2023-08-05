
/** テキスト管理 */
public class TextManager 
{
    private static TextManager instance = null;
    private TextManager() {}

    // インスタンスを返す
    public static TextManager getInstance()
    {
        if(instance == null)
        {
            instance = new TextManager();
        }
        return instance;
    }
}
