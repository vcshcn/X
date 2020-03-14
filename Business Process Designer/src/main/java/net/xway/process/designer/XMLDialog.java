package net.xway.process.designer;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class XMLDialog extends Dialog<Void> {


	public XMLDialog(String xml, ResourceBundle bundle) {
		setTitle(bundle.getString("ProcessDefinition"));
		setResizable(true);

		TextArea area = new TextArea(xml);
		area.setEditable(false);
		getDialogPane().setContent(area);
		
		ButtonType _btnCopy = new ButtonType(bundle.getString("Copy"), ButtonData.YES);
		ButtonType _btnClose = new ButtonType(bundle.getString("Close"), ButtonData.CANCEL_CLOSE);
		getDialogPane().getButtonTypes().addAll(_btnCopy, _btnClose);
		
		Button btnCopy = (Button)getDialogPane().lookupButton(_btnCopy);
		btnCopy.setDefaultButton(false);
		btnCopy.addEventFilter(ActionEvent.ACTION, l -> {
			ClipboardContent content = new ClipboardContent();
			content.putString(xml);
			Clipboard.getSystemClipboard().setContent(content);
			l.consume();
		});
		Button btnClose = (Button)getDialogPane().lookupButton(_btnClose);
		btnClose.setDefaultButton(true);
		btnClose.setCancelButton(true);
	}
	
	
}
