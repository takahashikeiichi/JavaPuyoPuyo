package GameLibrary;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/** 入力管理 */
public class InputManager
{
    private static InputManager instance = null;
    private InputManager() {}
    private ArrayList<KeyInput> keyInputs = new ArrayList<>();

    /** 自身のインスタンスを返す */
    public static InputManager getInstance()
    {
        if(instance == null)
        {
            instance = new InputManager();
        }
        return instance;
    }

    /**　キー入力 登録されているイベントを呼び出す */
    public void keyPressed(KeyEvent e)
    {
        keyInputs.forEach(input -> input.keyPressed(e));
    }

    /** キー離し　登録されているイベントを呼び出す */
    public void keyReleased(KeyEvent e)
    {
        keyInputs.forEach(input -> input.keyReleased(e));
    }

    /** イベントを登録 */
    public void addKeyInput(KeyInput keyInput)
    {
        keyInputs.add(keyInput);
    }

    /** イベント登録を解除 */
    public void removeKeyInput(KeyInput keyInput)
    {
        keyInputs.remove(keyInput);
    }
}
