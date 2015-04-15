package com.venice.mhcs.data.client;

import apple.laf.JRSUIConstants.Widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.venice.mhcs.data.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MHCS implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);
	
	
          

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		//Declaration of Module Logging panel elements
		Panel modPanel = new FlowPanel();
		DefaultTextBox idNum = new DefaultTextBox("Module ID");
		DefaultTextBox x = new DefaultTextBox("X");
		DefaultTextBox y = new DefaultTextBox("Y");
		
		//Set max number of characters allowed
		//Also, set textbox width to reasonable size
		idNum.setMaxLength(3);
		idNum.setVisibleLength(9);
		
		x.setMaxLength(3);
		x.setVisibleLength(3);
		
		y.setMaxLength(3);
		y.setVisibleLength(3);
		
		//Declaring rotations ListBox and adding choices
		final ListBox rotate = new ListBox();
		rotate.addItem("Rotations");
		rotate.addItem("None");
		rotate.addItem("1");
		rotate.addItem("2");
		//Change handler to disable default "Rotations" value after a selection is made
		rotate.addChangeHandler(new ChangeHandler() {
	        public void onChange(ChangeEvent changeEvent) {
	            SelectElement selectElement = rotate.getElement().cast();
	            selectElement.getOptions().getItem(0).setDisabled(true);

	        }
	    });
		
		//Declaring damage ListBox and adding choices
		final ListBox damage = new ListBox();
		damage.addItem("Damage");
		damage.addItem("None");
		damage.addItem("Minor");
		damage.addItem("Moderate");
		damage.addItem("Severe");
		//Change handler to disable default "Damage" value after a selection is made
		damage.addChangeHandler(new ChangeHandler() {
	        public void onChange(ChangeEvent changeEvent) {
	            SelectElement selectElement = damage.getElement().cast();
	            selectElement.getOptions().getItem(0).setDisabled(true);

	        }
	    });
		
		//Declaration of submit button to add modules to list
		Button submit = new Button("Submit");
		
		//Adding panel elements to Module Logging panel
		modPanel.add(idNum);
		modPanel.add(x);
		modPanel.add(y);
		modPanel.add(rotate);
		modPanel.add(damage);
		modPanel.add(submit);
		
		// Create a three-item tab panel, with the tab area 1.5em tall.
		TabLayoutPanel p = new TabLayoutPanel(2.0, Unit.EM);
		p.add(new HTML("Login"), "Login");
		p.add(modPanel, "Module Logging");
		p.add(new HTML("Habitat Config"), "Habitat Config");
		//p.add(modPanel, "Module Logging");
		
		
		// Attach the LayoutPanel to the RootLayoutPanel. The latter will listen for
	    // resize events on the window to ensure that its children are informed of
	    // possible size changes.
	    RootLayoutPanel rp = RootLayoutPanel.get();
	    rp.add(p);
			  }
	
	/**
	 * Helper class to create default text in textboxes
	 * @author maxronning
	 *
	 */
	public class DefaultTextBox extends TextBox implements FocusListener { 
        private String defaultText; 

        public DefaultTextBox(String defText) { 
                defaultText = defText; 
                setText(defaultText); 
                addFocusListener(this); 
        } 

        public void setDefaultText(String defText) { 
                defaultText = defText; 
        } 

        public String getDefaultText() { 
                return defaultText; 
        } 


		@Override
		public void onFocus(com.google.gwt.user.client.ui.Widget sender) {
			this.setText("");
			
		}

		@Override
		public void onLostFocus(com.google.gwt.user.client.ui.Widget sender) {
			this.setText(defaultText);
			
		} 

} 
}
