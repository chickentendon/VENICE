package com.venice.mhcs.GUI;

import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.TextBox;

public class defaultTextBox extends TextBox implements FocusListener { 
    private String defaultText; 

    public defaultTextBox(String defText) { 
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
		
	} 


}
