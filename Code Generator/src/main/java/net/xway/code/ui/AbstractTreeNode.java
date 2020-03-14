package net.xway.code.ui;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public abstract class AbstractTreeNode implements TreeNode {

	private AbstractTreeNode parentNode;
	private List<AbstractTreeNode> nodes = new ArrayList<>();
	private Object data = null;
	

	public void addNode(AbstractTreeNode node) {
		if (node instanceof EnumComponentNode) {
			nodes.add(node);
		}
		else if (node instanceof ProjectNode) {
			nodes.add(node);
		}
		else {
			boolean b = false;
			for (int i=nodes.size()-1; i>=0; i--) {
				if (nodes.get(i) instanceof ComponentNode == false) {
					nodes.add(i, node);
					b = true;
					break ;
				}
			}
			if (b == false) {
				nodes.add(node);
			}
		}
		updateTreeUI();
	}
	
	public void deleteNode(AbstractTreeNode node) {
		nodes.remove(node);
		updateTreeUI();
	}
	
	public AbstractTreeNode() {
		parentNode = null;
	}
	
	public AbstractTreeNode(AbstractTreeNode parentNode) {
		this.parentNode = parentNode;
	}
	
	public AbstractTreeNode(AbstractTreeNode parentNode, List<AbstractTreeNode> nodes) {
		this.parentNode = parentNode;
		this.nodes = nodes;
	}

	@Override
	public AbstractTreeNode getChildAt(int childIndex) {
		return nodes != null ? nodes.get(childIndex) : null;
	}

	@Override
	public int getChildCount() {
		return nodes != null ? nodes.size() : 0 ;
	}

	@Override
	public AbstractTreeNode getParent() {
		return parentNode;
	}

	@Override
	public int getIndex(TreeNode node) {
		return nodes.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		return true;
	}

	@Override
	public boolean isLeaf() {
		return nodes == null || nodes.size() == 0;
	}

	@Override
	public Enumeration<AbstractTreeNode> children() {
		return new Enumeration<AbstractTreeNode>() {

			private int index = 0;
			
			@Override
			public boolean hasMoreElements() {
				return (nodes.size()==0) ? false : index < nodes.size();
			}

			@Override
			public AbstractTreeNode nextElement() {
				return nodes.get(index++);
			}
			
		};
	}

	public abstract JPopupMenu getContentMenu() ;
	
	public abstract JPanel getContentPanel() ;
	
	public abstract JTree getTree();
	
	public void updateTreeUI() {
		getTree().expandPath(getTree().getSelectionPath());
		getTree().updateUI();
	}
	
	public TreePath getTreePath() {
		ArrayList<TreeNode> stack = new ArrayList<>();
		stack.add(this);
		
		TreeNode node = getParent();
		while (node != null) {
			stack.add(0, node);
			node = node.getParent();
		}
		
		return new TreePath(stack.toArray());
	}
	
	protected void nodeChanged() {
		DefaultTreeModel model = (DefaultTreeModel) getTree().getModel();
		model.nodeChanged(this);
	}
}
