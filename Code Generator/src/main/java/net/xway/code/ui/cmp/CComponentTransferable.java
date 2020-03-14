package net.xway.code.ui.cmp;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class CComponentTransferable implements Transferable {

	public final static DataFlavor IntFlavor = new DataFlavor(Integer.TYPE, "int");
	
	private int index;
	private Point location;
	private DataFlavor[] dataFlavor = new DataFlavor[] { IntFlavor };
	
	public CComponentTransferable(int index, Point location) {
		this.index = index;
		this.location = location;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return dataFlavor;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		for (DataFlavor flv : dataFlavor) {
			if (flv.equals(flavor)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return new Object[] { index, location };
	}
	
	
}
