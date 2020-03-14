/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.xway.process.designer.tools;

import java.util.ResourceBundle;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import net.xway.process.designer.IReloadHandle;
import net.xway.process.designer.NodeType;

/**
 *
 * @author cc
 */
public abstract class AbstractTool extends VBox implements IReloadHandle {

	private static final int WIDTH = 48;
	private static final int HEIGHT = 48;

    protected IconBox iconBox;
    protected Label lblTitle;

	public AbstractTool() {
        iconBox = new IconBox();
        lblTitle = new Label();
        getChildren().addAll(iconBox, lblTitle);
        setAlignment(Pos.CENTER);
        
        setOnDragDetected( m -> {
            Dragboard dragboard = startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(iconBox.getAsImage());
            dragboard.setContent(content);
        });

        prefWidth(WIDTH);
        prefHeight(HEIGHT + lblTitle.getHeight());        
        setWidth(WIDTH);
        setHeight(HEIGHT + lblTitle.getHeight());
        
    }

    public abstract NodeType getType();
	public abstract String getTitle();
	public abstract Canvas getIcon();
	
	
    @Override
    public void onReloadFinish(ResourceBundle bundle) {
    	if (getTitle() != null)
    		lblTitle.setText(bundle.getString(getTitle()));
    	if (getIcon() != null)
    		iconBox.setIcon(getIcon());
    }
    
    public int getIconWidth() {
    	return WIDTH;
    }
    
    public int getIconHeight() {
    	return HEIGHT;
    }
    
    @Override
    public String toString() {
        return getType() + "Tool";
    }
	
	public class IconBox extends Region {
		
		public IconBox() {
			setMaxSize(getIconWidth(), getIconHeight());
			setMinSize(getIconWidth(), getIconHeight());
			setPrefSize(getIconWidth(), getIconHeight());
			
			getChildren().add(new Canvas(getIconWidth(), getIconHeight()));
		}
		
		public void setIcon(Canvas c) {
			getChildren().remove(0);
			getChildren().add(c);
		}
		
		public Canvas getIcon() {
			return (Canvas) getChildren().get(0);
		}
		
		public Image getAsImage() {
			WritableImage image = new WritableImage(getIconWidth(), getIconHeight());
			getIcon().snapshot(null, image);
			return image;
		}
	}
}
