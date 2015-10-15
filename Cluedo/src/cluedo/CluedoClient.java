/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo;

import game.JoueurReseauClient;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tinithrari
 */
public class CluedoClient {
    
    private JoueurReseauClient client;
    
    public CluedoClient()
    {
        Scanner sc = new Scanner(System.in);
        String adresse, nom;
        
        System.out.println("Entrer votre nom : ");
        nom = sc.nextLine();
        
        System.out.println("Entrer l'adresse du serveur : ");
        adresse = sc.nextLine();
        
        try {
            client = new JoueurReseauClient(nom, new Socket(adresse, 12345));
        } catch (IOException ex) {
            System.err.println("Impossible de se connecter au serveur");
            System.exit(1);
        }
    }
    
    public void run()
    {
        try {
            client.play();
        } catch (IOException ex) {
            System.err.println("Erreur de communication, fermeture");
            System.exit(1);
        }
    }
}
