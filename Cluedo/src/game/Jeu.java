package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Classe représentant les composantes principales du jeu cluedo
 */
public class Jeu {

    private LinkedList<Carte> paquet;
    private LinkedList<Joueur> joueurs;
    private Crime crime;
    
    private LinkedList<String> armes;
    private LinkedList<String> lieux;
    private LinkedList<String> suspects;
    
    /**
     * Créer et initialise le jeu ainsi que ses composantes
     * @param  joueurs La liste des joueurs participant au jeux
     */
    public Jeu(LinkedList<Joueur> joueurs) {
        
        this.joueurs = joueurs;
        
        paquet = new LinkedList<Carte>();
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
    private void distribuer() {
    	int nbJ = joueurs.size();
        int p=18/nbJ; // Nombre de cartes que les joueurs auront au minimum chacun
		int k=0;
		for(int i=0;i<p;i++)
			for(int l=0;l<nbJ;l++)
				joueurs.get(l).addCard(paquet.get(k++));
		if(18%nbJ!=0) // Cas où les joueurs n'auront pas le même nombre de cartes (4 ou 5 joueurs)
			for(int a=0;a<18%nbJ;a++)
				joueurs.get(a).addCard(paquet.get(k++));
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
     
    // Affiche les cartes montrés par un joueur 
    public void suggerer(Joueur joueur, Lieu lieu, Arme arme, Suspect meurtrier) {
    	
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

    public void gestionDeCommande(Joueur j)
    {
        if(!j.aPerdu()){
            
            String command;
            boolean finDuTour = false;
            Scanner scan = new Scanner(System.in);

            while(!finDuTour)
            {
                String[] splittedCommand; 
                
                System.out.println("Enter command: ");
                command = scan.nextLine();
                
                splittedCommand = command.split(" ");
                
                if(splittedCommand[0].equals("move"))
                {
                    if(splittedCommand.length != 5)
                    {
                        if(suspects.contains(splittedCommand[2]) &&
                                    lieux.contains(splittedCommand[3]) &&
                                    armes.contains(splittedCommand[4]))
                        {                      
                            if(splittedCommand[1].equals("suggest"))
                            {

                            }
                            else if(splittedCommand[1].equals("accuse"))
                            {
                                if(!accuser(j,new Suspect(splittedCommand[2]),new Lieu(splittedCommand[3]),new Arme(splittedCommand[4])))
                                {
                                    j.perdu();
                                    System.out.println("Wrong accusation, "+j.getNom()+" has lost!");
                                }
                                else
                                {
                                    //TODO
                                }
                            }
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
                }
                
                else if(splittedCommand[0].equals("show"))
                {
                    j.voirCartes();
                }
                else if(splittedCommand[0].equals("exit"))
                {
                    System.exit(0);
                }
                else if(splittedCommand[0].equals("help"))
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
                    System.out.println("Suspects \t\t Places \t\t Weapons");
                    
                    for(int i=0;i<lieux.size();i++)
                    {
                        if(i < suspects.size())
                            System.out.print(suspects.get(i));
                        else 
                            System.out.print("\t\t");
                        System.out.print(" \t\t ");
            
                        System.out.print(lieux.get(i));
                        System.out.print(" \t\t ");
                        
                        if(i < armes.size())
                            System.out.print(armes.get(i));
                        System.out.println("");
                    }
                }
                else
                {
                    System.out.println("Wrong command, please use help to see valid command");
                }
            }  
        }
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
            System.err.println("Le fichier n'existe pas...");
            System.exit(1);
        }
    }
    
    /**
     * Charge les cartes lieu à partir du fichier lieu.txt dans le package data
     */
    private void loadLieu()
    {
        try {
            BufferedReader bR = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("data/liey.txt")));
            String line;
            
            while ((line = bR.readLine()) != null)
            {
                paquet.add(new Lieu(line));
                lieux.add(line);
            }
        } catch (IOException ex) {
            System.err.println("Le fichier n'existe pas...");
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
            System.err.println("Le fichier n'existe pas...");
            System.exit(1);
        }
    }
}
