package org.protege.ontoviz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import org.protege.editor.owl.ui.view.cls.AbstractOWLClassViewComponent;
import org.protege.ontoviz.common.GraphController;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLEntity;

import ca.uvic.cs.chisel.cajun.graph.AbstractGraph;
import ca.uvic.cs.chisel.cajun.graph.FlatGraph;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNode;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNodeCollectionEvent;
import ca.uvic.cs.chisel.cajun.graph.node.GraphNodeCollectionListener;

/**
 * Plugin extension point for the ontoviz view.
 *
 * @author seanf
 */
public class OntovizView extends AbstractOWLClassViewComponent  {
	private static final long serialVersionUID = -6969495880634875570L;

	// reference to the graph controller object, gives access to graph model and graph functions
	private GraphController graphController;

	// flag for cancelling updateView call, this is for managing synchronization between
	// the graph and the class tree
	private boolean cancelSelectionUpdate;



	/**
	 * When a node gets selected in the graph, we want to update the global class selection for Protege.
	 */
	private void syncNodeSelection() {
		GraphNode node = ((AbstractGraph)graphController.getGraph()).getFirstSelectedNode();
		if(node != null) {
			cancelSelectionUpdate = true;
			setGlobalSelection((OWLEntity)node.getUserObject());
		}
	}

	@Override
	public void initialiseClassView() throws Exception {
		setLayout(new BorderLayout());

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				super.componentResized(e);

			}
		});

		graphController = new GraphController(this, this.getOWLEditorKit());

		graphController.getGraph().addNodeSelectionListener(new GraphNodeCollectionListener() {
			public void collectionChanged(GraphNodeCollectionEvent arg0) {
				syncNodeSelection();
			}
		});

		add(TopPanel.getTopPanel(this,graphController), BorderLayout.NORTH);
		//add(SidePanel.getSidePanel(graphController.getGraph()), BorderLayout.EAST);

		Dimension d = new Dimension(800, 600);
		setPreferredSize(d);
		setSize(d);
		setLocation(100, 50);

		setVisible(true);
	}

	@Override
	protected OWLClass updateView(OWLClass owlClass) {
		if(owlClass != null && !cancelSelectionUpdate) {
			graphController.showOWLClass(owlClass);
		}

		cancelSelectionUpdate = false;

		return null;
	}

	@Override
	public void disposeView() {
		// TODO Auto-generated method stub
	}

}
