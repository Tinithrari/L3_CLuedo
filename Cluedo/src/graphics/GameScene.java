/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import graphics.GUIElement.GUIEvent;

import org.jsfml.audio.Sound;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import assets.font.FontStore;
import assets.sound.SoundStore;

/**
 *
 * @author Tinithrari
 */
public class GameScene extends Scene implements GUIEventListener{

	private SimpleButton button;
	private WhiteBoard wB;
	private Sound player;
	
	public GameScene()
	{
		button = new SimpleButton("Test", FontStore.BLOODFEAST, new Vector2f(200f,300f), 35);
		button.addActionListener(this);
		player = new Sound();
		wB = new WhiteBoard(new Vector2f(1024-141, 0));
		
		wB.addMessage("Test1");
		wB.addMessage("Test2");
		wB.addMessage("Test3");
		wB.addMessage("Test4");
		wB.addMessage("Test5");
		wB.addMessage("Test6");
		wB.addMessage("Test7");
	}
	
    @Override
    public void handleEvent(RenderWindow _w) {
        button.processEvent(_w);
        wB.processEvent(_w);
    }

    @Override
    public void update(long delta) {
    }

    @Override
    public void render(RenderWindow _w) {
        button.draw(_w);
        wB.draw(_w);
    }

    @Override
    public void actionPerformed(GUIEvent e, Object source) {
        if (source == button && e == GUIEvent.CLICKED)
        {
        	player.setBuffer(SoundStore.getSound(SoundStore.LOSE));
        	player.play();
        }
    }
    
}
