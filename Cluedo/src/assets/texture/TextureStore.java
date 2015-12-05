/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assets.texture;

import java.io.IOException;
import java.util.HashMap;
import org.jsfml.graphics.Texture;

/**
 *
 * @author Tinithrari
 */
public class TextureStore {
    private static HashMap<String, Texture> textureStore;
    
    public static final String BLOODYWALL = "bloodywall.jpg";
    public static final String MAINMENU = "MainMenu.png";
    public static final String WHITEBOARD = "WhiteBoard.png";
    
    public static Texture getTexture(String key) throws IOException
    {
        Texture tex;
        
        if (textureStore == null)
            textureStore = new HashMap();
        
        if ( (tex = textureStore.get(key)) == null )
        {
            tex = new Texture();
            tex.loadFromStream(tex.getClass().getClassLoader().getResourceAsStream("assets/texture/" + key));
            textureStore.put(key, tex);
        }
        
        return tex;
    }
}
