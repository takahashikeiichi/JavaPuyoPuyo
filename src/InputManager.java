import java.awt.event.KeyEvent;
import java.util.ArrayList;

interface KeyInput
{
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
}

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

    public void keyPressed(KeyEvent e)
    {
        keyInputs.forEach(input -> input.keyPressed(e));
    }

    public void keyReleased(KeyEvent e)
    {
        keyInputs.forEach(input -> input.keyReleased(e));
    }

    public void addKeyInput(KeyInput keyInput)
    {
        keyInputs.add(keyInput);
    }

    public void removeKeyInput(KeyInput keyInput)
    {
        keyInputs.remove(keyInput);
    }
}
