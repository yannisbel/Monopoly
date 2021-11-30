/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package monopoly;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class GestionAction extends Applet implements MouseMotionListener{
   private int x;
   private int y;

   @Override
   public void init() { 
      super.init();
      this.addMouseMotionListener(this);
   }

   @Override
   public void mouseDragged(java.awt.event.MouseEvent e) {}

   @Override
   public void mouseMoved(MouseEvent e) {
      x = e.getX();
      y = e.getY();
      repaint();
      showStatus("x = "+x+" ; y = "+y);
   }

   @Override
   public void paint(Graphics g) {
      super.paint(g);
      g.drawString("x = "+x+" ; y = "+y,20,20);
   }
}
