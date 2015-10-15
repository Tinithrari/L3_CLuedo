/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cluedo;

import java.util.Scanner;

/**
 *
 * @author Tinithrari
 */
public class Cluedo {
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            CluedoSolo game = new CluedoSolo();
            Scanner sc = new Scanner(System.in);
            int nbJoueur = 0;

            while (nbJoueur < 3 || nbJoueur > 6)
            {
                System.out.println("How many players ? (between 3 and 6)");
                nbJoueur = sc.nextInt();
            }
            game.run(nbJoueur);
        }
        else
        {
            if (args.length == 1 && args[0].equals("-server"))
            {
               CluedoServeur jeu = new CluedoServeur(false);
               jeu.run();
            }
            else if (args.length == 1 && args[0].equals("-client"))
            {
                CluedoClient jeu = new CluedoClient();
                jeu.run();
            }
            else if (args.length == 2 && args[0].equals("-server") && args[1].equals("-embedded"))
            {
                CluedoServeur jeu = new CluedoServeur(true);
                jeu.run();
            }
            else
            {
                System.err.println("Lancement possible :");
                System.err.println("Cluedo.jar");
                System.err.println("Cluedo.jar -client");
                System.err.println("Cluedo.jar -server");
                System.err.println("Cluedo.jar -server -embedded");
                System.exit(2);
            }
        }
    }
}
