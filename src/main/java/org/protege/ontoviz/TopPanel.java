package org.protege.ontoviz;
import javax.swing.*;
import java.awt.event.*;

import org.protege.editor.owl.ui.view.cls.AbstractOWLClassViewComponent;
import org.protege.ontoviz.common.GraphController;
import org.protege.ontoviz.actions.*;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;
import ca.uvic.cs.chisel.cajun.actions.*;

public class TopPanel extends JToolBar{

   private TopPanel(AbstractOWLClassViewComponent comp, GraphController controller){
      super();
      FlatGraph graph = (FlatGraph) controller.getGraph();

      setBackground(OntoVizColors.PRIMARY_LIGHTER);
      setBorder(BorderFactory.createLineBorder(OntoVizColors.BLACK));

      JFrame mainWindow = (javax.swing.JFrame)SwingUtilities.windowForComponent(comp);

      zoomInButton = this.add(new ZoomInAction(graph.getCamera()));
      addSeparator();
      zoomOutButton= this.add(new ZoomOutAction(graph.getCamera()));
      addSeparator();
      saveButton = this.add(new SaveGraphAction(mainWindow, controller));
      addSeparator();
      scrShotButton = this.add(new ExportImageAction(mainWindow, controller.getGraph().getCanvas()));
      addSeparator();
      openButton = this.add(new OpenGraphAction(mainWindow, controller));

      OntoVizConstants.setUpButton(openButton, OntoVizIcons.ICON_OPEN, "Open");
      OntoVizConstants.setUpButton(saveButton, OntoVizIcons.ICON_SAVE, "Save");
      OntoVizConstants.setUpButton(scrShotButton, OntoVizIcons.ICON_SCR_SHOT, "Screenshot");
      OntoVizConstants.setUpButton(zoomOutButton, OntoVizIcons.ICON_ZOOM_OUT, "Zoom Out");
      OntoVizConstants.setUpButton(zoomInButton, OntoVizIcons.ICON_ZOOM_IN, "Zoom In");


      GroupLayout topPanelLayout = new GroupLayout(this);
      setLayout(topPanelLayout);
      topPanelLayout.setHorizontalGroup(
         topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
         .addGroup(topPanelLayout.createSequentialGroup()
             .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
             .addComponent(zoomInButton)
             .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
             .addComponent(zoomOutButton, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
             .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
             .addComponent(scrShotButton)
             .addGap(18, 18, 18).addComponent(saveButton)
             .addGap(18, 18, 18).addComponent(openButton))
         );
      topPanelLayout.setVerticalGroup(
         topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
         .addComponent(zoomInButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         .addGroup(GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
             .addGap(0, 0, Short.MAX_VALUE)
             .addGroup(topPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                 .addComponent(openButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                 .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                 .addComponent(scrShotButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
             .addGap(2, 2, 2))
         .addComponent(zoomOutButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         );
   }

   public static TopPanel getTopPanel(AbstractOWLClassViewComponent comp, GraphController controller){
     return new TopPanel(comp, controller);
   }

   private JButton zoomOutButton, zoomInButton, scrShotButton, saveButton, openButton;
}
