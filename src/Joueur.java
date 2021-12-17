package JeuxMonopoly;

import java.util.ArrayList;
import java.util.Scanner;
import Cases.Case;
        
public class Joueur {
    private String nom;
    private int id;
    private int porteMonnaie = 150000;
    private int estEnPrison = 0;
    private boolean possedeCarteSDP = false;
    private int[] listeTerrain = new int[40];
    private int position;
    private int compteurDouble = 0;
    private boolean reponseQuestion;
    private Plateau plateau;
    
    public Joueur(String nom, int id, Plateau p) {
        this.nom = nom;
        this.id = id;
        this.plateau = p;
    }
    
    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
 
    public int getPorteMonnaie() {
        return porteMonnaie;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
     public int getId() {
        return id;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPorteMonnaie(int porteMonnaie) {
        this.porteMonnaie = porteMonnaie;
    }

    public boolean isEstEnPrison() {
        return estEnPrison > 0;
    }

    public void setEstEnPrison(int estEnPrison) {
        this.estEnPrison = estEnPrison;
    }

    public boolean isPossedeCarteSDP() {
        return possedeCarteSDP;
    }

    public void setPossedeCarteSDP(boolean possedeCarteSDP) {
        this.possedeCarteSDP = possedeCarteSDP;
    }

    public int[] getListeTerrain() {
        return listeTerrain;
    }

    public void addListeTerrain(int i) {
        listeTerrain[i] = 1;
    }
    
    public void question(String s){
        Scanner sc = new Scanner(System.in);
        System.out.println(s);
        System.out.println("entrez y si oui, n sinon : ");
        String reponse = sc.nextLine();
        reponseQuestion = reponse == "y";
    }
    
    public void avancer(int d1,int d2){
        int primeTour = 20000;
        if((position + d1 + d2) / 40 >= 1){
            System.out.println("Vous passez par la case départ, recevez " 
                                    + primeTour + " euros");
            porteMonnaie += primeTour;
        }
        position = (position + d1 + d2) % 40;
    }
    
    public int payer(int montant){
        if(porteMonnaie < montant){
            System.out.println("Votre solde est d'actuellement " + porteMonnaie
                                    + ", vous ne dispoez pas des fonds suffisant"
                                    + " pour payer cette facture");
            question("Souhaitez vous hypotéquer des biens ?");
            if(reponseQuestion || porteMonnaie == 0){
                hypotequer(montant);
                return payer(montant);
            }
            else{
                int nMontant = montant - porteMonnaie;
                setPorteMonnaie(0);
                return payer(nMontant);
            }
        }
        else{
            porteMonnaie -= montant;
            return montant;
        }
    }
    
    public void hypotequer(int montant){
        System.out.println("Vous possédez : ");
        int restant = montant;
        int terrainRestant = 0;
        for(int i = 0; i < 40; i++){
            if(listeTerrain[i] == 1){
                terrainRestant++;
                System.out.println(plateau.getCasePlateau().get(i).toString());
                question("souhaitez vous hypotéquer ce terrain ?");
                if(reponseQuestion){
                    listeTerrain[i] = -1;
                    porteMonnaie += plateau.getCasePlateau().get(i).getPrix()/2;
                    restant -= porteMonnaie;
                    System.out.println("votre nouveau solde est de "
                                        + porteMonnaie + " €."
                                        + " Il vous manque encore" 
                                        + restant + " €.");
                }
            }
        }
    }
    
    public void banqueroute(){
        plateau.getListeJoueur().remove(this);
        for(int i = 0; i < 40; i++){
            if(listeTerrain[i] != 1){
                plateau.changementProprietaire(0, i);
            }
        }
        System.out.println("Malheureusement, vos fonds sont arrivés à épuisement"
                            + ", vous etes contraint d'abandonner la partie");
    }
    
    public void jouer(){
        int d1 = Des.lancer();
        int d2 = Des.lancer();
        System.out.println("Vous avez obtenu " + (d1 + d2) + "aux dés.");
        if(isEstEnPrison()){
            if(d1 != d2){
                question("Voulez-vous payer 5000 € pour sortir de prison ?");
                if (!reponseQuestion  && estEnPrison < 3){
                    estEnPrison++;
                }
                else{
                    if (porteMonnaie < 5000){
                        hypotequer(5000);
                        if (porteMonnaie < 5000){
                            banqueroute();
                            return;
                        }
                    }
                    porteMonnaie -= 5000;
                    estEnPrison = 0;
                    jouer();
                }
            }
            else{
                compteurDouble++;
                avancer(d1,d2);
                plateau.getCasePlateau().get(position).actionCase(this,plateau);
                jouer();
            }
        }
        else{
            avancer(d1,d2);
            plateau.getCasePlateau().get(position).actionCase(this, plateau);
            if( d1 == d2){
                compteurDouble++;
                if(compteurDouble == 3){
                    position = 10;
                    estEnPrison = 1;
                    return;
                }
                jouer();
            }
        }
    }
   
}
