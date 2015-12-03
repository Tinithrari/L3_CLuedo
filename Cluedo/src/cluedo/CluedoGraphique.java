package cluedo;

import graphics.Scene;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;

/**
 *
 * @author Tinithrari
 */
public class CluedoGraphique {
    private RenderWindow _window;
    private static Scene _currentScene;
    
    public CluedoGraphique(Scene scene)
    {
        _window = new RenderWindow(new VideoMode(800,600), "Cluedo - Faculté Jean Perrin", RenderWindow.TITLEBAR | RenderWindow.CLOSE);
    }
    
    public void processEvent()
    {
        _currentScene.handleEvent(_window);
    }
    
    public void update(float delta)
    {
        _currentScene.update(delta);
    }
    
    public void render()
    {
        _window.clear();
        _currentScene.render(_window);
        _window.display();
    }
    
    public static void setScene(Scene scene)
    {
        _currentScene = scene;
    }
}
