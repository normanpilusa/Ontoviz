package org.protege.editor.owl;

	import java.io.File;
	import java.net.ProtocolException;
	import java.net.URI;
	import java.util.Collections;
	import java.util.Dictionary;
	import java.util.HashSet;
	import java.util.Properties;
	import java.util.Set;

	import org.apache.log4j.Logger;
	import org.osgi.framework.Bundle;
	import org.osgi.framework.Constants;
	import org.osgi.framework.ServiceReference;
	import org.osgi.framework.ServiceRegistration;
	import org.osgi.service.packageadmin.PackageAdmin;
	import org.protege.editor.core.BookMarkedURIManager;
	import org.protege.editor.core.ProtegeApplication;
	import org.protege.editor.core.editorkit.AbstractEditorKit;
	import org.protege.editor.core.editorkit.EditorKit;
	import org.protege.editor.core.editorkit.EditorKitDescriptor;
	import org.protege.editor.core.editorkit.RecentEditorKitManager;
	import org.protege.editor.core.ui.error.ErrorLogPanel;
	import org.protege.editor.core.ui.wizard.Wizard;
	//import org.protege.editor.owl.*;
	import org.protege.editor.owl.model.OWLModelManager;
	import org.protege.editor.owl.model.OWLModelManagerImpl;
	import org.protege.editor.owl.model.OWLWorkspace;
	import org.protege.editor.owl.model.SaveErrorHandler;
	import org.protege.editor.owl.model.io.IOListenerPlugin;
	import org.protege.editor.owl.model.io.IOListenerPluginInstance;
	import org.protege.editor.owl.model.io.IOListenerPluginLoader;
	import org.protege.editor.owl.ui.OntologyFormatPanel;
	import org.protege.editor.owl.ui.SaveConfirmationPanel;
	import org.protege.editor.owl.ui.UIHelper;
	import org.protege.editor.owl.ui.error.OntologyLoadErrorHandlerUI;
	import org.protege.editor.owl.ui.explanation.ExplanationManager;
	import org.protege.editor.owl.ui.ontology.imports.missing.MissingImportHandlerUI;
	import org.protege.editor.owl.ui.ontology.wizard.create.CreateOntologyWizard;
	import org.semanticweb.owlapi.model.IRI;
	import org.semanticweb.owlapi.model.OWLOntology;
	import org.semanticweb.owlapi.model.OWLOntologyFormat;
	import org.semanticweb.owlapi.model.OWLOntologyID;
	import org.semanticweb.owlapi.model.OWLOntologyManager;
	import org.semanticweb.owlapi.model.OWLOntologyStorageException;
	import org.semanticweb.owlapi.model.OWLOntologyStorerNotFoundException;
	import org.semanticweb.owlapi.util.VersionInfo;
	import org.semanticweb.owlapi.vocab.PrefixOWLOntologyFormat;


	/**
	 * adapted from  Author: Matthew Horridge<br>
	 * by musax
	 */
public class OWLEditorKit2 extends OWLEditorKit {

	    private static final Logger logger = Logger.getLogger(OWLEditorKit2.class);
	    public static final String ID = "OWLEditorKit2";
	    private OWLWorkspace workspace;
	    private OWLModelManager modelManager;
	    private Set<URI> newPhysicalURIs;
	    private OntologyLoadErrorHandlerUI loadErrorHandler;
	    private ServiceRegistration registration;

	  public OWLEditorKit2() {
	      super(new OWLEditorKitFactory());
	  }

		@Override
    protected void initialise(){
	        //logger.info("Using OWL API version " + VersionInfo.getVersionInfo().getVersion());
	        this.newPhysicalURIs = new HashSet<URI>();

					// TODO find API workaround for NullPointerException
	        modelManager = new OWLModelManagerImpl();

	        modelManager.setExplanationManager(new ExplanationManager(this));
	        modelManager.setMissingImportHandler(new MissingImportHandlerUI(this));
	        /*modelManager.setSaveErrorHandler(new SaveErrorHandler(){
	            public void handleErrorSavingOntology(OWLOntology ont, URI physicalURIForOntology, OWLOntologyStorageException e) throws Exception {
	                handleSaveError(ont, physicalURIForOntology, e);
	            }
	        });*/
	        loadErrorHandler = new OntologyLoadErrorHandlerUI(this);
	        modelManager.setLoadErrorHandler(loadErrorHandler);
	        loadIOListenerPlugins();
					Dictionary props = new Properties();
	        registration = ProtegeOWL.getBundleContext().registerService("org.protege.editor.owl.EditorKit", this, props);
	    }

			private void loadIOListenerPlugins() {
	        IOListenerPluginLoader loader = new IOListenerPluginLoader(this);
	        for(IOListenerPlugin pl : loader.getPlugins()) {
	            try {
	                IOListenerPluginInstance instance = pl.newInstance();
	                getModelManager().addIOListener(instance);
	            }
	            catch (Throwable e) {
									e.printStackTrace();
	            }
	        }
	    }

	}
