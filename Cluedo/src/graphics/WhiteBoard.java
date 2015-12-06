package graphics;

import java.io.IOException;
import java.util.ArrayList;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
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
			
			previous = new SimpleButton("Up", FontStore.BLOODGUT, new Vector2f(pos.x + BG_WIDTH - 30, pos.y + 40), 15);
			next = new SimpleButton("Down", FontStore.BLOODGUT, new Vector2f(pos.x + BG_WIDTH - 60, pos.y + BG_HEIGHT - 60), 15);
			
			previous.addActionListener(this);
			next.addActionListener(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void processEvent(RenderWindow _w) 
	{
		if (begIndex == 0)
			previous.setState(GUIEvent.DISABLED);
		else if (begIndex == message.size())
			next.setState(GUIEvent.DISABLED);
		
		previous.processEvent(_w);
		next.processEvent(_w);
	}


	@Override
	public void draw(RenderWindow _w) {
		_w.draw(bg);
		
		previous.draw(_w);
		next.draw(_w);
		
		for (int i = begIndex; i < message.size() && i < begIndex + MAX_MESSAGE; i++)
		{
			_w.draw(message.get(i));
		}
	}
	
	public void addMessage(String mes)
	{
		Text t;
		Vector2f position;
		if (mes == null)
			return;
		
		t = new Text(mes, ft);
		
		if (message.size() == 0)
		{
			position = new Vector2f(bg.getPosition().x, bg.getPosition().y + 105);
		}
		else
		{
			position = new Vector2f(message.get(message.size() - 1).getPosition().x, message.get(message.size() - 1).getPosition().y + 20);
		}
		
		t.setPosition(position);
		t.setCharacterSize(20);
		
		message.add(t);
		
		next.setState(GUIEvent.RELEASED);
	}

	@Override
	public void actionPerformed(GUIEvent e, Object source) 
	{
		if (e == GUIEvent.CLICKED && source == previous)
		{
			begIndex--;
			
			for (Text t : message)
			{
				if (message.indexOf(t) < begIndex || message.indexOf(t) >= begIndex + MAX_MESSAGE)
					t.setColor(Color.TRANSPARENT);
				else
					t.setColor(Color.WHITE);
				t.setPosition(t.getPosition().x, t.getPosition().y + 20);
			}
			next.setState(GUIEvent.RELEASED);
		}
		else if (e == GUIEvent.CLICKED && source == next)
		{
			begIndex++;
			
			for (Text t : message)
			{
				if (message.indexOf(t) < begIndex || message.indexOf(t) >= begIndex + MAX_MESSAGE)
					t.setColor(Color.TRANSPARENT);
				else
					t.setColor(Color.WHITE);

				t.setPosition(t.getPosition().x, t.getPosition().y - 20);
			}
			previous.setState(GUIEvent.RELEASED);
		}
	}
}
