package com.venice.mhcs.data.GUI;

import java.util.ArrayList;

import javax.validation.constraints.Min;

import com.google.gwt.resources.css.Minify;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.venice.mhcs.data.client.Configuration;
import com.venice.mhcs.data.client.LocalStorage;
import com.venice.mhcs.data.client.Module;
import com.venice.mhcs.data.client.ModuleGrid;
import com.venice.mhcs.data.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class configTab {
	private static Configuration mini = new Configuration();
	private static LocalStorage stor = new LocalStorage();
	private static ModuleGrid grid = new ModuleGrid();
	private static ScrollPanel sp = new ScrollPanel();
	private static ModuleMap map = new ModuleMap();
	private static ArrayList<Module> modList = new ArrayList<Module>();
	public static void initConfig(){
		
		final ListBox arrayBox = new ListBox();
		arrayBox.addItem("Local Storage");
		arrayBox.addItem("Test1");
		arrayBox.addItem("Test2");
		arrayBox.addItem("Test3");
		arrayBox.addItem("Test4");
		arrayBox.addItem("Test5");
		arrayBox.addItem("Test6");
		arrayBox.addItem("Test7");
		arrayBox.addItem("Test8");
		arrayBox.addItem("Test9");
		arrayBox.addItem("Test10");
		
		arrayBox.setStylePrimaryName("listBoxMargin");
		
		final ListBox configBox = new ListBox();
		
		configBox.addItem("Minimum Config1");
		configBox.addItem("Minimum Config2");
		configBox.setStylePrimaryName("listBoxMargin");
		
		final Button sumbitButton = new Button("Sumbit");
		sumbitButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				
			}
		});
		
		mini.setMinimum1(stor.getModuleList());
		
		if(mini.isMinimum(mini.getMinimum1())){
			mini = mini.buildMin1(mini);
			grid.addArray(mini.getMinimum1());
			map.initalizeMap(grid);
			sp.add(map.getGrid());
		}
	} 
	
	public static ScrollPanel getConfig(){
		return sp;
	}
}
