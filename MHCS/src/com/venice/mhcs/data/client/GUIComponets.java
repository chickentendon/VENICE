package com.venice.mhcs.data.client;

import java.util.ArrayList;

import javafx.scene.control.ScrollToEvent;

import com.google.gwt.dev.jjs.impl.CatchBlockNormalizer;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.event.shared.UmbrellaException;

public class GUIComponets {
	private ScrollPanel ModulePanel = new ScrollPanel(); 
	private ScrollPanel ModulePanelReset = new ScrollPanel();
	private FlexTable modtable = new FlexTable();
	
	public FlexTable getModTable(){
		return modtable;
	}
	
	public ScrollPanel getScrollPanel(){
		
		return ModulePanel;
	}
	public void resetPanel(){
		ModulePanel = ModulePanelReset;
	}
	
	/**
	 * Takes an ArrayList of Modules that updates the module ScrollPanel
	 * @param modList The up-to date ArrayList of modules 
	 */
	public void updatePanel(final ArrayList<Module> modList, final LocalStorage myStore){
		
		modtable.removeAllRows();
		ScrollPanel tempPanel = new ScrollPanel();
		String tempID = new String();
		String tempDamage = new String();
		String tempX = new String();
		String tempY = new String();
		String tempRotation = new String();
		String tempType = new String();
		
		modtable.setCellPadding(3);
		modtable.setStylePrimaryName("tableAlign");
		modtable.setText(0, 0, " ID Number ");
		modtable.setText(0, 1, " Type ");
		modtable.setText(0, 2, " (X,Y) ");

		modtable.setText(0, 4, " Rotations ");
		modtable.setText(0, 5, " Condition ");
		modtable.setText(0, 6, " Remove ");

		for (int i = 0; i < modList.size(); i++){
			final int size = modtable.getRowCount();
		    tempID = modList.get(i).getID();
			tempDamage = modList.get(i).getDamage();
			tempX = modList.get(i).getX();
			tempY = modList.get(i).getY();
			tempRotation = modList.get(i).getRotation();
			tempType = modList.get(i).getModuleType().toString();
			
			modtable.setText(size, 0, tempID);
			modtable.setText(size, 1, tempType);
			modtable.setText(size, 2, "(" + tempX + " , " + tempY + ")");

			modtable.setText(size, 4, tempRotation);
			modtable.setText(size, 5, tempDamage);
	
			Button removeButton = new Button("X");
			removeButton.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					myStore.Remove(modtable.getText(size, 0));
					modtable.removeRow(size);
	
					
				}
			});

			modtable.setWidget(size, 6, removeButton);

		}

		
		ModulePanel.setSize("500px", "200px");
		ModulePanel.add(modtable);

		
	}

}