package cluedo;

import graphics.Scene;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

/**
 *
 * @author Tinithrari
 */
public class CluedoGraphique {
    private RenderWindow _window;
    private static Scene _currentScene;
    
    public CluedoGraphique(Scene scene)
    {
        _window = new RenderWindow(new VideoMode(1024,768), "Cluedo - Faculté Jean Perrin", RenderWindow.TITLEBAR | RenderWindow.CLOSE);
        this._currentScene = scene;
    }
    
    public void processEvent()
    {
    	Event e;
    	
    	while ( (e = _window.pollEvent() ) != null)
    	{
    		if (e.type == Event.Type.CLOSED)
    			_window.close();
    	}
        _currentScene.handleEvent(_window);
    }
    
    public void update(long delta)
    {
        _currentScene.update(delta);
    }
    
    public void render()
    {
        _window.clear();
        _currentScene.render(_window);
        _window.display();
    }
    
    public void run()
    {
    	long delta = 0, lastFrameRefresh = System.currentTimeMillis(), actualRefreshTime = System.currentTimeMillis();
    	final float waitTime = 1000/60;
    	while(_window.isOpen())
    	{
    		processEvent();
    		update(delta);
    		render();
    		actualRefreshTime = System.currentTimeMillis();
    		delta = actualRefreshTime - lastFrameRefresh;
    		
    		if (delta / 60 < waitTime)
    		{
				try 
    			{
					Thread.sleep( (long) (waitTime - (delta / 60)) );
				} 
    			catch (InterruptedException e) 
    			{
					e.printStackTrace();
				}
    		}
    	}
    }
    
    public static void setScene(Scene scene)
    {
        _currentScene = scene;
    }
}
