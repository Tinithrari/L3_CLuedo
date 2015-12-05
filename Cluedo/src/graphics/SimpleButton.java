/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import assets.font.FontStore;
import java.io.IOException;

import org.jsfml.graphics.Color;
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
public class SimpleButton extends GUIElement{

    private GUIEvent state;
    private Text text;
    private RectangleShape form;
    private Vector2f pos;
    private Color color;
    
    
    public SimpleButton(String texte, String refFont, Vector2f pos, int fontSize)
    {
        super();
        Font f;
        state = GUIEvent.RELEASED;
        int size;
        color = new Color(0x6B, 0x6B, 0x6B);
        
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
        
        form = new RectangleShape(new Vector2f(size, size - 50));
        form.setPosition(pos);
        form.setFillColor(color);
    }
    
    @Override
    public void processEvent(RenderWindow _w) {
        Vector2f mousePos = new Vector2f(Mouse.getPosition(_w));
        if (form.getGlobalBounds().contains(mousePos.x, mousePos.y))
        {
	        if (Mouse.isButtonPressed(Mouse.Button.LEFT) && state != GUIEvent.PRESSED)
	        {
	            notifyListener(GUIEvent.PRESSED);
	            state = GUIEvent.PRESSED;
	            form.setFillColor(new Color(color.r - 30, color.g - 30, color.b - 30));
	        }
	            
	        else if (! Mouse.isButtonPressed(Mouse.Button.LEFT) && state == GUIEvent.PRESSED) 
	        {
	            notifyListener(GUIEvent.CLICKED);
	            state = GUIEvent.CLICKED;
	            form.setFillColor(color);
	        }
	        else if (state != GUIEvent.PRESSED)
	        {
	        	notifyListener(GUIEvent.HOVER);
	        	state = GUIEvent.HOVER;
	        	form.setFillColor(new Color(color.r + 30, color.g + 30, color.b + 30));
	        }
        }
        else
        {
        	state = GUIEvent.RELEASED;
        	notifyListener(GUIEvent.RELEASED);
        	form.setFillColor(color);
        }
    }

    @Override
    public void draw(RenderWindow _w) {
        _w.draw(form);
        _w.draw(text);
    }
    
}
