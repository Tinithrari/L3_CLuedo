package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Classe représentant les composantes principales du jeu cluedo
 */
public class Jeu {

    private Stack<Carte> paquet;
    private LinkedList<Joueur> joueurs;
    private Crime crime;
    private boolean gagne;
    
    private LinkedList<String> armes;
    private LinkedList<String> lieux;
    private LinkedList<String> suspects;
    
    /**
     * Créer et initialise le jeu ainsi que ses composantes
     * @param  joueurs La liste des joueurs participant au jeux
     */
    public Jeu(LinkedList<Joueur> joueurs) {
        
        this.joueurs = joueurs;
        gagne = false;
        
        paquet = new Stack<Carte>();
        armes = new LinkedList<String>();
        lieux = new LinkedList<String>();
        suspects = new LinkedList<String>();
        
        loadArme();
        loadLieu();
        loadSuspect();
        
        creerCrime();
        melanger();
        distribuer();
        ordreJoueurs();
    }

    public Jeu() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Permet de savoir si le jeu a été gagné
     * @return true si oui, false sinon
     */
    public boolean estGagne() {
        return gagne;
    }

    /**
     * Permet d'obtenir la liste des joueurs
     * @return la liste des joueurs
     */
    public LinkedList<Joueur> getJoueurs() {
        return joueurs;
    }
    
    /**
     * Mélange le paquet de carte
     */
    private void melanger() {
        Collections.shuffle(paquet);
    }
    
    private void ordreJoueurs(){
    	Collections.shuffle(joueurs);
    }

    /**
     * Distribue les cartes aux différents joueurs
     */
    private void distribuer() 
    {
    	int iterator = 0;
        
        while (! paquet.empty())
        {
            joueurs.get(iterator).addCard(paquet.pop());
            iterator++;
            iterator %= joueurs.size();
        }
    }
    
    /**
     * Permet de créer le crime
     */
    private void creerCrime() {
        
        // On choisit au hasard une arme, un lieu et un suspect
        Arme arme = new Arme( armes.get((int) (Math.random() * armes.size())  ) );
        Lieu lieu = new Lieu( lieux.get((int) (Math.random() * lieux.size()) ) );
        Suspect suspect = new Suspect(suspects.get((int) (Math.random() * suspects.size()) ) );
        
        // On créé le crime
        crime = new Crime(arme, suspect, lieu);
        
        // On retire les cartes du paquet
        paquet.remove(arme);
        paquet.remove(lieu);
        paquet.remove(suspect);
    }

    /**
     * Permet d'effectuer une suggestion
     * @param joueur Le joueur ayant effectuer une suggestion
     * @param lieu le lieu suggérer
     * @param arme l'arme suggérer
     * @param meurtrier le suspect
     */
     
    /**
     * Bascule l'état du jeu en mode suggestion
     * @param joueur Le joueur ayant fait la suggestion
     * @param meurtrier Le suspect
     * @param lieu le lieu présumé
     * @param arme l'arme présumé
     */
    public void suggerer(Joueur joueur, Suspect meurtrier, Lieu lieu, Arme arme) {
    	
        int nbJoueurDemande = 0;
        // Récupère l'index du joueur ayant fait une suggestion
        int index = joueurs.indexOf(joueur);
        
        // Passe au joueur suivant
        index++;
        index %= joueurs.size();
        
        
        Carte c = null;
        // Tant que le joueur ne dit pas qu'il n'a pas de carte correspondant à la suggestion et que l'on a pas demandé à tout les joueurs
        while ( (c == null && nbJoueurDemande != joueurs.size() - 1))
        {
            if(joueurs.get(index) != joueur)
            {
                c = joueurs.get(index).montrerCarte(lieu, arme, meurtrier);
                
                if (c!=null)
                    System.out.println(joueurs.get(index).getNom() + " show the card " + c);
                else
                    System.out.println(joueurs.get(index).getNom() + " have not one of them");
               
                // On augmente le nombre de joueurs auquel on a posé la question
                nbJoueurDemande++;
            }
                // On passe aux joueurs suivant
                index++;
                index %= joueurs.size();          
        }
        if (c != null)
            System.out.println(joueurs.get(index).getNom() + ": I disprove your suggestion: " + c);
    }

    /**
     * Permet d'émettre une accusation
     * @param joueur Le joueur effectuant l'accusation
     * @param lieu Le lieu présumé du crime
     * @param arme L'arme présumé du crime
     * @param meurtrier Le meutrier présumé
     * @return true si l'accusation est correcte, false sinon
     */
    public boolean accuser(Joueur joueur, Suspect meurtrier, Lieu lieu, Arme arme) {
        return crime.getArme().equals(arme) && crime.getLieu().equals(lieu) && crime.getMeurtrier().equals(meurtrier);
    }

    /**
     * Effectue le tour d'un joueur
     * @param j Le joueur qui doit joueur son tour
     */
    public void effectuerTour(Joueur j)
    {
        if(!j.aPerdu()){
            
            System.out.println("Turn of " + j.getNom());
            String command;
            boolean finDuTour = false;

            while(!finDuTour)
            {
                String[] splittedCommand; 
                
                System.out.println("Enter a command");
                command = j.commande();
                
                splittedCommand = command.split(" ");
                
                switch (splittedCommand[0]) {
                    case "move":
                        finDuTour = gererCommandeMove(splittedCommand, j);
                        break;
                    case "show":
                        j.voirCartes();
                        break;
                    case "exit":
                        System.exit(0);
                    case "help":
                        displayHelp();
                        break;
                    default:
                        System.out.println("Wrong command, please use help to see valid command");
                        break;
                }
            }  
        }
        
        else
            System.out.println(j.getNom() + " has lost, he can't play anymore");
    }
    /**
     * Charge les cartes armes à partir du fichier arme.tt dans le package data
     */
    private void loadArme()
    {
        try {
            BufferedReader bR = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/arme.txt")));
            String line;
            
            while ((line = bR.readLine()) != null)
            {
                paquet.add(new Arme(line));
                armes.add(line);
            }
        } catch (IOException ex) {
            System.err.println("The file doesn't exist ...");
            System.exit(1);
        }
    }
    
    /**
     * Charge les cartes lieu à partir du fichier lieu.txt dans le package data
     */
    private void loadLieu()
    {
        try {
            BufferedReader bR = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/lieu.txt")));
            String line;
            
            while ((line = bR.readLine()) != null)
            {
                paquet.add(new Lieu(line));
                lieux.add(line);
            }
        } catch (IOException ex) {
            System.err.println("The file doesn't exist ...");
            System.exit(1);
        }
    }
    
    /**
     * Charge les cartes suspects à partir du fichier suspect.txt dans le package data
     */
    private void loadSuspect()
    {
        try {
            BufferedReader bR = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/suspect.txt")));
            String line;
            
            while ((line = bR.readLine()) != null)
            {
                paquet.add(new Suspect(line));
                suspects.add(line);
            }
        } catch (IOException ex) {
            System.err.println("The file doesn't exist ...");
            System.exit(1);
        }
    }
    
    /**
     * Affiche l'aide du jeu
     */
    private void displayHelp()
    {
        System.out.println("show");
        System.out.println("\t show your cards and status");

        System.out.println("move");
        System.out.println("\t <action> <Suspect> <Place> <Weapon>");
        System.out.println("\t available actions: suggest, accuse");

        System.out.println("exit");
        System.out.println("\t Leave the program");

        System.out.println("help");
        System.out.println("\t Show this message");

        System.out.println("Available cards: ");
        System.out.println("Suspects\t\t\tPlaces\t\t\tWeapons");

        for (int i = 0; i < lieux.size(); i++) {
            int nombreTabulation;
            String mot = "";
            
            if (i < suspects.size()) {
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
     * Gère la commande move d'un joueur
     * @param splittedCommand Un tableau contenant chaque morceau de la commande
     * @param j Le joueur ayant effectué cette commande
     */
    private boolean gererCommandeMove(String[] splittedCommand, Joueur j)
    {
        if (splittedCommand.length == 5) 
        {
            if (suspects.contains(splittedCommand[2])
                    && lieux.contains(splittedCommand[3])
                    && armes.contains(splittedCommand[4])) 
            {
                
                // On créé les cartes entré dans la commandes
                Suspect suspect = new Suspect(splittedCommand[2]);
                Lieu lieu = new Lieu(splittedCommand[3]);
                Arme arme = new Arme(splittedCommand[4]);

                // Si l'action est une suggestion
                if (splittedCommand[1].equals("suggest")) 
                {
                    suggerer(j, suspect, lieu, arme);
                    return true;
                } 
                
                // Si l'action est une accusation
                else if (splittedCommand[1].equals("accuse")) 
                {
                    if (!accuser(j, suspect, lieu, arme)) 
                    {
                        j.perdu();
                        System.out.println("Wrong accusation, " + j.getNom() + " has lost!");
                        return true;
                    } else 
                    {
                        System.out.println("The player " + j.getNom() + " has won the game");
                        gagne = true;
                        return true;
                    }
                } 

                // Instruction erronée
                else 
                {
                    System.out.println("Wrong action, please use \"suggest\" or \"accuse\"");
                }
            } 
           
            else 
            {
                System.out.println("One or severals card name are incorrect, name of card available with help");
            }
        } 
        
        else
        {
            System.out.println("Not enough arguments, usage: move <action> Suspect Place Weapon");
        }
        return false;
    }
}
