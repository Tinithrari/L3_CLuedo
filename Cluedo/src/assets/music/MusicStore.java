package assets.music;

import java.io.IOException;
import java.util.HashMap;

import org.jsfml.audio.Music;

public class MusicStore {
private static HashMap<String, Music> musicStore;
	
	public static Music getMusic(String key)
	{
		Music m;
		
		if (musicStore == null)
			musicStore = new HashMap<String, Music>();
		
		if ( (m = musicStore.get(key)) == null)
		{
			m = new Music();
			
			try {
				m.openFromStream(m.getClass().getClassLoader().getResourceAsStream("assets/music/" + key));
				musicStore.put(key, m);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return m;
	}
}
