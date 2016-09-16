package org.protege.ontoviz;

import javax.swing.*;
import java.awt.event.*;
public class GUIActions {

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

}
