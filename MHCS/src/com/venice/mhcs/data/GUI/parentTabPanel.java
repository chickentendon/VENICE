package com.venice.mhcs.data.GUI;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.venice.mhcs.data.GUI.configTab;
import com.venice.mhcs.data.GUI.loginTab;
import com.venice.mhcs.data.GUI.moduleTab;
import com.venice.mhcs.data.GUI.configTab.*;
import com.venice.mhcs.data.GUI.loginTab.*;
import com.venice.mhcs.data.GUI.moduleTab.*;
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

public class parentTabPanel {

		public void initParentTab() {
			
			final TabLayoutPanel p = new TabLayoutPanel(2.0, Unit.EM);
			//loginTab.getEnterButton().addClickHandler(new ClickHandler() {
			
			//public void onClick(ClickEvent event) {
				if (loginTab.getUser().getText().equals("astro1") && loginTab.getPTB().getText().equals("daisy")) {
					Window.alert("Log In : Successful");
					p.getTabWidget(0).removeFromParent();
					p.add(moduleTab.modPanel, "Module Logging");
					p.add(new HTML("Habitat Config"), "Habitat Config");
					
				}
				else {
				Window.alert("Incorrect user ID/password, try again");
				loginTab.getPTB().setText("");
				loginTab.user.setText("");
				loginTab.user.setFocus(true);
				}
	
			}
	}
 	
