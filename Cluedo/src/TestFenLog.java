
import cluedo.CluedoGraphique;
import graphics.LogScene;

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
            LogScene scene = new LogScene(3);
            CluedoGraphique cluedo = new CluedoGraphique(scene);
		
            cluedo.run();
	}//main
}
