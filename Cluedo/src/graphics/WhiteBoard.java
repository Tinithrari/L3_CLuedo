package graphics;

import java.io.IOException;
import java.util.ArrayList;

import org.jsfml.graphics.ConstView;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.View;
import org.jsfml.system.Vector2f;

import assets.font.FontStore;
import assets.texture.TextureStore;

public class WhiteBoard extends GUIElement implements GUIEventListener{
	
	private static final int BG_WIDTH = 141;
	private static final int BG_HEIGHT = 768;
	private final int MAX_MESSAGE = 10;
	
	private Sprite bg;
	private Font ft;
	
	private SimpleButton previous;
	private SimpleButton next;
	
	private ArrayList<Text> message;
	private Vector2f position;
	
	private int begIndex;
	
	public WhiteBoard(Vector2f pos)
	{
		try {
			bg = new Sprite();
			bg.setTexture(TextureStore.getTexture(TextureStore.WHITEBOARD));
			ft = FontStore.getFont(FontStore.ERASER);
			
			message = new ArrayList<Text>();
			bg.setPosition(pos);
			begIndex = 0;
			
			previous = new SimpleButton("↑", FontStore.BLOODGUT, new Vector2f(105, 0), 35);
			next = new SimpleButton("↓", FontStore.BLOODGUT, new Vector2f(105,675), 35);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void processEvent(RenderWindow _w) {
		// TODO Auto-generated method stub
		
		previous.setState(begIndex == 0 ? GUIEvent.DISABLED : GUIEvent.RELEASED);
		next.setState(begIndex == 0 ? GUIEvent.DISABLED : GUIEvent.RELEASED);
	}


	@Override
	public void draw(RenderWindow _w) {
		_w.draw(bg);
	}
	
	public void addMessage(String mes)
	{
		if (mes == null)
			return;
		
		message.add(new Text(mes, ft));
	}

	@Override
	public void actionPerformed(GUIEvent e, Object source) {
		if (e == GUIEvent.CLICKED && source == previous)
		{
			
			for (Text t : message)
				t.setPosition(t.getPosition().x, t.getPosition().y - 35);
			begIndex--;
		}
		else if (e == GUIEvent.CLICKED && source == next)
		{
			
		}
	}
	
}
