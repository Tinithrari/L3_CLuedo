/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.util.ArrayList;

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
    
    protected abstract void notifyListener();
}
