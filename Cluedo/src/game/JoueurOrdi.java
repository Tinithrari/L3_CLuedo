package game;


import java.util.*;

/**
 * 
 */
public class JoueurOrdi extends Joueur {

    /**
     * 
     */
    public JoueurOrdi(String nom) {
        super(nom);
    }

    /**
     * @return
     */
    public String commande() {
        // TODO implement here
        return "";
    }

    @Override
    public Carte montrerCarte(Carte lieu, Carte arme, Carte suspect) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void afficherMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayHelp(LinkedList<String> lieux, LinkedList<String> armes, LinkedList<String> suspects) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}