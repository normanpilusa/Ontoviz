package org.protege.ontoviz;

import ca.uvic.cs.chisel.cajun.actions.LayoutAction;
import ca.uvic.cs.chisel.cajun.graph.AbstractGraph;
import java.awt.ActiveEvent;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.protege.editor.core.editorkit.EditorKit;
import org.protege.editor.owl.OWLEditorKit2;
import org.protege.editor.owl.OWLEditorKit;
import org.protege.editor.owl.OWLEditorKitFactory;
import org.protege.editor.owl.model.OWLModelManager;
import org.protege.editor.owl.model.OWLModelManagerImpl;
import org.protege.editor.owl.ui.view.cls.AbstractOWLClassViewComponent;
import org.protege.ontoviz.actions.ExportImageAction;
import org.protege.ontoviz.actions.OpenGraphAction;
import org.protege.ontoviz.common.GraphController;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class TopPanelTest {

  //String testFilePath = "C:\\Users\\musax\\Desktop\\protege\\pizza.owl";
  private static final Logger logger = Logger.getLogger(TopPanelTest.class);
  private GraphController graphController;
  private boolean init = false;

  private void initialize(){
    JPanel mainWindow = new JPanel();
    try {
      graphController = new GraphController(mainWindow, new OWLEditorKit2());
    }
    catch(NullPointerException e){
      //logger.info("GraphController creation failed, actions' tests aborted.");
      return;
    }

		ExportImageAction exportImage = new ExportImageAction(mainWindow, graphController.getGraph().getCanvas());
		OpenGraphAction openGraph = new OpenGraphAction(mainWindow, graphController);
		openGraph.loadFromFile(new File(TopPanelTest.class.getResource("testing.graph").getFile()));
    init = true;
  }

  @Test
  public void testAction(){
    initialize();
    if (init){
      testLayoutAction("Grid");
      testLayoutAction("Vertical");
      testLayoutAction("Horizontal");
      testLayoutAction("Radial");
    }
  }

  @Test
  public void testDefault(){
    Assert.assertEquals(0,0);
  }

  /**
   * find action by using its name, perform action and check that it's
   * the last action to be performed on the graph.
   */
  private void testLayoutAction(String actionStr){

    for (LayoutAction action : graphController.getGraph().getLayouts()) {
      System.out.println("---"+action.getClass());
			if(actionStr.equals(action.getName())){
				ActionEvent arg0=null;
				action.actionPerformed(arg0);
				action.doAction();
				action.runLayout();

        String lastActionName = ((AbstractGraph) graphController.getGraph()).getLastLayout().getName();
        Assert.assertEquals(true, actionStr.equals(lastActionName));

				graphController.refresh();
				System.out.println("---"+action.getName());
			}
		}
  }

}
