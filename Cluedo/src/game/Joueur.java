package game;


import java.io.IOException;
import java.util.*;

/**
 * 
 */
public abstract class Joueur {

    private boolean perdu=false;
    protected LinkedList<Carte> main;
    protected String nom;
    private int num_joueur;
    
    /**
     * Créé un joueur
     * @param nom le nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
        this.main = new LinkedList<Carte>();
    }

    /**
     * Permet de récupérer le numéro du joueur
     * @return Le numéro du joueur
     */
    public int getNum_joueur() {
        return num_joueur;
    }

    /**
     * Permet de définir le numéro du joueur
     * @param num_joueur 
     */
    public void setNum_joueur(int num_joueur) {
        this.num_joueur = num_joueur;
    }
    
     /**
      * Obtient le nom du joueur
      * @return le nom du joueur
      */
     public String getNom(){
         return this.nom;
     }
     
    /**
     * Affiche la main du joueur
     */
    public void voirCartes() {
        for(Carte c : main)
        {
            String type;
            if (c instanceof Suspect)
                type = "suspect";
            else if (c instanceof Arme)
                type = "weapon";
            else
                type = "place";
            System.out.println(type + ": " + c.getNom());
        }
    }
    
    /**
     * Ajoute une carte dans la main du joueur
     * @param c La carte à ajouter
     */
    public void addCard(Carte c) {
        main.add(c);
    }
    
    /**
     * Permet de mettre l'état du joueur à perdu
     */
    public void perdu() {
        this.perdu = true;
    }
    
    /**
     * Indique si le joueur a perdu
     * @return true si le joueur a perdu, false sinon
     */
    public boolean aPerdu()
    {
        return this.perdu;
    }

    /**
     * Demande une commande au joueur
     * @return la commande du joueur
     */
    public abstract String commande();
    
    public abstract Carte montrerCarte(Carte lieu, Carte arme, Carte suspect);
    
    public abstract void afficherMessage(String message);
    
    public abstract void send(String message) throws IOException;
    public abstract String receive() throws IOException;
}
