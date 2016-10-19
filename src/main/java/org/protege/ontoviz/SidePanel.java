package org.protege.ontoviz;

//import com.sun.corba.se.impl.orbutil.graph.Graph;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ca.uvic.cs.chisel.cajun.actions.LayoutAction;
import ca.uvic.cs.chisel.cajun.constants.LayoutConstants;
import ca.uvic.cs.chisel.cajun.graph.Graph;

import org.eclipse.zest.layouts.algorithms.DirectedGraphLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalDirectedGraphLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;

/**
 *
 * @author musa xakaza
 */
 public class SidePanel extends JPanel{

    public static SidePanel getSidePanel(Graph graph){
       return new SidePanel(graph);
     }

    private void default_view(){
       layoutCtrlPanel.setVisible(false);
       arcTypeCtrlPanel.setVisible(false);
       nodeTypeCtrlPanel.setVisible(false);
    }

    private SidePanel(Graph graph){

       jPanel3 = new JPanel();
       jPanel4 = new JPanel();
       jPanel5 = new JPanel();

       nodeTypeCtrlPanel = new JPanel();
       arcTypeCtrlPanel = new JPanel();
       layoutCtrlPanel = new JPanel();

       setBackground(OntoVizColors.PRIMARY_LIGHTER);
       setBorder(BorderFactory.createLineBorder(OntoVizColors.BLACK));
       setMaximumSize(new java.awt.Dimension(402, 546));
       setMinimumSize(new java.awt.Dimension(402, 546));

       //=================================================================

       layoutLabel = new JLabel();
       arcTypeLabel = new JLabel();
       nodeTypeLabel = new JLabel();

       OntoVizConstants.setUpLabel(layoutLabel, "Layout");
       OntoVizConstants.setUpLabel(arcTypeLabel, "Arc Types");
       OntoVizConstants.setUpLabel(nodeTypeLabel, "Node Types");

       layoutTBtn = new JToggleButton("+");
       nodeTypeTBtn = new JToggleButton("+");
       arcTypeTBtn = new JToggleButton("+");

       layoutTBtn.addItemListener(
          new java.awt.event.ItemListener() {
             public void itemStateChanged(java.awt.event.ItemEvent evt) {
                layoutTBtnItemStateChanged(evt);
             }
          });

       nodeTypeTBtn.addItemListener(
          new java.awt.event.ItemListener() {
             public void itemStateChanged(java.awt.event.ItemEvent evt) {
                nodeTypeTBtnItemStateChanged(evt);
             }
          });

       arcTypeTBtn.addItemListener(
          new java.awt.event.ItemListener() {
             public void itemStateChanged(java.awt.event.ItemEvent evt) {
                arcTypeTBtnItemStateChanged(evt);
             }
          });

       //=================================================================
       int style = 1;
       gridBtn = new JButton(new LayoutAction(" Grid", OntoVizIcons.GRID, new GridLayoutAlgorithm(style), graph));
       vertBtn = new JButton(new LayoutAction(" Vertical", OntoVizIcons.VERT, new DirectedGraphLayoutAlgorithm(style), graph));
       horiBtn = new JButton(new LayoutAction(" Horizontal", OntoVizIcons.HORI, new HorizontalDirectedGraphLayoutAlgorithm(style), graph));
       radBtn  = new JButton(new LayoutAction(" Radial", OntoVizIcons.RAD, new RadialLayoutAlgorithm(style), graph));

       checkbox1 = new Checkbox();
       checkbox2 = new Checkbox();
       checkbox3 = new Checkbox();
       checkbox4 = new Checkbox();
       checkbox5 = new Checkbox();

       checkbox1.setLabel("arc type item 1");
       checkbox2.setLabel("arc type item 2");
       checkbox3.setLabel("arc type item 3");
       checkbox4.setLabel("node type item 1");
       checkbox5.setLabel("node type item 2");

       //=================================================================
       layoutCtrlPanel.setBackground(OntoVizColors.PRIMARY_LIGHTER);
       arcTypeCtrlPanel.setBackground(OntoVizColors.PRIMARY_LIGHTER);
       nodeTypeCtrlPanel.setBackground(OntoVizColors.PRIMARY_LIGHTER);

       arcTypeCtrlPanel.setPreferredSize(new java.awt.Dimension(136, 106));

       jPanel3.setBackground(OntoVizColors.PRIMARY_DARKER);
       jPanel4.setBackground(OntoVizColors.PRIMARY_DARKER);
       jPanel5.setBackground(OntoVizColors.PRIMARY_DARKER);

       OntoVizConstants.setHeaderPanel(jPanel4, nodeTypeTBtn, nodeTypeLabel, 77);
       OntoVizConstants.setHeaderPanel(jPanel5, arcTypeTBtn, arcTypeLabel, 80);
       OntoVizConstants.setHeaderPanel(jPanel3, layoutTBtn, layoutLabel, 94, 129);


       GroupLayout layoutCtrlPanelLayout = new GroupLayout(layoutCtrlPanel);
       layoutCtrlPanel.setLayout(layoutCtrlPanelLayout);
       layoutCtrlPanelLayout.setHorizontalGroup(
          layoutCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(layoutCtrlPanelLayout.createSequentialGroup()
              .addGap(24, 24, 24)
              .addGroup(layoutCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                  .addComponent(vertBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(gridBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
              .addGap(54, 54, 54)
              .addGroup(layoutCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                  .addComponent(radBtn, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                  .addComponent(horiBtn))
              .addContainerGap(51, Short.MAX_VALUE))
          );
       layoutCtrlPanelLayout.setVerticalGroup(
          layoutCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(layoutCtrlPanelLayout.createSequentialGroup()
              .addContainerGap()
              .addGroup(layoutCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                  .addComponent(gridBtn)
                  .addComponent(radBtn))
              .addGap(15, 15, 15)
              .addGroup(layoutCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                  .addComponent(vertBtn)
                  .addComponent(horiBtn))
              .addContainerGap())
          );

       GroupLayout nodeTypeCtrlPanelLayout = new GroupLayout(nodeTypeCtrlPanel);
       nodeTypeCtrlPanel.setLayout(nodeTypeCtrlPanelLayout);
       nodeTypeCtrlPanelLayout.setHorizontalGroup(
          nodeTypeCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(GroupLayout.Alignment.TRAILING, nodeTypeCtrlPanelLayout.createSequentialGroup()
              .addContainerGap()
              .addGroup(nodeTypeCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                  .addComponent(checkbox5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(checkbox4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
              .addContainerGap())
          );
       nodeTypeCtrlPanelLayout.setVerticalGroup(
          nodeTypeCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(nodeTypeCtrlPanelLayout.createSequentialGroup()
              .addContainerGap()
              .addComponent(checkbox4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addGap(2, 2, 2)
              .addComponent(checkbox5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addContainerGap(54, Short.MAX_VALUE))
          );

       GroupLayout arcTypeCtrlPanelLayout = new GroupLayout(arcTypeCtrlPanel);
       arcTypeCtrlPanel.setLayout(arcTypeCtrlPanelLayout);
       arcTypeCtrlPanelLayout.setHorizontalGroup(
          arcTypeCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(GroupLayout.Alignment.TRAILING, arcTypeCtrlPanelLayout.createSequentialGroup()
              .addContainerGap()
              .addGroup(arcTypeCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                  .addComponent(checkbox3, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                  .addComponent(checkbox2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(checkbox1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
              .addContainerGap())
          );
       arcTypeCtrlPanelLayout.setVerticalGroup(
          arcTypeCtrlPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(arcTypeCtrlPanelLayout.createSequentialGroup()
              .addGap(18, 18, 18)
              .addComponent(checkbox1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
              .addGap(2, 2, 2)
              .addComponent(checkbox2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
              .addGap(2, 2, 2)
              .addComponent(checkbox3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
              .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          );

       GroupLayout infoPanelLayout = new GroupLayout(this);
       setLayout(infoPanelLayout);
       infoPanelLayout.setHorizontalGroup(
          infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(nodeTypeCtrlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(arcTypeCtrlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
          .addComponent(layoutCtrlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
          );
       infoPanelLayout.setVerticalGroup(
          infoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
          .addGroup(infoPanelLayout.createSequentialGroup()
              .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(layoutCtrlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(jPanel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(nodeTypeCtrlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(arcTypeCtrlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
              .addContainerGap(121, Short.MAX_VALUE))
          );

       default_view();
    }

    private void layoutTBtnItemStateChanged(ItemEvent evt) {
       OntoVizConstants.toggleEvt(evt, layoutTBtn, layoutCtrlPanel);
    }

    private void nodeTypeTBtnItemStateChanged(ItemEvent evt) {
       OntoVizConstants.toggleEvt(evt, nodeTypeTBtn, nodeTypeCtrlPanel);
    }

    private void arcTypeTBtnItemStateChanged(ItemEvent evt) {
       OntoVizConstants.toggleEvt(evt, arcTypeTBtn, arcTypeCtrlPanel);
    }

    private Checkbox checkbox1, checkbox2, checkbox3, checkbox4, checkbox5;

    private JButton gridBtn, vertBtn, radBtn, horiBtn;

    private JLabel layoutLabel, nodeTypeLabel, arcTypeLabel;

    private JToggleButton layoutTBtn, nodeTypeTBtn, arcTypeTBtn;

    private JPanel layoutCtrlPanel, nodeTypeCtrlPanel, arcTypeCtrlPanel;

    private JPanel jPanel3, jPanel4, jPanel5;


 }
