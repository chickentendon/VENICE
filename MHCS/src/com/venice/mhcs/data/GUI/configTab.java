package com.venice.mhcs.data.GUI;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.venice.mhcs.data.client.Configuration;
import com.venice.mhcs.data.client.LocalStorage;
import com.venice.mhcs.data.client.Module;
import com.venice.mhcs.data.client.ModuleGrid;
import com.venice.mhcs.data.client.TestCases;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class configTab {
	private static Configuration mini = new Configuration();
	private static Configuration full = new Configuration();
	private static LocalStorage stor = new LocalStorage();
	private static ModuleGrid grid = new ModuleGrid();
	private static FlowPanel fp = new FlowPanel();
	private static ScrollPanel sp = new ScrollPanel();
	private static VerticalPanel vp = new VerticalPanel();
	private static ModuleMap map = new ModuleMap();
	private static ArrayList<Module> modList = new ArrayList<Module>();
	private static ArrayList<Module> saveList = new ArrayList<Module>();
	//private static TestCases tCase = new TestCases();
	public static void initConfig(){
		final TestCases tCase = new TestCases();
		//Listbox of sources of arrayLists
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
		//sets arrayBox css
		arrayBox.setStylePrimaryName("listBoxMargin");
		// listbox of what type of configs
		final ListBox configBox = new ListBox();
		
		// adds settings to config tabs
		configBox.addItem("Minimum Config1");
		configBox.addItem("Minimum Config2");
		configBox.addItem("Full Config1");
		configBox.addItem("Full Config2");
		configBox.setStylePrimaryName("listBoxMargin");
		
		//declares submit button
		final Button sumbitButton = new Button("Show Config");
		//handler checks for configbox and testbox for values
		sumbitButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// clears map
				sp.clear();
				grid = new ModuleGrid();
				// checks what value is in the config dropdown and adds arraylist modlist
				if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Local Storage")){
					modList = stor.getModuleList();
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test1")){
					modList = tCase.getTestCase("1");
					modList = tCase.getTestCase("1");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test2")){
					modList = tCase.getTestCase("2");
					modList = tCase.getTestCase("2");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test3")){
					modList = tCase.getTestCase("3");
					modList = tCase.getTestCase("3");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test4")){
					modList = tCase.getTestCase("4");
					modList = tCase.getTestCase("4");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test5")){
					modList = tCase.getTestCase("5");
					modList = tCase.getTestCase("5");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test6")){
					modList = tCase.getTestCase("6");
					modList = tCase.getTestCase("6");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test7")){
					modList = tCase.getTestCase("7");
					modList = tCase.getTestCase("7");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test8")){
					modList = tCase.getTestCase("8");
					modList = tCase.getTestCase("8");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test9")){
					modList = tCase.getTestCase("9");
					modList = tCase.getTestCase("9");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test10")){
					modList = tCase.getTestCase("10");
					modList = tCase.getTestCase("10");
				}
				else{
					Window.alert("Test Case Not ready");
				}
				
				// if configbox is minconfig1 it set map to minconfig1
				if(configBox.getItemText(configBox.getSelectedIndex()).equals("Minimum Config1")){
					for(int i = 0; i < modList.size(); i++){
						modList.get(i).makeClean();
					}
					
					mini.setMinimum1(modList);
					
					if(mini.isMinimum(mini.getMinimum1())){
						mini = mini.buildMin1(mini);
						grid.addArray(mini.getMinimum1());
						ModuleMap.initalizeMap(grid);
						sp.add(ModuleMap.getGrid());
						saveList = mini.getMinimum1();
					}
				}
				// if configbox is minconfig2 it set map to minconfig2
				else if (configBox.getItemText(configBox.getSelectedIndex()).equals("Minimum Config2")){
					for(int i = 0; i < modList.size(); i++){
						modList.get(i).makeClean();
					}
					
					mini.setMinimum2(modList);
					
					if(mini.isMinimum(mini.getMinimum2())){
						mini = mini.buildMin2(mini);
						grid.addArray(mini.getMinimum2());
						ModuleMap.initalizeMap(grid);
						sp.add(ModuleMap.getGrid());
						saveList = mini.getMinimum2();
					}
				}
				// if configbox is fullconfig1 it set map to fullconfig1
				else if(configBox.getItemText(configBox.getSelectedIndex()).equals("Full Config1")){
					for(int i = 0; i < modList.size(); i++){
						modList.get(i).makeClean();
					}
					
					full.setFull1(modList);
					
					full = full.buildFull1(full);
					grid.addArray(full.getFull1());
					ModuleMap.initalizeMap(grid);
					sp.add(ModuleMap.getGrid());
					saveList = full.getFull1();
				}
				// if configbox is fullconfig1 it set map to fullconfig2
				else if(configBox.getItemText(configBox.getSelectedIndex()).equals("Full Config2")){
					for(int i = 0; i < modList.size(); i++){
						modList.get(i).makeClean();
					}
					
					full.setFull2(modList);
					
					full = full.buildFull2(full);
					grid.addArray(full.getFull2());
					ModuleMap.initalizeMap(grid);
					sp.add(ModuleMap.getGrid());
					saveList = full.getFull2();
				}
				
				// updates panel
				vp.add(fp);
				vp.add(sp);
			}
			
		});
		// declares show map button
		final Button mapButton = new Button("Show Map");
		// handler displays module map
		mapButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				sp.clear();
				grid = new ModuleGrid();
				if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Local Storage")){
					modList = stor.getModuleList();
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test1")){
					modList = tCase.getTestCase("1");
					modList = tCase.getTestCase("1");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test2")){
					modList = tCase.getTestCase("2");
					modList = tCase.getTestCase("2");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test3")){
					modList = tCase.getTestCase("3");
					modList = tCase.getTestCase("3");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test4")){
					modList = tCase.getTestCase("4");
					modList = tCase.getTestCase("4");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test5")){
					modList = tCase.getTestCase("5");
					modList = tCase.getTestCase("5");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test6")){
					modList = tCase.getTestCase("6");
					modList = tCase.getTestCase("6");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test7")){
					modList = tCase.getTestCase("7");
					modList = tCase.getTestCase("7");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test8")){
					modList = tCase.getTestCase("8");
					modList = tCase.getTestCase("8");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test9")){
					modList = tCase.getTestCase("9");
					modList = tCase.getTestCase("9");
				}
				else if(arrayBox.getItemText(arrayBox.getSelectedIndex()).equals("Test10")){
					modList = tCase.getTestCase("10");
					modList = tCase.getTestCase("10");
				}
				else{
					Window.alert("Test Case Not ready");
				}
				grid.addArray(modList);
				ModuleMap.initalizeMap(grid);
				sp.add(ModuleMap.getGrid());
				vp.add(fp);
				vp.add(sp);
			}
				
			
		});
		
		final ListBox savedConfig = new ListBox();
		savedConfig.setStylePrimaryName("listBoxMargin");
		savedConfig.addItem("min1");
		savedConfig.addItem("min2");
		savedConfig.addItem("full1");
		savedConfig.addItem("full2");
		
		final Button save = new Button("Save");
		final Button load = new Button("Load");
		
		save.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				stor.StoreConfig(saveList, savedConfig.getItemText(savedConfig.getSelectedIndex()));
				Window.alert("Config saved");
			}
		});
		
		load.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(stor.getStoredConfig(savedConfig.getItemText(savedConfig.getSelectedIndex())).size() == 0){
					Window.alert("No config found");
				}
				else{ 
				Window.alert("Config loaded");
				grid = new ModuleGrid();
				grid.addArray(stor.getStoredConfig(savedConfig.getItemText(savedConfig.getSelectedIndex())));
				ModuleMap.initalizeMap(grid);
				sp.clear();
				sp.add(ModuleMap.getGrid());
				vp.add(fp);
				vp.add(sp);
				}
			}
		});
		
		sp.setPixelSize(1500, 700);
		fp.setHeight("25px");
		fp.add(arrayBox);
		fp.add(configBox);
		fp.add(mapButton);
		fp.add(sumbitButton);
		fp.add(savedConfig);
		fp.add(save);
		fp.add(load);

		vp.add(fp);
		vp.add(sp);
		

	} 
	
	public static VerticalPanel getConfig(){
		return vp;
	}
}
