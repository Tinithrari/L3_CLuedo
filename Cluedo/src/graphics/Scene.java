/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import org.jsfml.graphics.RenderWindow;

/**
 *
 * @author Tinithrari
 */
public abstract class Scene{
    
    public abstract void handleEvent(RenderWindow _w);
    public abstract void update(long delta);
    public abstract void render(RenderWindow _w);
}
