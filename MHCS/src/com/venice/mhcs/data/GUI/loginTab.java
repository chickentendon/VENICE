package com.venice.mhcs.data.GUI;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Button;
import com.venice.mhcs.data.client.MHCS;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.KeyboardListener;
import com.google.gwt.user.client.ui.KeyboardListenerAdapter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
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
	
	//Declaration of panels for formatting
	static VerticalPanel head = new VerticalPanel();
	final static Label space = new Label("     ");

	/**
	 * initialize the loginPanel
	 */
	public static void initLoginPanel() {
		
		//set CSS defined characteristics to loginPanel widgets
		loginPanel.setStylePrimaryName("homeBackground");
		homeTab.homePanel.setStylePrimaryName("homeBackground");
		homeTab.header.setStylePrimaryName("h1");
		vertPanel.getElement().setAttribute("align", "center");
	    
		//Add and align MHCS header, and login textboxes
		head.add(homeTab.header);
	    head.getElement().setAttribute("align", "center");
	    top.getElement().setAttribute("align", "center");
		top.setStylePrimaryName("whiteText");
		bottom.setStylePrimaryName("whiteText");
	    bottom.getElement().setAttribute("align", "center");
	    ptb.getElement().setAttribute("align", "center");
	    user.getElement().setAttribute("align", "center");
	    ptb.setStyleName("logInMargin");
	    user.setStyleName("logInMargin");
	    
	    //set characteristics of password textbox
	    ptb.setMaxLength(6);
	    ptb.setVisibleLength(10);
	    ptb.setAlignment(TextAlignment.CENTER);
	    ptb.addKeyDownHandler(new KeyDownHandler() {

	       //keyboard handler to click "Log In" button when the enter key is pressed
	    	@Override
	        public void onKeyDown(KeyDownEvent event) {
	         if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
	                     enter.click();
	               }
	        }
	    });
	    
	    //set characteristics for username textbox
	    user.setAlignment(TextAlignment.CENTER);
	    user.setMaxLength(6);
	    user.setVisibleLength(10);

	    //add username label, username textbox, password label, password textbox to vertpanel
	    vertPanel.add(top);
	    vertPanel.add(user);
	    vertPanel.add(bottom);
	    vertPanel.add(ptb);
	    vertPanel.add(space);
	    
	    //align widgets in vertpanel to align vertically
	    vertPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

	    //set alignment of login button to center
		enter.getElement().setAttribute("align", "center");
		
		//add login to vertPanel
		vertPanel.add(enter);
		
		//add header and vertPanel to pwPanel
		pwPanel.add(head);
		pwPanel.add(vertPanel);
		
		//add pwPanel to loginPanel
		loginPanel.add(pwPanel);
		
		//initialize modules in local storage before login
		moduleTab.initAttributes(MHCS.myStorage);
		
		//initialize configuration tab before login
		configTab.initConfig();
	
		//click handler to hide login tab on successful login, 
		//and show home, logging and config tab
		enter.addClickHandler(new ClickHandler() {
	    	@Override
			public void onClick(ClickEvent event) {
    			if (getUser().getText().equals("astro1") && getPTB().getText().equals("lol")) {
    				SoundController soundController = new SoundController();
    				Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_BASIC,
    				    "http://www.d.umn.edu/~ronni111/war/loginsuccessful.mp3");
    				sound.setVolume(50);
    				sound.play();
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
	
	/**
	 * Getter for loginPanel
	 * @return loginPanel
	 */
	public AbsolutePanel getLoginPanel() {
		return loginPanel;
	}
	
}



