package com.venice.mhcs.data.GUI;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.venice.mhcs.data.client.MHCS;
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
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
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

public class loginTab {
		
	//Declaration of login widgets
	public final static  Button enter = new Button("Log In");
	public final static  PasswordTextBox ptb = new PasswordTextBox();
	public final static Label top = new Label("Username:");
	public final static  Label bottom = new Label("Password:");
	public final static  TextBox user = new TextBox();
		
	//Declaration of panels
	public static AbsolutePanel loginPanel = new AbsolutePanel();
	public static FlowPanel pwPanel = new FlowPanel();
	public static VerticalPanel vertPanel = new VerticalPanel();
	final static Label space = new Label("     ");
	
	public static void initLoginPanel() {
		
		final RootLayoutPanel rp = RootLayoutPanel.get();
		
		vertPanel.getElement().setAttribute("align", "center");
		top.getElement().setAttribute("align", "center");
	    bottom.getElement().setAttribute("align", "center");
	    ptb.getElement().setAttribute("align", "center");
	    user.getElement().setAttribute("align", "center");
	    
	    ptb.setStyleName("logInMargin");
	    ptb.setMaxLength(6);
	    ptb.setVisibleLength(10);
	    ptb.setAlignment(TextAlignment.CENTER);
	    
	    user.setStyleName("logInMargin");
	    user.setAlignment(TextAlignment.CENTER);
	    user.setMaxLength(6);
	    user.setVisibleLength(10);
	    
	    vertPanel.add(top);
	    vertPanel.add(user);
	    vertPanel.add(bottom);
	    vertPanel.add(ptb);
	    vertPanel.add(space);
	    vertPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    
	    final moduleTab moduleHelper = new moduleTab();

		final Button enter = new Button("Log In");
		enter.getElement().setAttribute("align", "center");
		
		vertPanel.add(enter);
		pwPanel.add(vertPanel);
		loginPanel.add(pwPanel);
		
		moduleTab.initAttributes(MHCS.myStorage);
		configTab.initConfig();
		
		enter.addClickHandler(new ClickHandler() {
	    	public void onClick(ClickEvent event) {
    			if (getUser().getText().equals("astro1") && getPTB().getText().equals("lol")) {
    				Window.alert("Log In : Successful");
    				MHCS.getP().getTabWidget(0).removeFromParent(); 
    				MHCS.getP().add(homeTab.getMain(), "Home");
    				MHCS.getP().add(moduleTab.getModPanel(), "Module Logging");

    				MHCS.getP().add(configTab.getConfig(), "Habitat Config");
    			}
    			else {
    			Window.alert("Incorrect user ID/password, try again");
    			getPTB().setText("");
    			getUser().setText("");
    			getUser().setFocus(true);
    			}
	    	}
	    	});


	}
	
	/**
	 * Getter for enter button
	 * @return enter
	 */
	public static Button getEnter() {
		return enter;
	}
	
	/**
	 * Getter for password textbox ptb
	 * @return ptb
	 */
	public static PasswordTextBox getPTB() {
		return ptb;
	}
	
	/**
	 * Getter for user textbox
	 * @return user 
	 */
	public static TextBox getUser() {
		return user;
	}
	
	public AbsolutePanel getLoginPanel() {
		return loginPanel;
	}
	
}
