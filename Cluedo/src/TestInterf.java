
import cluedo.CluedoGraphique;
import graphics.MenuScene;

public class TestInterf{

	public static void main(String[] args)
		{
		MenuScene scene = new MenuScene();
		CluedoGraphique cluedo = new CluedoGraphique(scene);
		
		cluedo.run();
	}//main
}//class
