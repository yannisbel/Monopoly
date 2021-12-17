package JeuxMonopoly;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
        
public class Main {

    public static int indiceMax(ArrayList<Integer> t){
            int m = 0;
            for(int i = 1; i < t.size();i++){
                if(t.get(i)> t.get(m)){
                    m = i;
                }
            }
            return m;
    }
    
    public static void main(String[] args) {
        Plateau Plato = new Plateau();
        System.out.println("Vous venez de débuter une partie de Monopoly");
        Scanner sc = new Scanner(System.in);
        int nbJoueur = 0;
        ArrayList<Joueur> temporaire = new ArrayList();
        while(true){
            try{
                System.out.println("Veuillez entrer 0 pour débuter la partie "
                                             + "ou 1 pour ajouter un joueur ");
                int code = sc.nextInt();
                sc.nextLine();
                if (code == 0){
                    if (nbJoueur > 1){
                        break;
                    }
                    else{
                        System.out.println("Il faut au minimum deux joueur pour "
                                                        + "débuter la partie ");
                    }
                }
                else{
                    System.out.println("Nom du joueur : ");
                    String nom = sc.nextLine();
                    temporaire.add(new Joueur(nom,1 + nbJoueur, Plato));
                    nbJoueur++;
                    if(nbJoueur == 6){
                        System.out.println("Nombre maximal de joueur atteint");
                        break;
                    }
                }
                
                
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        ArrayList<Integer> ordre = new ArrayList();
        for(int i = 0; i<nbJoueur; i++){
            ordre.add(Des.lancer() + Des.lancer());
            System.out.println(temporaire.get(i).getNom() + "a obtenu " + ordre.get(i));
        }
        

        for(int i = 0; i < nbJoueur; i++){
            int j = indiceMax(ordre);
            temporaire.get(j).setId(i);
            Plato.addPlayer(temporaire.get(j));
            temporaire.remove(j);
            ordre.remove(j);
        }
        
        int iterateur = 0;
        while( nbJoueur > 1){
            Plato.getListeJoueur().get(iterateur).jouer();
            if(nbJoueur == Plato.getListeJoueur().size()){
                iterateur = (iterateur + 1) % nbJoueur;
            }
            else{
                nbJoueur = Plato.getListeJoueur().size();
            }
        }
        System.out.println("Felicitation " + Plato.getListeJoueur().get(0)
                                + ", vous remportez la partie");
    }
    
}
