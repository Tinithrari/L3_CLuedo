
import java.io.IOException;
import java.nio.file.Paths;

import org.jsfml.graphics.Color;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Keyboard;
import org.jsfml.window.Keyboard.Key;
import org.jsfml.window.Mouse;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

public class TestInterf{
	public static void main(String[] args)
		{
		String lettre = "";
		RenderWindow fen = new RenderWindow();
		Texture maTexture = new Texture();
		Sprite monSprite = new Sprite();
		Font bloodFeast = new Font();
		Font bloodGut = new Font();
		try{
		maTexture.loadFromStream(maTexture.getClass().getClassLoader().getResourceAsStream("Cluedo.jpg"));
		bloodFeast.loadFromStream(bloodFeast.getClass().getClassLoader().getResourceAsStream("Bloodfeast.ttf"));
		bloodGut.loadFromStream(bloodGut.getClass().getClassLoader().getResourceAsStream("Bloodgut.ttf"));
		monSprite.setTexture(maTexture);
		}catch (IOException e){
			e.printStackTrace();
                        System.exit(1);
		}
		fen.create(new VideoMode(1024, 768), "Cluedo", RenderWindow.TITLEBAR | RenderWindow.CLOSE);
		Text titre = new Text("CLUEDO", bloodFeast, 70);
		titre.setPosition(260, 20);
		titre.setColor(Color.BLACK);
		titre.setStyle(Text.BOLD | Text.UNDERLINED);
		while(fen.isOpen())
                {
                    for (Event event : fen.pollEvents())
                    {
			if(event.type == Event.Type.CLOSED)
                        {
                            fen.close();
                            System.exit(0);
                        }
                    }//for
                    fen.clear();
                    fen.draw(monSprite);
                    fen.draw(titre);
                    fen.display();
                    fen.setKeyRepeatEnabled(false);
		}//while
		System.out.println(lettre);
	}//main
}//class
