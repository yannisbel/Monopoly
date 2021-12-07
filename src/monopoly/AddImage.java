/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AddImage {

 public static void main(String[] args) {
  
     //Création de JFrame
     JFrame frame = new JFrame("plateau de jeu");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(1500,1500);
     frame.setLocationRelativeTo(null);
     frame.setVisible(true);
     
     //URL de l'image
     String imgUr="monopoly-classique-plateau.jpg";
     ImageIcon icone = new ImageIcon(imgUr);
  
     //Création de JLable avec un alignement gauche
     JLabel jlabel = new JLabel(icone, JLabel.CENTER);
     
     // Création d'une zone de texte
     JLabel label = new JLabel("Joueur 1", JLabel.CENTER);
     frame.add(label);
     frame.setVisible(true);
     frame.setLocationRelativeTo(null);
     
  
     //ajouter les deux JLabel a JFrame
     frame.getContentPane().add(jlabel);
     frame.validate();
 }
}