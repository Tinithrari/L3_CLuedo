package graphics;

import java.io.IOException;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;

import assets.font.FontStore;
import assets.texture.TextureStore;

public class WhiteBoard extends GUIElement implements GUIEventListener{
	
	private static final int BG_WIDTH = 141;
	private static final int BG_HEIGHT = 768;
	private static final int VERTICAL_OFFSET = 30;
	
	private Sprite bg;
	private Font ft;
	private Vector2f pos;
	private View view;
	
	public WhiteBoard(Vector2f pos)
	{
		try {
			bg = new Sprite(TextureStore.getTexture(TextureStore.WHITEBOARD));
			ft = FontStore.getFont(FontStore.ERASER);
			this.pos = pos;
			
			view = new View(new FloatRect(pos.x, pos.y, pos.x + BG_WIDTH, pos.y + BG_HEIGHT - VERTICAL_OFFSET));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void processEvent(RenderWindow _w) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(RenderWindow _w) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(GUIEvent e, Object source) {
		// TODO Auto-generated method stub
		
	}
	
}
