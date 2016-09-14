package org.protege.ontoviz;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class OntoVizConstants{

   public static Font TAHOMA_FONT = new Font("Tahoma", 0, 14);
   public static String LABEL_TXT = "    |";

   public static void setUpLabel(JLabel label, ImageIcon icon, String tip){
      label.setIcon(icon);
      label.setToolTipText(tip);
      setUpLabel(label,LABEL_TXT);
   }

   public static void setUpLabel(JLabel label, String txt){
      label.setFont(OntoVizConstants.TAHOMA_FONT);
      label.setText(txt);
   }

   public static void toggleEvt(ItemEvent evt, JToggleButton tglBtn, JPanel ctrlPanel){
      if (evt.getStateChange() == evt.SELECTED){
         ctrlPanel.setVisible(true);
         tglBtn.setText("-");
      }
      else if(evt.getStateChange() == evt.DESELECTED){
         ctrlPanel.setVisible(false);
         tglBtn.setText("+");
      }
   }

   public static void setUpBtn(JButton btn, ImageIcon icon, String txt){
      btn.setIcon(icon);
      btn.setText(txt);
   }

   public static void setHeaderPanel(JPanel headerPanel, JToggleButton toggleBtn, JLabel label, int gap){
      setHeaderPanel(headerPanel, toggleBtn, label, gap, GroupLayout.DEFAULT_SIZE);
   }

   public static void setHeaderPanel(JPanel headerPanel, JToggleButton toggleBtn, JLabel label, int gap,int contGap){

      GroupLayout headerPanelLayout = new GroupLayout(headerPanel);
      headerPanel.setLayout(headerPanelLayout);

      headerPanelLayout.setHorizontalGroup(
         headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
         .addGroup(headerPanelLayout.createSequentialGroup()
             .addContainerGap()
             .addComponent(toggleBtn)
             .addGap(gap, gap, gap)
             .addComponent(label)
             .addContainerGap(contGap, Short.MAX_VALUE))
         );

      headerPanelLayout.setVerticalGroup(
         headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
         .addGroup(GroupLayout.Alignment.TRAILING, headerPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
             .addComponent(label, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
             .addComponent(toggleBtn, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)

         );

   }

}
