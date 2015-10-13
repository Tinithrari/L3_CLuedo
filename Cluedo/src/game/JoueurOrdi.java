package game;


import java.io.IOException;
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
    public void send(String message) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String receive() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}