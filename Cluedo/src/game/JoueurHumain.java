package game;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Représente un joueur humain
 */
public class JoueurHumain extends Joueur {

    public static LinkedList<String> armes;
    public static LinkedList<String> suspects;
    public static LinkedList<String> lieux;
    
    /**
     * Créé un joueur humain
     * @param nom Le nom du joueur
     */
    public JoueurHumain(String nom) {
        super(nom);
        loadArme();
        loadLieu();
        loadSuspect();
    }

    /**
     * Demande une commande aux joueurs
     * @return la commande saisie par le joueur
     */
    @Override
    public String commande() {
        String commande;
        
        Scanner sc = new Scanner(System.in);
        
        commande = sc.nextLine();
        return commande;
    }

    /**
     * Retourne la carte à montrer choisie par le joueur
     * @param lieu lieu suggéré
     * @param arme arme suggéré
     * @param suspect suspect suggéré
     * @return
     */
    @Override
    public Carte montrerCarte(Carte lieu, Carte arme, Carte suspect) {
        LinkedList<Carte> card = new LinkedList<Carte>();
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
        int choix,cpt=1;
        do{
            System.out.println(nom + ": Please, choose the card you want to show \n");
            for(Carte c:card)
            {
                System.out.println((cpt)+". "+c.toString());
                cpt++;
            }
            choix=sc.nextInt();
            
            cpt=1; //Initialise le compteur au cas ou l'on choisit une mauvaise touche
        }while(choix > card.size() || choix < 0);
        return card.get(choix-1);
    }

    @Override
    public void afficherMessage(String message) {
        if (message == null)
            throw new NullPointerException("A message must be specified");
        
        System.out.println(message);
    }

    public void displayHelp(LinkedList<String> lieux, LinkedList<String> armes, LinkedList<String> suspects) {
        System.out.println("show");
        System.out.println("\t show your cards and status");

        System.out.println("move");
        System.out.println("\t <action> <Suspect> <Weapon> <Place>");
        System.out.println("\t available actions: suggest, accuse");

        System.out.println("exit");
        System.out.println("\t Leave the program");

        System.out.println("help");
        System.out.println("\t Show this message");

        System.out.println("Available cards: ");
        System.out.println("Suspects\t\t\tPlaces\t\t\tWeapons");

        for (int i = 0; i < lieux.size(); i++) 
        {
            int nombreTabulation;
            String mot = "";
            
            if (i < suspects.size()) 
            {
                mot = suspects.get(i);
                System.out.print(mot);
            }
            if(mot.equals(""))
            {
                for(int j = 0; j < 4 - mot.length()/4  ; j++)
                {
                    System.out.print("\t");
                }
            }
            
            else
            {
                for(int j = 0; j <= 4 - mot.length()/4  ; j++)
                {
                    System.out.print("\t");
                }
            }
           
            mot = lieux.get(i);
            
            System.out.print(mot);          
            
            for(int j = 0; j < 4 - mot.length()/4  ; j++)
                System.out.print("\t");
            
            if(mot.length() == 12)
                System.out.print("\t");
            
            if (i < armes.size())
                System.out.print(armes.get(i));
            
            System.out.println("");
        }
    }
    
    /**
     * Charge les cartes armes à partir du fichier arme.tt dans le package data
     */
    private void loadArme()
    {
        if (armes == null)
        {
            armes = new LinkedList<String>();
            try {
                BufferedReader bR = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/arme.txt")));
                String line;

                while ((line = bR.readLine()) != null)
                {
                    armes.add(line);
                }
            } catch (IOException ex) {
                System.err.println("The file doesn't exist ...");
                System.exit(1);
            }
        }
    }
    
    /**
     * Charge les cartes suspects à partir du fichier suspect.txt dans le package data
     */
    private void loadSuspect()
    {
        if (suspects == null)
        {
            suspects = new LinkedList<String>();
            try {
                BufferedReader bR = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/suspect.txt")));
                String line;

                while ((line = bR.readLine()) != null)
                {
                    suspects.add(line);
                }
            } catch (IOException ex) {
                System.err.println("The file doesn't exist ...");
                System.exit(1);
            }
        }
    }
    
    /**
     * Charge les cartes lieu à partir du fichier lieu.txt dans le package data
     */
    private void loadLieu()
    {
        if (lieux == null)
        {
            lieux = new LinkedList<String>();
            try {
                BufferedReader bR = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/lieu.txt")));
                String line;

                while ((line = bR.readLine()) != null)
                {
                    lieux.add(line);
                }
            } catch (IOException ex) {
                System.err.println("The file doesn't exist ...");
                System.exit(1);
            }
        }
    }
}