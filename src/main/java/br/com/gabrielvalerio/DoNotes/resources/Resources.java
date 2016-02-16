package br.com.gabrielvalerio.DoNotes.resources;

import java.awt.Image;
import java.awt.Toolkit;

public final class Resources {
	
	private static final String linkStart 		= "/br/com/gabrielvalerio/DoNotes/resources/";
	
	public static final Image 						ICONE		= Toolkit.getDefaultToolkit().getImage(Resources.class.getResource("icontray.png"));
	public static final javafx.scene.image.Image 	BACKGROUND 	= new javafx.scene.image.Image(Resources.class.getResource("background.png").toExternalForm());
	public static final javafx.scene.image.Image 	MOVEIMAGE	= new javafx.scene.image.Image(Resources.class.getResource("move.png").toExternalForm());
	public static final javafx.scene.image.Image 	CLOSEIMAGE	= new javafx.scene.image.Image(Resources.class.getResource("close.png").toExternalForm());
	
	public static final String		TRANSPTXTAREA	= linkStart + "transparenttextarea.css";
	public static final String 		BG_LINK			= linkStart + "background.png";
	public static final int			BG_WIDTH		= 547;
	public static final int			BG_HEIGHT		= 547;
}
