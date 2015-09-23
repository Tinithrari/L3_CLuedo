package game;


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

    private String nom;
    private boolean muet=false;
    private LinkedList<Carte> main;
     
     /**
      * 
      * @return 
      */
     public String getNom(){
         return this.nom;
     }
     
    // Affiche chaque carte de la main du joueur
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
    
    public Carte montreCarte(){
        
        Scanner sc = new Scanner(System.in);
        String nom = sc.nextLine();
        
        return null;
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
            System.out.println("Please, chose the card you want to show to the other player:\n");
            for(Carte c:card)
                System.out.println((cpt+1)+". "+c.toString());
            choix=sc.nextInt();
        }while(choix > card.size() || choix < 0);
        return card.get(choix-1);
    }
    
    public void perdu() {
        this.muet = true;
    }

    /**
     * @return
     */
    public abstract String commande();

}
