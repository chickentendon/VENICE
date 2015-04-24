package com.venice.mhcs.data.GUI;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.venice.mhcs.data.GUI.defaultTextBox;
import com.venice.mhcs.data.client.GUIComponets;
import com.venice.mhcs.data.client.LocalStorage;
import com.venice.mhcs.data.client.ModuleGrid;
import com.venice.mhcs.data.client.MHCS.DefaultTextBox;
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

public class moduleTab {
	
	//Declaration of module attribute widgets
	private static ScrollPanel modList = new ScrollPanel();
	private static defaultTextBox idNum = new defaultTextBox("Module ID");
	private static defaultTextBox x = new defaultTextBox("X");
	private static defaultTextBox y = new defaultTextBox("Y");
	
	//Declaration of panels
	final static FlowPanel modPanel = new FlowPanel();
	final FlowPanel attribPanel = new FlowPanel();
	
	private LocalStorage myStorage = new LocalStorage();
	private GUIComponets GUIHelper = new GUIComponets();
	final ModuleGrid moduleGrid = new ModuleGrid();
	//moduleGrid.addArray(myStorage.getModuleList());

	
	public static void initAttributes() {
	
	idNum.setStylePrimaryName("textBoxMargin");
	x.setStylePrimaryName("textBoxMargin");
	y.setStylePrimaryName("textBoxMargin");
	
	//Set max number of characters allowed
	//Also, set textbox width to reasonable size
	idNum.setMaxLength(3);
	idNum.setVisibleLength(9);
	idNum.setAlignment(TextAlignment.CENTER);
	
	x.setMaxLength(3);
	x.setVisibleLength(3);
	x.setAlignment(TextAlignment.CENTER);
	
	y.setMaxLength(3);
	y.setVisibleLength(3);
	y.setAlignment(TextAlignment.CENTER);
	
}
	
	public static FlowPanel getModPanel(){
		Window.alert("moduleTab getModPanel()");
		return modPanel;
	}
}
