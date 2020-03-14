package net.xway.process.designer.node;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import net.xway.process.designer.IElementSerializable;
import net.xway.process.designer.IReloadHandle;
import net.xway.process.designer.IXMLString;
import net.xway.process.designer.NodeCategory;
import net.xway.process.designer.NodeType;

public interface IProcessNode extends IReloadHandle, IXMLString, IElementSerializable, ChangeListener<String> {

	public Integer getDatabaseId();
	
	public StringProperty getKeyProperty();
	
	public StringProperty getNameProperty();
	
	public StringProperty getDescriptionProperty();

	public NodeCategory getCategory();
	
	public NodeType getType();
	
	public ProcessDefinitionPane getDefinitionPane();
	
	public String getDefaultKeyValue();

	public String getDefaultNameValue() ;

	public AbstractNodeContextMenu getNodeContextMenu();
	
	public AbstractNodeContextPane getNodeContextPane();

	public boolean hasFocus();
	public void onFocus();
	public void onLostFocus();
}
