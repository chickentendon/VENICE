package com.venice.mhcs.data.client;
//import com.venice.mhcs.GUI.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.venice.mhcs.data.GUI.ModuleMap;
import com.venice.mhcs.data.GUI.loginTab;
import com.venice.mhcs.data.GUI.moduleTab;
import com.venice.mhcs.data.shared.FieldVerifier;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.UmbrellaException;
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
	
	
	public static LocalStorage myStorage = new LocalStorage();
	
	final static TabLayoutPanel p = new TabLayoutPanel(2.0, Unit.EM);
	final loginTab loginHelper = new loginTab();
	final static ScrollPanel mapPanel = new ScrollPanel();		//Map Panel
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		GWT.setUncaughtExceptionHandler(new
		        GWT.UncaughtExceptionHandler() {
		        public void onUncaughtException(Throwable e) {
		          // do exception handling stuff
		      }
		      // do module loading stuff
		    });
		
		ModuleGrid moduleGrid = new ModuleGrid();
		moduleGrid.addArray(myStorage.getModuleList());
		
		
		
		final FlowPanel modPanel = new FlowPanel(); 		//Module Tab

		mapPanel.setPixelSize(1500, 400);					//Map Panel Size
		
		
		moduleTab.initAttributes(myStorage);
		
		//add characteristic
		modPanel.add(moduleTab.getAttributes());
		modPanel.add(moduleTab.getModList());
		
		//MODULE MAP STUFF
		ModuleMap.initalizeMap(moduleGrid);
	    mapPanel.add(ModuleMap.getGrid());
		modPanel.add(mapPanel);
		//END MODULE MAP STUFF
	
		


		

		loginHelper.initLoginPanel();
	
		p.add(loginHelper.getLoginPanel(), "Login");

    	
		// Attach the LayoutPanel to the RootLayoutPanel. The latter will listen for
	    // resize events on the window to ensure that its children are informed of
	    // possible size changes.
    RootLayoutPanel rp = RootLayoutPanel.get();
    rp.add(p);
	    

	};
	
	public final static TabLayoutPanel getP(){
		return p;
	}
	
	public final static ScrollPanel getMapPanel() {
		return mapPanel;
	}

}
