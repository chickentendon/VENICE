package com.venice.mhcs.data.client;

import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.venice.mhcs.data.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

		DefaultTextBox y = new DefaultTextBox("Y");
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
		
		//Declaring rotations ListBox and adding choices
		final ListBox rotate = new ListBox();
		rotate.addItem("Rotations");
		rotate.addItem("None");
		rotate.addItem("1");
		rotate.addItem("2");
		rotate.setStylePrimaryName("listBoxMargin");
		//Change handler to disable default "Rotations" value after a selection is made
		rotate.addChangeHandler(new ChangeHandler() {
	        public void onChange(ChangeEvent changeEvent) {
	            SelectElement selectElement = rotate.getElement().cast();
	            selectElement.getOptions().getItem(0).setDisabled(true);

	        }
	    });
		
		//Declaring damage ListBox and adding choices
		final ListBox damage = new ListBox();
		damage.addItem("Condition");
		damage.addItem("Damaged");
		damage.addItem("Undamaged");
		damage.addItem("Uncertain");
		damage.setStylePrimaryName("listBoxMargin");
		//Change handler to disable default "Damage" value after a selection is made
		damage.addChangeHandler(new ChangeHandler() {
	        public void onChange(ChangeEvent changeEvent) {
	            SelectElement selectElement = damage.getElement().cast();
	            selectElement.getOptions().getItem(0).setDisabled(true);

	        }
	    });
		
		//Declaration of submit button to add modules to list
		Button submit = new Button("Submit");
		submit.setStylePrimaryName("buttonMargin");
		
		//Adding panel elements to Module Logging panel
		attribPanel.add(idNum);
		attribPanel.add(x);
		attribPanel.add(y);
		attribPanel.add(rotate);
		attribPanel.add(damage);
		attribPanel.add(submit);
		attribPanel.setStylePrimaryName("panelMargin");
		//add characteristic
		modPanel.add(attribPanel);
		// Create a three-item tab panel, with the tab area 1.5em tall.
		TabLayoutPanel p = new TabLayoutPanel(2.0, Unit.EM);
		p.add(new HTML("Login"), "Login");
		p.add(modPanel, "Module Logging");
		p.add(new HTML("Habitat Config"), "Habitat Config");

		
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
