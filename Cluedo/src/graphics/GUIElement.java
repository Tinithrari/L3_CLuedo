/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.util.ArrayList;
import org.jsfml.graphics.RenderWindow;

/**
 *
 * @author Tinithrari
 */
public abstract class GUIElement {
    protected ArrayList<GUIEventListener> listeners;
    
    public GUIElement()
    {
        listeners = new ArrayList();
    }
    
    public abstract void processEvent(RenderWindow _w);
    protected abstract void notifyListener();
    
    public void addActionListener(GUIEventListener listener)
    {
        listeners.add(listener);
    }
}
