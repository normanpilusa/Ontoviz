package org.protege.ontoviz;

import javax.swing.*;
import java.awt.event.*;
public class GUIActions implements ActionListener {

   public static void labelAction(String command){
      switch(command){
         case "Open":
         case "Save":
         case "Zoom In":
         case "Zoom Out":
         case "Screenshot":
            JOptionPane.showMessageDialog(null, command);
            break;
      }
   }

   public void actionPerformed(ActionEvent e) {
      String command = e.getActionCommand();

      switch(command){
         case "Grid":
         case "Radial":
         case "Vertical":
         case "Horizontal":
            JOptionPane.showMessageDialog(null, command);
            break;
      }

   }
}
