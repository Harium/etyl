package com.harium.etyl.awt.core;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.harium.etyl.commons.context.Context;
import com.harium.etyl.core.Core;

public class AWTDragAndDrop implements DropTargetListener {

	Core core;
	
	public AWTDragAndDrop(Core core) {
		this.core = core;
	}
	
	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		Context context = core.getCurrentContext();
		context.dragEnter();
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		Context context = core.getCurrentContext();
		context.dragExit();
	}

	@Override
	public void drop(DropTargetDropEvent evt) {
		int action = evt.getDropAction();
		evt.acceptDrop(action);
		try {
			Transferable data = evt.getTransferable();
			//DataFlavor flavors[] = data.getTransferDataFlavors();
			if (data.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
				List<File> list = (List<File>) data.getTransferData(DataFlavor.javaFileListFlavor);
				
				Context context = core.getCurrentContext();
				int mx = (int) evt.getLocation().getX();
				int my = (int) evt.getLocation().getY();
				context.dropFiles(mx, my, list);
			}
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			evt.dropComplete(true);
		}
	}
	
}
