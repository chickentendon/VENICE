package com.venice.mhcs.data.client;
//import com.venice.mhcs.GUI.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Panel;
import com.venice.mhcs.data.GUI.ModuleMap;
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
	
	private ScrollPanel modList = new ScrollPanel();
	private DefaultTextBox idNum = new DefaultTextBox("Module ID");
	private DefaultTextBox x = new DefaultTextBox("X");
	private DefaultTextBox y = new DefaultTextBox("Y");
	
	private LocalStorage myStorage = new LocalStorage();
	private GUIComponets GUIHelper = new GUIComponets();

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
		//Declaration of Module Logging panel elements
		AbsolutePanel loginPanel = new AbsolutePanel();
		final FlowPanel modPanel = new FlowPanel();
		final ScrollPanel mapPanel = new ScrollPanel();
		mapPanel.setPixelSize(1500, 1000);
		FlowPanel attribPanel = new FlowPanel();
		FlowPanel pwPanel = new FlowPanel();
		VerticalPanel vertPanel = new VerticalPanel();
		vertPanel.getElement().setAttribute("align", "center");
		final Label top = new Label("Username:");
		top.getElement().setAttribute("align", "center");
	    final Label bottom = new Label("Password:");
	    bottom.getElement().setAttribute("align", "center");
	    final PasswordTextBox ptb = new PasswordTextBox();
	    ptb.getElement().setAttribute("align", "center");
	    ptb.setStyleName("logInMargin");
	    ptb.setMaxLength(6);
	    ptb.setVisibleLength(8);
	    ptb.setAlignment(TextAlignment.CENTER);
	    final TextBox user = new TextBox();
	    user.getElement().setAttribute("align", "center");
	    user.setStyleName("logInMargin");
	    user.setAlignment(TextAlignment.CENTER);
	    user.setMaxLength(6);
	    user.setVisibleLength(8);
	    	
		
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
		
		//Declaring rotations ListBox and adding choices
		final ListBox rotate = new ListBox();
		rotate.addItem("Rotations");
		rotate.addItem("0");
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
		
		GUIHelper.resetPanel();
		//GUIHelper.updatePanel(myStorage.getModuleList());
		//modList.add(GUIHelper.getScrollPanel());
		GUIHelper.updatePanel(myStorage.getModuleList(), myStorage);
		modList.add(GUIHelper.getScrollPanel());
		
		submit.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event){
				
				String ID = "";
				String X = "";
				String Y = "";
				String rotation = "";
				String dam = "";
				
				if(!idNum.getText().equals("")) {
					ID = idNum.getText();
				}
				else Window.alert("You must enter something for Id");
				//String ID = idNum.getText();
				if(!x.getText().equals("")){
					X = x.getText();
				}
				else Window.alert("You must enter something for X");
				if(!y.getText().equals("")){
					Y = y.getText();
				}
				else Window.alert("You must enter something for Y");
				if(!rotate.getItemText(rotate.getSelectedIndex()).equals("")){
					rotation = rotate.getItemText(rotate.getSelectedIndex());
				}
				else Window.alert("You must enter something for rotations");
				if(!damage.getItemText(damage.getSelectedIndex()).equals("")){
					dam = damage.getItemText(damage.getSelectedIndex());
				}
				else Window.alert("You must enter something for damage");
				Module newModule = new Module(ID,dam,X,Y,rotation);
				
				if(newModule.isConsistent()){
			
					GUIHelper.resetPanel();
					//GUIHelper.updatePanel(myStorage.getModuleList());
					//modList.add(GUIHelper.getScrollPanel());
					myStorage.Store(newModule);
					GUIHelper.updatePanel(myStorage.getModuleList(), myStorage);
					modList.add(GUIHelper.getScrollPanel());

				}
				else{	
				}
				
			}
		});
		
		submit.setStylePrimaryName("buttonMargin");
		
		//Adding panel elements to Module Logging panel
		attribPanel.add(idNum);
		attribPanel.add(x);
		attribPanel.add(y);
		attribPanel.add(rotate);
		attribPanel.add(damage);
		attribPanel.add(submit);
		attribPanel.setStylePrimaryName("panelMargin");
		modList.setStylePrimaryName("panelMargin");
		
		//add characteristic
		modPanel.add(attribPanel);
		modPanel.add(modList);
		
		ModuleMap.initalizeMap(moduleGrid);
//		Grid g = new Grid(50, 100);
//		g.setSize("5000px", "2500px");
//		for(int row = 0; row < g.getRowCount(); row++)
//	    {
//	    	for(int col = 0; col < g.getColumnCount(); col++)
//	    	{
//	    		g.getCellFormatter().setHeight(row, col, "50px");
//    			g.getCellFormatter().setWidth(row, col, "50px");
//	    	}
//	    }
//		
//	    for(int row = 0; row < g.getRowCount(); row++)
//	    {
//	    	for(int col = 0; col < g.getColumnCount(); col++)
//	    	{
//	    		if(moduleGrid.getItem(col + 1, g.getRowCount() - row) == null)
//	    		{
//	    		}
//	    		else if(moduleGrid.getItem(col + 1, g.getRowCount() - row).getType().equals("Plain"))
//	    		{
//	    			g.setWidget((row), col, new Image("images/Plain.jpg"));
//	    		}
//	    		else if(moduleGrid.getItem(col + 1, g.getRowCount() - row).getType().equals("Airlock"))
//	    		{
//	    			g.setWidget((row), col, new Image("images/Airlock.jpg"));
//	    		}
//	    		else if(moduleGrid.getItem(col + 1, g.getRowCount() - row).getType().equals("Canteen"))
//	    		{
//	    			g.setWidget((row), col, new Image("images/Canteen.jpg"));
//	    		}
//	    		else if(moduleGrid.getItem(col + 1, g.getRowCount() - row).getType().equals("Control"))
//	    		{
//	    			g.setWidget((row), col, new Image("images/Control.jpg"));
//	    		}
//	    		else if(moduleGrid.getItem(col + 1, g.getRowCount() - row).getType().equals("Dormitory"))
//	    		{
//	    			g.setWidget((row), col, new Image("images/Dormitory.jpg"));
//	    		}
//	    		else if(moduleGrid.getItem(col + 1, g.getRowCount() - row).getType().equals("Food and Water"))
//	    		{
//	    			g.setWidget((row), col, new Image("images/Food.jpg"));
//	    		}
//	    		else if(moduleGrid.getItem(col + 1, g.getRowCount() - row).getType().equals("Gym and Relaxation"))
//	    		{
//	    			g.setWidget((row), col, new Image("images/Gym.jpg"));
//	    		}
//	    		else if(moduleGrid.getItem(col + 1, g.getRowCount() - row).getType().equals("Power"))
//	    		{
//	    			g.setWidget((row), col, new Image("images/Power.jpg"));
//	    		}
//	    		else if(moduleGrid.getItem(col + 1, g.getRowCount() - row).getType().equals("Sanitation"))
//	    		{
//	    			g.setWidget((row), col, new Image("images/Sanitation.jpg"));
//	    		}
//			  }
//	    }
	    
//	    g.setVisible(true);
	    mapPanel.add(ModuleMap.getGrid());
	    mapPanel.scrollToRight();
		modPanel.add(mapPanel);
		final Label space = new Label("     ");
	    
		vertPanel.add(top);
	    vertPanel.add(user);
	    vertPanel.add(bottom);
	    vertPanel.add(ptb);
	    vertPanel.add(space); 
	    
	    
	   

		
		
		// Create a three-item tab panel, with the tab area 1.5em tall.
		final TabLayoutPanel p = new TabLayoutPanel(2.0, Unit.EM);
		final Button enter = new Button("Log In");
		enter.getElement().setAttribute("align", "center");
		pwPanel.add(vertPanel);
		loginPanel.add(pwPanel);
		vertPanel.add(enter);
		p.add(loginPanel, "Login");

		
    	enter.addClickHandler(new ClickHandler() {
    	public void onClick(ClickEvent event) {
    			if (user.getText().equals("astro1") && ptb.getText().equals("daisy")) {
    				Window.alert("Log In : Successful");
    				p.getTabWidget(0).removeFromParent();
    				p.add(modPanel, "Module Logging");
    				p.add(new HTML("Habitat Config"), "Habitat Config");
    				
    			}
    			else {
    			Window.alert("Incorrect user ID/password, try again");
    			ptb.setText("");
    			user.setText("");
    			user.setFocus(true);
    			}

    	}
    }); 	
    	
    	
    	
		// Attach the LayoutPanel to the RootLayoutPanel. The latter will listen for
	    // resize events on the window to ensure that its children are informed of
	    // possible size changes.
	    RootLayoutPanel rp = RootLayoutPanel.get();
	    rp.add(p);
	    

	};
	
	
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
			
		} 

} 
}
