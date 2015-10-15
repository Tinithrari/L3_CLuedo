/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo;

import game.Jeu;
import game.Joueur;
import game.ServeurReg;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tinithrari
 */
public class CluedoServeur 
{
    private ServeurReg server;
    private Jeu jeu;

    
    public CluedoServeur(boolean embedded) 
    {
        try {
            int nbJoueur = 0;
            Scanner sc = new Scanner(System.in);
            
            while (nbJoueur < 3 || nbJoueur > 6)
            {
                System.out.println("How many players ? (between 3 and 6)");
                nbJoueur = sc.nextInt();
            }
            
            server = new ServeurReg(12345,nbJoueur,embedded);
        } catch (IOException ex) {
            System.err.println("Error occurs during the initialization of the server");
            System.exit(1);
        }
    }
    
    public void run()
    {
        int iterator = 0;
        
        server.run();
        LinkedList<Joueur> joueurs = server.getClients();
        jeu = new Jeu(joueurs);
        
        while (! (jeu.estGagne() || partiePerdu(joueurs) ) )
        {
            try {
                jeu.effectuerTour(joueurs.get(iterator));
                
                iterator++;
                iterator %= joueurs.size();
            } catch (IOException ex) {
                for (Joueur j : joueurs)
                    try {
                        j.send("error other");
                    } catch (IOException ex1) {
                        System.exit(1);
                    }
                System.exit(1);
            }
        }
        if (partiePerdu(joueurs))
            for (Joueur j : joueurs)
                try 
                {
                    j.send("end");
                } 
                catch (IOException ex) 
                {
                    System.exit(1);
                }
    }
    
    private boolean partiePerdu(LinkedList<Joueur> joueurs)
    {
        boolean partiePerdu = true;
        
        for (Joueur j : joueurs)
            partiePerdu &= j.aPerdu();
        
        return partiePerdu;
    }
}
