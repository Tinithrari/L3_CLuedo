import cluedo.CluedoGraphique;
import graphics.GameScene;

public class TestGameScene {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameScene scene = new GameScene();
		CluedoGraphique cluedo = new CluedoGraphique(scene);
		
		cluedo.run();
	}

}
