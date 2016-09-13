import javax.swing.*;
import java.awt.event.*;

public class TopPanel extends JPanel{

   private static TopPanel instance = new TopPanel();
   
   private TopPanel(){
      zoomInLabel = new JLabel();
      zoomOutLabel = new JLabel();
      scrShotLabel = new JLabel();
      saveLabel = new JLabel();
      openLabel = new JLabel();
      
      setBackground(OntoVizColors.PRIMARY_LIGHTER);
      setBorder(BorderFactory.createLineBorder(OntoVizColors.BLACK));
      
      OntoVizConstants.setUpLabel(openLabel, OntoVizIcons.ICON_OPEN, "Open");
      OntoVizConstants.setUpLabel(saveLabel, OntoVizIcons.ICON_SAVE, "Save");
      OntoVizConstants.setUpLabel(scrShotLabel, OntoVizIcons.ICON_SCR_SHOT, "Screenshot");
      OntoVizConstants.setUpLabel(zoomOutLabel, OntoVizIcons.ICON_ZOOM_OUT, "Zoom Out");
      OntoVizConstants.setUpLabel(zoomInLabel, OntoVizIcons.ICON_ZOOM_IN, "Zoom In");
      
      openLabel.addMouseListener(
         new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               GUIActions.labelAction("Open");
            }
         });
      
      saveLabel.addMouseListener(
         new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
               GUIActions.labelAction("Save");
            }
         });
      
      scrShotLabel.addMouseListener(
         new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
               GUIActions.labelAction("Screenshot");
            }
         });
      
      zoomOutLabel.addMouseListener(
         new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
               GUIActions.labelAction("Zoom Out");
            }
         });
      
      zoomInLabel.addMouseListener(
         new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
               GUIActions.labelAction("Zoom In");
            }
         });
      
      GroupLayout topPanelLayout = new GroupLayout(this);
      setLayout(topPanelLayout);
      topPanelLayout.setHorizontalGroup(
         topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
         .addGroup(topPanelLayout.createSequentialGroup()
             .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
             .addComponent(zoomInLabel)
             .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
             .addComponent(zoomOutLabel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
             .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
             .addComponent(scrShotLabel)
             .addGap(18, 18, 18).addComponent(saveLabel)
             .addGap(18, 18, 18).addComponent(openLabel))
         );
      topPanelLayout.setVerticalGroup(
         topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
         .addComponent(zoomInLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         .addGroup(GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
             .addGap(0, 0, Short.MAX_VALUE)
             .addGroup(topPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                 .addComponent(openLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                 .addComponent(saveLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                 .addComponent(scrShotLabel, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
             .addGap(2, 2, 2))
         .addComponent(zoomOutLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         );
   }

   public static TopPanel getTopPanel(){ return instance; }
   
   private JLabel zoomOutLabel, zoomInLabel, scrShotLabel, saveLabel, openLabel;
}
