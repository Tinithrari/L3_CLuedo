/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import graphics.GUIElement.GUIEvent;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import assets.font.FontStore;

/**
 *
 * @author Tinithrari
 */
public class GameScene extends Scene implements GUIEventListener{

	private SimpleButton button;
	
	public GameScene()
	{
		button = new SimpleButton("Test", FontStore.BLOODFEAST, new Vector2f(200f,300f), 35);
		button.addActionListener(this);
	}
	
    @Override
    public void handleEvent(RenderWindow _w) {
        button.processEvent(_w);
    }

    @Override
    public void update(long delta) {
    }

    @Override
    public void render(RenderWindow _w) {
        button.draw(_w);
    }

    @Override
    public void actionPerformed(GUIEvent e, Object source) {
        if (source == button && e == GUIEvent.CLICKED)
        	System.out.println("Coucou");
    }
    
}
