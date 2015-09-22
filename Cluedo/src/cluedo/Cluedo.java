package cluedo;

import Game.Jeu;

/**
 * Représente l'interface de lancement du cluedo
 * @author Tinithrari
 */
public class Cluedo {

    private Jeu jeu;
    
    /**
     * Permet de construire une instance du jeu
     */
    public Cluedo()
    {
       // jeu = new Jeu();
    }
    
    /**
     * Permet d'exécuter le jeu
     */
    public void run()
    {
        
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        Cluedo game = new Cluedo();
        
        game.run();
    }
    
}
