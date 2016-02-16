package br.com.gabrielvalerio.DoNotes.resources;

import java.awt.Image;
import java.awt.Toolkit;

import javafx.scene.text.Font;

public final class Resources {
	
	private static final String linkStart 		= "/br/com/gabrielvalerio/DoNotes/resources/";
	
	public static final Image 						ICONE		= Toolkit.getDefaultToolkit().getImage(Resources.class.getResource("icontray.png"));
	public static final javafx.scene.image.Image 	BACKGROUND 	= new javafx.scene.image.Image(Resources.class.getResource("background.png").toExternalForm());
	public static final javafx.scene.image.Image 	MOVEIMAGE	= new javafx.scene.image.Image(Resources.class.getResource("move.png").toExternalForm());
	public static final javafx.scene.image.Image 	CLOSEIMAGE	= new javafx.scene.image.Image(Resources.class.getResource("close.png").toExternalForm());
	public static final javafx.scene.image.Image 	NOTELISTBG	= new javafx.scene.image.Image(Resources.class.getResource("listviewbg.png").toExternalForm());
	
	public static final javafx.scene.image.Image 	SIDEBARCLOSED	= new javafx.scene.image.Image(Resources.class.getResource("sidebar.png").toExternalForm());
	public static final javafx.scene.image.Image 	SIDEBAROPEN		= new javafx.scene.image.Image(Resources.class.getResource("sidebarback.png").toExternalForm());
	public static final int							SIDEBARWIDTH = 15;
	
	public static final String		TRANSPTXTAREA	= linkStart + "transparenttextarea.css";
	public static final String		TRANSPLSTVIEW	= linkStart + "transparentlistview.css";
	
	public static final String		LISTBG_LINK		= linkStart + "listviewbg.png";
	public static final String 		BG_LINK			= linkStart + "background.png";
	public static final int			BG_WIDTH		= 547;
	public static final int			BG_HEIGHT		= 547;
	
	public static final int			FONTSIZE		= 12;
	public static final Font		FONT			= new Font("Verdana", FONTSIZE);
}
