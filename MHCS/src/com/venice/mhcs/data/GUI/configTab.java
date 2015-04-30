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
import com.venice.mhcs.data.client.TestCases;
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
	private static FlowPanel fp = new FlowPanel();
	private static ScrollPanel sp = new ScrollPanel();
	private static VerticalPanel vp = new VerticalPanel();
	private static ModuleMap map = new ModuleMap();
	private static ArrayList<Module> modList = new ArrayList<Module>();
	private static ArrayList<Module> saveList = new ArrayList<Module>();
	//private static TestCases tCase = new TestCases();
	public static void initConfig(){
		final TestCases tCase = new TestCases();
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
		
		final Button sumbitButton = new Button("Submit");
		sumbitButton.addClickHandler(new ClickHandler() {
			
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
				else{
					Window.alert("Test Case Not ready");
				}
				
				
				if(configBox.getItemText(configBox.getSelectedIndex()).equals("Minimum Config1")){
					mini.setMinimum1(modList);
					
					if(mini.isMinimum(mini.getMinimum1())){
						mini = mini.buildMin1(mini);
						grid.addArray(mini.getMinimum1());
						map.initalizeMap(grid);
						sp.add(map.getGrid());
						saveList = mini.getMinimum1();
					}
				}
				
				else if (configBox.getItemText(configBox.getSelectedIndex()).equals("Minimum Config2")){
					mini.setMinimum2(modList);
					
					if(mini.isMinimum(mini.getMinimum2())){
						mini = mini.buildMin2(mini);
						grid.addArray(mini.getMinimum2());
						map.initalizeMap(grid);
						sp.add(map.getGrid());
						saveList = mini.getMinimum2();
					}
				}
				vp.add(fp);
				vp.add(sp);
			}
			
		});
		
		final ListBox savedConfig = new ListBox();
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
				
			}
		});
		
		sp.setPixelSize(700, 700);
		fp.setHeight("25px");
		fp.add(arrayBox);
		fp.add(configBox);
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