package net.xway.process.designer.node;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import net.xway.process.designer.IReloadHandle;

public abstract class AbstractNodeContextPane extends BorderPane implements IReloadHandle {

	private Label lblKey;
	private TextField txtKey;
	private Label lblName;
	private TextField txtName;
	private Label lblDescription;
	private TextField txtDescription;

	private FPane top = new FPane();
	private FPane center = new FPane();
	private FPane bottom = new FPane();
	
	public AbstractNodeContextPane(IProcessNode node) {
		lblKey = new Label();
		txtKey = new TextField();
        lblName = new Label();
        txtName = new TextField();
        lblDescription = new Label();
        txtDescription = new TextField();
		
        txtKey.setText(node.getKeyProperty().get());
        node.getKeyProperty().bindBidirectional(txtKey.textProperty());
		
        txtName.setText(node.getNameProperty().get());
        node.getNameProperty().bindBidirectional(txtName.textProperty());

        txtDescription.setText(node.getDescriptionProperty().get());
        node.getDescriptionProperty().bindBidirectional(txtDescription.textProperty());
        
        setTop(top);
 		setCenter(center);
		setBottom(bottom);
		
		addTop(lblKey, txtKey, lblName, txtName, lblDescription, txtDescription);
	}
	
	public void addTop(Node...children ) {
		top.getChildren().addAll(children);
	}
	
	public void addCenter(Node...children ) {
		center.getChildren().addAll(children);
	}
	
	public TextField getTxtName() {
		return txtName;
	}

	public TextField getTxtDescription() {
		return txtDescription;
	}
	
	@Override
	public void onReloadFinish(ResourceBundle bundle) {
		lblKey.setText(bundle.getString("Label.Key"));
    	lblName.setText(bundle.getString("Label.Name"));
    	lblDescription.setText(bundle.getString("Label.Description"));
	}

	class FPane extends FlowPane {
		
		private final static int hgap = 5,  vgap = 5;
		
		public FPane(Node... children) {
			super(hgap, vgap, children);
			setPadding(new Insets(10, 10, 10, 10));
		}
	}
}
