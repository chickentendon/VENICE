package com.venice.mhcs.data.client;

import com.venice.mhcs.data.GUI.loginTab;
import com.venice.mhcs.data.GUI.moduleTab;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;

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
	
	//Declaration of local storage 
	public static LocalStorage myStorage = new LocalStorage();
	
	//Declaration of tabbed interface
	final static TabLayoutPanel p = new TabLayoutPanel(2.0, Unit.EM);
	
	//declaration of loginHelper
	final loginTab loginHelper = new loginTab();

	/**
	 * This is the entry point method.
	 */
	@Override
	public void onModuleLoad() {
		
	GWT.setUncaughtExceptionHandler(new
	        GWT.UncaughtExceptionHandler() {
	        @Override
			public void onUncaughtException(Throwable e) {
	          // do exception handling stuff
	      }
	      // do module loading stuff
	    });		
		
		//Declaration of modPanel
	final FlowPanel modPanel = new FlowPanel(); 		//Module Tab
	
	//Add widgets to modPanel
	modPanel.add(moduleTab.getAttributes());
	modPanel.add(moduleTab.getModList());
		
	//initialize loginPanel
	loginTab.initLoginPanel();
	
	//set animation when moving between tabs
	p.setAnimationVertical(true);
	p.setAnimationDuration(200);
	
	//add loginTab on project launch
	p.add(loginHelper.getLoginPanel(), "Login");

    	
	// Attach the LayoutPanel to the RootLayoutPanel. The latter will listen for
	// resize events on the window to ensure that its children are informed of
    // possible size changes.	
    RootLayoutPanel rp = RootLayoutPanel.get();
    
    //set CSS defined margins to root panel
    rp.setStylePrimaryName("translateMargin");
    
    //add TabLayoutPanel p to rootpanel
    rp.add(p);

	};
	
	/**
	 * Getter for tablayoutpanel "p"
	 * @return p
	 */
	public final static TabLayoutPanel getP(){
		return p;
	}
	

}

