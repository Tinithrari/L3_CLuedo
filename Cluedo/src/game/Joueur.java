package game;


import java.util.*;

/**
 * 
 */
public abstract class Joueur {

    private String nom;
    private boolean perdu=false;
    private LinkedList<Carte> main;
    
    /**
     * Créé un joueur
     * @param nom le nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
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
     * Retourne la carte à montrer choisie par le joueur
     * @param lieu lieu suggéré
     * @param arme arme suggéré
     * @param suspect suspect suggéré
     * @return
     */
    public Carte montrerCarte(Carte lieu, Carte arme, Carte suspect) {
        LinkedList<Carte> card = null;
        if(main.contains(lieu))
            card.add(lieu);
        if(main.contains(arme))
            card.add(arme);
        if(main.contains(suspect))
            card.add(suspect);
        if(card.size()==0)
            return null;
        if(card.size()==1)
            return card.get(0);
        Scanner sc=new Scanner(System.in);
        int choix,cpt=0;
        do{
            System.out.println("Please, choose the card you want to show to the other player:\n");
            for(Carte c:card)
                System.out.println((cpt+1)+". "+c.toString());
            choix=sc.nextInt();
        }while(choix > card.size() || choix < 0);
        return card.get(choix-1);
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

}
