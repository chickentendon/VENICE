package com.venice.mhcs.data.GUI;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.Button;
import com.venice.mhcs.data.GUI.defaultTextBox;
import com.venice.mhcs.data.client.Configuration;
import com.venice.mhcs.data.client.GUIComponets;
import com.venice.mhcs.data.client.LocalStorage;
import com.venice.mhcs.data.client.Module;
import com.venice.mhcs.data.client.ModuleGrid;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;

public class moduleTab {
	
	//Declaration of module attribute widgets
	private static ScrollPanel modList = new ScrollPanel();
	private static defaultTextBox idNum = new defaultTextBox("Module ID");
	private static defaultTextBox x = new defaultTextBox("X");
	private static defaultTextBox y = new defaultTextBox("Y");
	private static Configuration mini = new Configuration();
	
	//Declaration of panels
	private final static FlowPanel attribPanel = new FlowPanel();
	private final static  AbsolutePanel modPanel = new AbsolutePanel(); 
	final static ScrollPanel mapPanel = new ScrollPanel();
	
	//Declaration of local storage, GUI components and module grid
	private static LocalStorage myStorage;
	private static GUIComponets GUIHelper = new GUIComponets();
	private static ModuleGrid moduleGrid = new ModuleGrid();

	/**
	 * Initialize module tab widgets
	 * @param Stor
	 */
	public static void initAttributes(LocalStorage Stor) {
	
	//set Stor equal to local variable myStorage
	myStorage = Stor;
	
	//set CSS style of textboxs
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
	
	//updates grid from local storage arraylist
	moduleGrid.addArray(myStorage.getModuleList());
	
	//set, initialize and add grid to the mapPanel
	mapPanel.setPixelSize(1500, 400);		
	ModuleMap.initalizeMap(moduleGrid);
    mapPanel.add(ModuleMap.getGrid());

	
	//Declaring rotations ListBox and adding choices
	final ListBox rotate = new ListBox();
	rotate.addItem("Rotations");
	rotate.addItem("0");
	rotate.addItem("1");
	rotate.addItem("2");
	rotate.setStylePrimaryName("listBoxMargin");
	
	//Declaring damage ListBox and adding choices
	final ListBox damage = new ListBox();
	damage.addItem("Condition");
	damage.addItem("Damaged");
	damage.addItem("Undamaged");
	damage.addItem("Uncertain");
	damage.setStylePrimaryName("listBoxMargin");
	
	/**
	 * Change handler to disable default "Rotations" value after a selection is made
	 */
	rotate.addChangeHandler(new ChangeHandler() {
        @Override
		public void onChange(ChangeEvent changeEvent) {
            SelectElement selectElement = rotate.getElement().cast();
            selectElement.getOptions().getItem(0).setDisabled(true);

        }
    });
	
	/**
	 * Change handler to disable default "Damage" value after a selection is made
	 */
	damage.addChangeHandler(new ChangeHandler() {
        @Override
		public void onChange(ChangeEvent changeEvent) {
            SelectElement selectElement = damage.getElement().cast();
            selectElement.getOptions().getItem(0).setDisabled(true);

        }
    });
	
	//Declaration of submit button to add modules to list
	Button submit = new Button("Submit");
	
	//reset and update panel 
	GUIHelper.resetPanel();
	GUIHelper.updatePanel(myStorage.getModuleList(), myStorage);
	modList.add(GUIHelper.getScrollPanel());
	
	/**
	 * Click handler for submit module button
	 */
	submit.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event){
			
			//set boxes back to default values
			String ID = "";
			String X = "";
			String Y = "";
			String rotation = "";
			String dam = "";
			
			//Declare sound to play when a module is submitted
			SoundController soundController = new SoundController();
			Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_BASIC,
			    "http://www.d.umn.edu/~ronni111/war/module.mp3");
			sound.setVolume(50);
			
			//logic checks for module attribute fields
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
			
			//checking if newModule has valid characteristics and doesnt already exist in modList
			if(newModule.isConsistent()){
		
				//reset the modList to include newModule
				GUIHelper.resetPanel();
				//play "module added" output
				sound.play();
				newModule.makeClean();
				//add newModule to local storage
				myStorage.Store(newModule);
				
				
	
				
				//add newModule to the grid
				moduleGrid.addModule(newModule);
				//update moduleMap with newModule
				ModuleMap.updateMap(moduleGrid);
				//update modList
				GUIHelper.updatePanel(myStorage.getModuleList(), myStorage);
				//add new modList to module tab
				modList.add(GUIHelper.getScrollPanel());
				//add updated map to module tab
				mapPanel.add(ModuleMap.getGrid());
			}
		}
	});
	
	//set CSS defined margins to submit button
	submit.setStylePrimaryName("buttonMargin");
	
	//add all module input boxes/drop menus to attribPanel
	attribPanel.add(idNum);
	attribPanel.add(x);
	attribPanel.add(y);
	attribPanel.add(rotate);
	attribPanel.add(damage);
	attribPanel.add(submit);
	
	//set CSS defined margins to attribPanel, modList, mapPanel, 
	//and modPanel, respectively
	attribPanel.setStylePrimaryName("panelMargin");
	modList.setStylePrimaryName("dialogVPanel");
	mapPanel.setStylePrimaryName("homeBackground");
	modPanel.setStylePrimaryName("modBackground");
	
	//add attribPanel, modList, and mapPanel to modPanel
	modPanel.add(attribPanel, 0, 90);
	modPanel.add(modList, 550, 25);
	modPanel.add(mapPanel, 0, 250);
}
	
	
	/**
	 * Getter for attribPanel
	 * @return attribPanel
	 */
	public static FlowPanel getAttributes() {
		return attribPanel;
	}
	
	/**
	 * Getter for modList
	 * @return modList
	 */
	public static ScrollPanel getModList(){
		return modList;
	}
	
	/**
	 * Getter for modPanel
	 * @return modPanel
	 */
	public static AbsolutePanel getModPanel() {
		
		return modPanel;
	}
	
	/**
	 * Getter for mapPanel
	 * @return mapPanel
	 */
	public final static ScrollPanel getMapPanel() {
		return mapPanel;
	}
	
	/**
	 * Refresh method for mapPanel after a module is added/removed
	 */
	public final static void refreshMap(){
		moduleGrid = new ModuleGrid();
		moduleGrid.addArray(myStorage.getModuleList());
		ModuleMap.updateMap(moduleGrid);
	    mapPanel.add(ModuleMap.getGrid());
	}
	
	/**
	 * Helper class to create default text in textboxs
	 * implemented by Max Ronning
	 */
	@SuppressWarnings("deprecation")
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
	

