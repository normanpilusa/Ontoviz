package org.protege.ontoviz;
import java.net.URL;
import javax.swing.ImageIcon;

import org.protege.ontoviz.common.GraphController;

public abstract class OntoVizIcons {

   public static final ImageIcon ICON_OPEN = loadImageIcon(GraphController.class, "/newicons/download-open-folder (2).png");
   public static final ImageIcon ICON_SAVE = loadImageIcon(GraphController.class, "/newicons/save.png");
   public static final ImageIcon ICON_SCR_SHOT = loadImageIcon(GraphController.class, "/newicons/photo-camera (2).png");
   public static final ImageIcon ICON_ZOOM_OUT = loadImageIcon(GraphController.class, "/newicons/zoom-out.png");
   public static final ImageIcon ICON_ZOOM_IN = loadImageIcon(GraphController.class, "/newicons/zoom-in.png");

   public static final ImageIcon GRID = loadImageIcon(GraphController.class, "/newicons/grid.png");
   public static final ImageIcon VERT = loadImageIcon(GraphController.class, "/newicons/vertical.png");
   public static final ImageIcon RAD = loadImageIcon(GraphController.class, "/newicons/radial.png");
   public static final ImageIcon HORI = loadImageIcon(GraphController.class, "/newicons/horizontal.png");

	@SuppressWarnings("unchecked")
	public static ImageIcon loadImageIcon(Class clas, String iconPath) {
        ImageIcon icon = null;
        URL url = clas.getResource(iconPath);
        if (url != null) {
            icon = new ImageIcon(url);
        }
        else {
        	icon = new ImageIcon(iconPath);
        }
        return icon;
    }
}
