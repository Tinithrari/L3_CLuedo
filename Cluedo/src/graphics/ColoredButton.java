/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import assets.font.FontStore;
import java.io.IOException;
import org.jsfml.graphics.Font;
import org.jsfml.graphics.RectangleShape;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Text;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
import org.jsfml.window.Mouse;
import org.jsfml.window.event.Event;

/**
 *
 * @author Tinithrari
 */
public class ColoredButton extends GUIElement{

    private enum State {NONE, PRESSED};
    
    private State state;
    private Text text;
    private RectangleShape form;
    private Vector2f pos;
    
    public ColoredButton(String texte, String refFont, Vector2f pos, int fontSize)
    {
        super();
        Font f;
        int size;
        
        if (texte == null || refFont == null)
            throw new IllegalArgumentException("Un des arguments est manquants");
        
        try 
        {
            f = FontStore.getFont(refFont);
            text = new Text(texte, f);
        } 
        catch (IOException ex) 
        {
            System.err.println("La police " + refFont + " est manquante");
            System.exit(1);
        }
        
        this.pos = pos;
        text.setCharacterSize(fontSize);
        text.setPosition(pos.x + 25, pos.y + 25);
        
        size = text.getString().length() * text.getCharacterSize();
        
        form = new RectangleShape(new Vector2f(size, size + 50));
        form.setPosition(pos);
    }
    
    @Override
    public void processEvent(RenderWindow _w) {
        Vector2f mousePos = new Vector2f(Mouse.getPosition(_w));
        
        if (Mouse.isButtonPressed(Mouse.Button.LEFT) && state == State.NONE)
            if (form.getLocalBounds().contains(mousePos.x, mousePos.y))
                notifyListener(GUIEvent.PRESSED);
            
        else if (! Mouse.isButtonPressed(Mouse.Button.LEFT) && state == State.PRESSED) 
            if (form.getLocalBounds().contains(mousePos.x, mousePos.y))
                notifyListener(GUIEvent.CLICKED);
            else
                notifyListener(GUIEvent.RELEASED);
    }

    @Override
    protected void notifyListener(GUIEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(RenderWindow _w) {
        _w.draw(form);
        _w.draw(text);
    }
    
}
