package cluedo;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.VideoMode;

/**
 *
 * @author Tinithrari
 */
public class CluedoGraphique {
    private RenderWindow _window;
    
    public CluedoGraphique()
    {
        _window = new RenderWindow(new VideoMode(800,600), "Cluedo - Faculté Jean Perrin", RenderWindow.TITLEBAR | RenderWindow.CLOSE);
    }
}
