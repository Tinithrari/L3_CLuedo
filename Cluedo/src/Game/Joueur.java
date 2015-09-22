package Game;


import java.util.*;

/**
 * 
 */
public abstract class Joueur {

    /**
     * 
     */
    public Joueur() {
    }

    /**
     * 
     */
    private String nom;

    /**
     * 
     */
    private boolean muet;

    /**
     * 
     */
    private Set<Carte> main;

    /**
     */
    public void voirCartes() {
        // TODO implement here
    }

    /**
     * @return
     */
    public Carte montrerCarte() {
        // TODO implement here
        return null;
    }

    /**
     * @param lieu 
     * @param arme 
     * @param meurtrier 
     */
    public void suggerer(Lieu lieu, Arme arme, Suspect meurtrier) {
        // TODO implement here
    }

    /**
     * @param lieu 
     * @param arme 
     * @param meurtrier 
     */
    public void accuser(Lieu lieu, Arme arme, Suspect meurtrier) {
        // TODO implement here
    }

    /**
     * @return
     */
    public abstract String commande();

}