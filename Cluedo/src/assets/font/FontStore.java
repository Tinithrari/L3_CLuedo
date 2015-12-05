/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.font;

import java.io.IOException;
import java.util.HashMap;
import org.jsfml.graphics.Font;

/**
 *
 * @author Tinithrari
 */
public class FontStore {
	
	public static final String BLOODFEAST = "Bloodfeast.ttf";
	public static final String BLOODGUT = "bloodgut.ttf";
	
    private static HashMap<String, Font> fontStore;
    
    public static Font getFont(String key) throws IOException
    {
        Font font;
        
        if (fontStore == null)
            fontStore = new HashMap();
        
        if ( (font = fontStore.get(key)) == null )
        {
            font = new Font();
            font.loadFromStream(font.getClass().getClassLoader().getResourceAsStream("assets/font/" + key));
            fontStore.put(key, font);
        }
        
        return font;
    }
}
