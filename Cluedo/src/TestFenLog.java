
import assets.font.FontStore;
import assets.texture.TextureStore;
import graphics.ColoredButton;
import java.io.IOException;
import java.util.ArrayList;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.window.VideoMode;
import org.jsfml.window.event.Event;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ju
 */
public class TestFenLog {
    public static void main(String[] args)
		{
                ArrayList<Text> players = new ArrayList<>();
		String lettre = "";
		RenderWindow fen = new RenderWindow();
		Sprite monSprite = new Sprite();
                RectangleShape form = new RectangleShape(new Vector2f(400,200));
                ColoredButton annuler = new ColoredButton("Annuler", "bloodgut.ttf", new Vector2f(750,300), 20);
                
                form.setPosition(new Vector2f(250,175));
		try{
                monSprite.setTexture(TextureStore.getTexture("bloodywall.jpg"));
                
		}catch (IOException e){
			e.printStackTrace();
                        System.exit(1);
		}
                
                for(int i=0;i<3;i++)
                { 
                    try {
                        players.add(new Text((i+1)+"\tnom_joueur", FontStore.getFont("bloodgut.ttf"), 40));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.exit(1);
                    }
                    players.get(i).setPosition(300 ,200+i*40);
                    players.get(i).setColor(Color.RED);
                    players.get(i).setStyle(Text.ITALIC);
                }
                
		fen.create(new VideoMode(1024, 768), "Cluedo", RenderWindow.TITLEBAR | RenderWindow.CLOSE);

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
                    fen.draw(form);
                    for(int i=0;i<3;i++)
                        fen.draw(players.get(i));
                    annuler.draw(fen);
                    fen.display();
                    fen.setKeyRepeatEnabled(false);
		}//while
		System.out.println(lettre);
	}//main
}
