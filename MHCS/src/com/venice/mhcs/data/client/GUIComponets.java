package com.venice.mhcs.data.client;

import java.util.ArrayList;

import javafx.scene.control.ScrollToEvent;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.ScrollPanel;

public class GUIComponets {
	private ScrollPanel ModulePanel = new ScrollPanel(); 
	private FlexTable modtable = new FlexTable();
	
	public ScrollPanel getModulePanel(){
		
		return ModulePanel;
	}
	
	public ScrollPanel getScrollPanel(){
		return ModulePanel;
	}
	
	public void updatePanel(ArrayList<Module> modList){
		ScrollPanel tempPanel = new ScrollPanel();
		String tempID = new String();
		String tempDamage = new String();
		String tempX = new String();
		String tempY = new String();
		String tempRotation = new String();
		String tempType = new String();
		
		
		modtable.setText(0, 0, "ID Number");
		modtable.setText(0, 1, "Type");
		modtable.setText(0, 2, "Condition");
		modtable.setText(0, 3, "X Coordinate");
		modtable.setText(0, 4, "Y Coordinate");
		modtable.setText(0, 5, "Rotations");
		
		for (int i = 0; i < modList.size(); i++){
			int size = modtable.getRowCount();
			tempID = modList.get(i).getID();
			tempDamage = modList.get(i).getDamage();
			tempX = modList.get(i).getX();
			tempY = modList.get(i).getY();
			tempRotation = modList.get(i).getRotation();
			tempType = modList.get(i).getModuleType().toString();
			
			modtable.setText(size, 0, tempID);
			modtable.setText(size, 1, tempType);
			modtable.setText(size, 2, tempDamage);
			modtable.setText(size, 3, tempX);
			modtable.setText(size, 4, tempY);
			modtable.setText(size, 5, tempRotation);
			
		}
		
		ModulePanel.add(modtable);
	}
	
}
