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
    
    private String buffer;
    protected String[] joueurs;
    
    /**
     * Créé un joueur humain
     * @param nom Le nom du joueur
     */
    public JoueurHumain(String nom) {
        super(nom);
        loadArme();
        loadLieu();
        loadSuspect();
        buffer = null;
    }

    /**
     * Retourne la carte à montrer choisie par le joueur
     * @param lieu lieu suggéré
     * @param arme arme suggéré
     * @param suspect suspect suggéré
     * @return
     */
    public Carte montrerCarte(Suspect suspect, Arme arme, Lieu lieu) {
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

    /**
     * Affiche l'aide sur la sortie standard
     */
    public void displayHelp() {
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

    /**
     * Permet d'envoyer un message aux client local afin d'effectuer un traitement
     * Pour obtenir le retour, utiliser la fonction receive
     * @param message Le message à faire parvenir au client local
     */
    @Override
    public void send(String message) throws IOException {
        String[] splitted = message.split(" ");
        
        if (splitted[0].equals("start") && splitted.length == 3)
        {
            commencer(splitted);
        }
        else if (splitted[0].equals("play") && splitted.length == 1)
        {
            String command = commande();
            buffer = command;
        }
        else if (splitted[0].equals("error") && splitted.length >= 2)
        {
            erreur(splitted);
        }
        else if (splitted[0].equals("move") )
        {
            int numero_joueur = Integer.parseInt(splitted[2]);
            
            System.out.print(joueurs[numero_joueur] + " :");
            
            for (int i = 3; i < splitted.length; i++)
            	System.out.print(" " + splitted[i]);
            
            System.out.print("\n");
        }
        else if (splitted[0].equals("ask") && splitted.length == 4)
        {
            if (suspects.contains(splitted[1]) && armes.contains(splitted[2]) && lieux.contains(splitted[3]))
            {
                Carte card = montrerCarte(new Suspect(splitted[1]), new Arme(splitted[2]), new Lieu(splitted[3]));
            	
                buffer = "respond";
                if (card != null)
                    buffer += " " + card.toString();
            }
        }
        else if (splitted[0].equals("info"))
        {
            String affichage = message.substring(splitted[0].length());
            System.out.println(affichage);
        }
        else if (splitted[0].equals("end"))
        {
            if (splitted.length == 1)
                System.out.println("Nobody has won");
            else
            {
                int num_joueur = Integer.parseInt(splitted[1]);
                System.out.println(joueurs[num_joueur] + " has won the game");
            }
        }
    }

    /**
     * Permet de recevoir la réponse du client local à une requête
     * @return La réponse du client
     */
    @Override
    public String receive() throws IOException {
        String tmp = buffer;
        buffer = null;
        return tmp;
    }
    
    /**
     * Sous-fonction permettant de gérer un début de jeu
     * @param splitted le tableau de chaine de la requête
     */
    public void commencer(String[] splitted)
    {
        String[] cartes;
        joueurs = splitted[1].split(",");
        cartes = splitted[2].split(",");

        for (String c : cartes) {
            if (suspects.contains(c)) 
            {
                this.addCard(new Suspect(c));
            } 
            else if (armes.contains(c)) 
            {
                this.addCard(new Arme(c));
            } 
            else 
            {
                this.addCard(new Lieu(c));
            }
        }
    }
    
    /**
     * Demande une commande aux joueurs
     * @return la commande saisie par le joueur
     */
    public String commande() 
    {
        String commande = null;
        
        Scanner sc = new Scanner(System.in);
        
        while (commande == null)
        {
            System.out.println("Entrer une commande");
            commande = sc.nextLine();
            
            if (commande.equals("help"))
            {
                displayHelp();
                commande = null;
            }
            else if (commande.equals("show"))
            {
                voirCartes();
                commande = null;
            }
        }
        return commande;
    }
    
    /**
     * Sous-fonction permettant de gérer les message d'erreur
     * @param splitted Le tableau de chaine de la requête
     */
    public void erreur(String[] splitted)
    {
        if (splitted[1].equals("exit"))
        {
            int num_joueur = Integer.parseInt(splitted[2]);
            System.err.println("The player " + joueurs[num_joueur] + " has leave the game, disconnecting...");
            System.exit(1);
        }
        else if (splitted[1].equals("invalid"))
        {
            System.err.print("Wrong move :");
            
            for (int i = 2; i < splitted.length; i++)
                System.err.print(" " + splitted[i]);
            System.err.print("\n");
        }
        else
        {
            System.err.println("Unexpected error occured :");
            
            for (int i = 2; i < splitted.length; i++)
                System.err.print(" " + splitted[i]);
            
            System.err.print("\n");
            System.exit(1);
        }
    }
}