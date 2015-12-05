package assets.sound;

import java.io.IOException;
import java.util.HashMap;

import org.jsfml.audio.SoundBuffer;

public class SoundStore {
	private static HashMap<String, SoundBuffer> soundStore;
	
	public static String LOSE = "Lose.ogg";
	
	public static SoundBuffer getSound(String key)
	{
		SoundBuffer buffer;
		
		if (soundStore == null)
			soundStore = new HashMap<String, SoundBuffer>();
		
		if ( (buffer = soundStore.get(key)) == null)
		{
			buffer = new SoundBuffer();
			try {
				buffer.loadFromStream(buffer.getClass().getResourceAsStream("assets/sound/" + key));
				soundStore.put(key, buffer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return buffer;
	}
}
