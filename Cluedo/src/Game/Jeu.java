package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

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
     */
    public Jeu() {
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
    }

    /**
     * Mélange le paquet de carte
     */
    private void melanger() {
        Collections.shuffle(paquet);
    }

    /**
     * Distribue les cartes aux différents joueurs
     */
    private void distribuer() {
        // TODO implement here
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
    public void suggerer(Joueur joueur, Lieu lieu, Arme arme, Suspect meurtrier) {
        // TODO implement here
    }

    /**
     * Permet d'émettre une accusation
     * @param joueur Le joueur effectuant l'accusation
     * @param lieu Le lieu présumé du crime
     * @param arme L'arme présumé du crime
     * @param meurtrier Le meutrier présumé
     * @return true si l'accusation est correcte, false sinon
     */
    public boolean accuser(Joueur joueur, Lieu lieu, Arme arme, Suspect meurtrier) {
        // TODO implement here
        return false;
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