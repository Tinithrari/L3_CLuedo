
package graphics;

import graphics.GUIElement.GUIEvent;

import java.io.IOException;

import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.event.Event;

import assets.font.FontStore;
import assets.texture.TextureStore;

public class MenuScene extends Scene implements GUIEventListener{

	private SimpleButton soloButton;
	private SimpleButton multiButton;
	private SimpleButton quitButton;
	private SimpleButton helpButton;
	private Sprite fond;
	private Text titre;
	
	public MenuScene(){
		super();
		fond = new Sprite();
		try {
			titre = new Text("Cluedo",FontStore.getFont(FontStore.BLOODFEAST), 70);
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		try{
			fond.setTexture(TextureStore.getTexture(TextureStore.MAINMENU));
			}catch (IOException e){
				e.printStackTrace();
	            System.exit(1);
			}
		soloButton = new SimpleButton("solo", FontStore.BLOODGUT, new Vector2f(350f,200f), 35);
		soloButton.addActionListener(this);
		multiButton = new SimpleButton("onligne", FontStore.BLOODGUT, new Vector2f(350f,300f), 35);
		multiButton.addActionListener(this);
		quitButton = new SimpleButton("Quitter", FontStore.BLOODGUT, new Vector2f(350f,400f), 35);
		quitButton.addActionListener(this);
		helpButton = new SimpleButton("Help", FontStore.BLOODGUT, new Vector2f(20f,20f), 35);
		helpButton.addActionListener(this);
		
		titre.setPosition(350, 20);
	}
	
	@Override
	public void actionPerformed(GUIEvent e, Object source) {
		
		
	}

	@Override
	public void handleEvent(RenderWindow _w) {
		soloButton.processEvent(_w);
		multiButton.processEvent(_w);
		quitButton.processEvent(_w);
		helpButton.processEvent(_w);
		
	}

	@Override
	public void update(long delta) {
		
		
	}

	@Override
	public void render(RenderWindow _w) {
		_w.draw(fond);
		_w.draw(titre);
		soloButton.draw(_w);
		multiButton.draw(_w);
		quitButton.draw(_w);
		helpButton.draw(_w);
		
	}
	
	

}