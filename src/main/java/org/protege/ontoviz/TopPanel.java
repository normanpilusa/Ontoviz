package org.protege.ontoviz;

import ca.uvic.cs.chisel.cajun.actions.FocusOnHomeAction;
import ca.uvic.cs.chisel.cajun.actions.LayoutAction;
import ca.uvic.cs.chisel.cajun.actions.ZoomInAction;
import ca.uvic.cs.chisel.cajun.actions.ZoomOutAction;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;

import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
import org.protege.editor.owl.ui.view.cls.AbstractOWLClassViewComponent;
import org.protege.ontoviz.actions.ExportImageAction;
import org.protege.ontoviz.actions.OpenGraphAction;
import org.protege.ontoviz.actions.SaveGraphAction;
import org.protege.ontoviz.common.GraphController;

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

      OntoVizConstants.setUpButton(openButton, OntoVizIcons.ICON_OPEN, "","Open graph configuration from file");
      OntoVizConstants.setUpButton(saveButton, OntoVizIcons.ICON_SAVE, "","Save graph configuration to file");
      OntoVizConstants.setUpButton(scrShotButton, OntoVizIcons.ICON_SCR_SHOT, "","Export to Image File");
      OntoVizConstants.setUpButton(zoomOutButton, OntoVizIcons.ICON_ZOOM_OUT, "","Zoom Out");
      OntoVizConstants.setUpButton(zoomInButton, OntoVizIcons.ICON_ZOOM_IN, "","Zoom In");

      int style = 1;
      gridBtn = new JButton(new LayoutAction(" Grid", OntoVizIcons.GRID, new GridLayoutAlgorithm(style), graph));
      vertBtn = new JButton(new LayoutAction(" Vertical", OntoVizIcons.VERT, new TreeLayoutAlgorithm(style), graph));
      horiBtn = new JButton(new LayoutAction(" Horizontal", OntoVizIcons.HORI, new HorizontalTreeLayoutAlgorithm(style), graph));
      radBtn  = new JButton(new LayoutAction(" Radial", OntoVizIcons.RAD, new RadialLayoutAlgorithm(style), graph));
      recenter = new JButton(new FocusOnHomeAction(graph.getAnimationHandler()));
      
      GroupLayout topPanelLayout = new GroupLayout(this);
      setLayout(topPanelLayout);
      topPanelLayout.setAutoCreateGaps(true);
      topPanelLayout.setHorizontalGroup(
          topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(topPanelLayout.createSequentialGroup()
              .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(recenter)
              .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(gridBtn)
              .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(vertBtn)
              .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(radBtn)
              .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(horiBtn)

              .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(zoomOutButton)
              .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(zoomInButton)
              .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(scrShotButton)
              .addGap(18, 18, 18).addComponent(saveButton)
              .addGap(18, 18, 18).addComponent(openButton)
              )
          );
      topPanelLayout.setVerticalGroup(
        topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addComponent(recenter)
          .addComponent(gridBtn)
          .addComponent(vertBtn)
          .addComponent(radBtn)
          .addComponent(horiBtn)
          .addComponent(zoomOutButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
              .addGroup(topPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                  .addComponent(openButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                  .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                  .addComponent(scrShotButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
              )
          .addComponent(zoomInButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          );
   }

   public static TopPanel getTopPanel(AbstractOWLClassViewComponent comp, GraphController controller){
     return new TopPanel(comp, controller);
   }

   private JButton zoomOutButton, zoomInButton, scrShotButton, saveButton, openButton;
   private JButton gridBtn, vertBtn, radBtn, horiBtn, recenter;
}
