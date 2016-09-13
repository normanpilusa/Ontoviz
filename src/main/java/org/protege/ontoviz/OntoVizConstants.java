import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class OntoVizConstants{

   
//   public static Dimension INFOPANELDIM = new Dimension(402, 546);


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
   
   public static MouseAdapter getAdapter(JLabel label){
      return 
         new MouseAdapter(){
            public void mouseClicked(MouseEvent evt){
               GUIActions.labelAction(label.getText());
            }
         };
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
   

   

}