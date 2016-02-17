package br.com.gabrielvalerio.DoNotes.resources;

import java.awt.Image;
import java.awt.Toolkit;

import javafx.scene.text.Font;

public final class Resources {
	
	private static final String linkStart 		= "/br/com/gabrielvalerio/DoNotes/resources/";
	//icon tray
	public static final Image 						ICONE		= Toolkit.getDefaultToolkit().getImage(Resources.class.getResource("icontray.png"));
	
	//imagens grandes
	public static final javafx.scene.image.Image 	BACKGROUND 		= new javafx.scene.image.Image(Resources.class.getResource("big/background.png").toExternalForm());
	public static final javafx.scene.image.Image 	MOVEIMAGE		= new javafx.scene.image.Image(Resources.class.getResource("big/move.png").toExternalForm());
	public static final javafx.scene.image.Image 	CLOSEIMAGE		= new javafx.scene.image.Image(Resources.class.getResource("big/close.png").toExternalForm());
	public static final javafx.scene.image.Image 	NOTELISTBG		= new javafx.scene.image.Image(Resources.class.getResource("big/listviewbg.png").toExternalForm());
	public static final javafx.scene.image.Image 	SIDEBARCLOSED	= new javafx.scene.image.Image(Resources.class.getResource("big/sidebar.png").toExternalForm());
	public static final javafx.scene.image.Image 	SIDEBAROPEN		= new javafx.scene.image.Image(Resources.class.getResource("big/sidebarback.png").toExternalForm());
	
	//imagens pequenas
	public static final javafx.scene.image.Image 	SMALLBACKGROUND 	= new javafx.scene.image.Image(Resources.class.getResource("small/smallbackground.png").toExternalForm());
	public static final javafx.scene.image.Image 	SMALLMOVEIMAGE		= new javafx.scene.image.Image(Resources.class.getResource("small/smallmove.png").toExternalForm());
	public static final javafx.scene.image.Image 	SMALLCLOSEIMAGE		= new javafx.scene.image.Image(Resources.class.getResource("small/smallclose.png").toExternalForm());
	public static final javafx.scene.image.Image 	SMALLNOTELISTBG		= new javafx.scene.image.Image(Resources.class.getResource("small/smalllistviewbg.png").toExternalForm());
	public static final javafx.scene.image.Image 	SMALLSIDEBARCLOSED	= new javafx.scene.image.Image(Resources.class.getResource("small/smallsidebar.png").toExternalForm());
	public static final javafx.scene.image.Image 	SMALLSIDEBAROPEN	= new javafx.scene.image.Image(Resources.class.getResource("small/smallsidebarback.png").toExternalForm());
	
	//links
	public static final String		CSSTXTAR_LINK		= linkStart + "transparenttextarea.css";
	public static final String		CSSTXTVW_LINK		= linkStart + "transparentlistview.css";
	public static final String		LISTBG_LINK			= linkStart + "big/listviewbg.png";
	public static final String 		BG_LINK				= linkStart + "big/background.png";
	public static final String		SMALLLISTBG_LINK	= linkStart + "small/smalllistviewbg.png";
	public static final String 		SMALLBG_LINK		= linkStart + "small/smallbackground.png";
	
	public static final int			BG_WIDTH		= 547;
	public static final int			BG_HEIGHT		= 547;
	public static final int			SMALLBG_WIDTH	= 274;
	public static final int			SMALLBG_HEIGHT	= 274;
	public static final int			SIDEBARWIDTH 	= 15;
	public static final int			NOTELISTWIDTH	= 100;
	public static final int			FONTSIZE		= 12;
	
	public static final Font		FONT			= new Font("Verdana", FONTSIZE);
}
