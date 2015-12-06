/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import assets.font.FontStore;
import assets.texture.TextureStore;
import graphics.GUIElement.GUIEvent;
import java.io.IOException;
import org.jsfml.graphics.Color;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Sprite;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;

/**
 *
 * @author ju
 */
public class LogScene extends Scene implements GUIEventListener {
    
        private int cptplayer;
        private int maxplayer;
        private SimpleButton cancelButton;
        private SimpleButton readyButton;
        private Sprite bg;
        private Text title;
        private RectangleShape logged;
        
        public LogScene(int nbPlayers)
        {
            super();
            cptplayer = 0;
            maxplayer = nbPlayers;
            bg = new Sprite();
            try {
                bg.setTexture(TextureStore.getTexture(TextureStore.BLOODYWALL));
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            
            try {
                title = new Text("Connexion", FontStore.getFont(FontStore.BLOODGUT),40);
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(1);
            }
            logged = new RectangleShape(new Vector2f(400,50*nbPlayers));
            cancelButton = new SimpleButton("Cancel", FontStore.BLOODGUT, new Vector2f(100f,500f), 35);
            cancelButton.addActionListener(this);
            readyButton = new SimpleButton("Let's go!", FontStore.BLOODGUT, new Vector2f(600f,500f), 35);
            readyButton.addActionListener(this);
            title.setPosition(300,50);
            logged.setPosition(250,175);
            logged.setFillColor(Color.BLACK);
        }

    @Override
    public void handleEvent(RenderWindow _w) {
        cancelButton.processEvent(_w);
        readyButton.processEvent(_w);
    }

    @Override
    public void update(long delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(RenderWindow _w) {
        _w.draw(bg);
        _w.draw(title);
        _w.draw(logged);
        cancelButton.draw(_w);
        if (cptplayer == maxplayer)
            readyButton.draw(_w);
    }
    
    public void updatePlayer(RenderWindow _w, String player)
    {
        Text pl = null;
        try {
            pl = new Text(player + " connected", FontStore.getFont(FontStore.BLOODGUT),40);
            pl.setPosition(new Vector2f(300, 200+50*cptplayer++));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        _w.draw(pl);
        _w.display();
    }

    @Override
    public void actionPerformed(GUIElement.GUIEvent e, Object source) {
        if (source == cancelButton && e == GUIEvent.CLICKED)
            return;// TODO
        else if (source == readyButton && e == GUIEvent.CLICKED)
            return;// TODO
    }
        
        
    
}
