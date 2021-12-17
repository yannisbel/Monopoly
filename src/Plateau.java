package JeuxMonopoly;

import Cases.Case;
import java.util.ArrayList;

public class Plateau {
    private ArrayList<Case> casePlateau;
    private ArrayList<Joueur> listeJoueur;
    private int[] propPlateau = new int[40];
            
    public int nbJoueur(){
        return listeJoueur.size();
    }
    
    public void changementProprietaire(int idjoueur, int idterrain){
        propPlateau[idterrain] = idjoueur;
    }
    
    public void addPlayer(Joueur j){
        listeJoueur.add(j);
    }

    public ArrayList<Case> getCasePlateau() {
        return casePlateau;
    }

    public int[] getPropPlateau() {
        return propPlateau;
    }
    
    
    public void addCase(Case c){
        casePlateau.add(c);
    }

    public ArrayList<Joueur> getListeJoueur() {
        return listeJoueur;
    }

    public void setListeJoueur(ArrayList<Joueur> listeJoueur) {
        this.listeJoueur = listeJoueur;
    }
    
    
}
