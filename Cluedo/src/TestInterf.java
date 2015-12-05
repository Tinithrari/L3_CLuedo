
import assets.font.FontStore;
import assets.texture.TextureStore;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		Sprite monSprite = new Sprite();
                Text titre = null;
		try{
		monSprite.setTexture(TextureStore.getTexture(TextureStore.MAINMENU));
		}catch (IOException e){
			e.printStackTrace();
                        System.exit(1);
		}
		fen.create(new VideoMode(1024, 768), "Cluedo", RenderWindow.TITLEBAR | RenderWindow.CLOSE);
                try {
                    titre = new Text("CLUEDO", FontStore.getFont(FontStore.BLOODFEAST), 70);
                    titre.setPosition(350, 20);
                    titre.setColor(Color.BLACK);
                    titre.setStyle(Text.BOLD | Text.UNDERLINED);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
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
