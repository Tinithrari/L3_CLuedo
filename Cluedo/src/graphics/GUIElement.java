/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.util.ArrayList;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.window.event.Event;

/**
 *
 * @author Tinithrari
 */
public abstract class GUIElement {
    protected ArrayList<GUIEventListener> listeners;
    
    public enum GUIEvent {PRESSED, CLICKED, RELEASED, HOVER};
    
    public GUIElement()
    {
        listeners = new ArrayList();
    }
    
    public abstract void processEvent(RenderWindow _w);
    public abstract void draw (RenderWindow _w);
    
    protected void notifyListener(GUIEvent e)
    {
        for (GUIEventListener l : listeners)
            l.actionPerformed(e, this);
    }
    
    public void addActionListener(GUIEventListener listener)
    {
        listeners.add(listener);
    }
}
