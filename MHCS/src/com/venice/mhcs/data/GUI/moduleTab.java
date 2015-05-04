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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusListener;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;

public class moduleTab {
	
	//Declaration of module attribute widgets
	private final static FlowPanel attribPanel = new FlowPanel();
	private static ScrollPanel modList = new ScrollPanel();
	private final static  FlowPanel modPanel = new FlowPanel(); 		//Module Tab
	private static defaultTextBox idNum = new defaultTextBox("Module ID");
	private static defaultTextBox x = new defaultTextBox("X");
	private static defaultTextBox y = new defaultTextBox("Y");
	final static ScrollPanel mapPanel = new ScrollPanel();
	private static Configuration mini = new Configuration();
	//Declaration of panels

	
	private static LocalStorage myStorage;
	private static GUIComponets GUIHelper = new GUIComponets();
	private static ModuleGrid moduleGrid = new ModuleGrid();

	
	public static void initAttributes(LocalStorage Stor) {
	
	myStorage = Stor;
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
	
	moduleGrid.addArray(myStorage.getModuleList());
	
	//MODULE MAP STUFF
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
	
	//Change handler to disable default "Rotations" value after a selection is made
	rotate.addChangeHandler(new ChangeHandler() {
        @Override
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
        @Override
		public void onChange(ChangeEvent changeEvent) {
            SelectElement selectElement = damage.getElement().cast();
            selectElement.getOptions().getItem(0).setDisabled(true);

        }
    });
	
	//Declaration of submit button to add modules to list
	Button submit = new Button("Submit");
	GUIHelper.resetPanel();
	GUIHelper.updatePanel(myStorage.getModuleList(), myStorage);
	modList.add(GUIHelper.getScrollPanel());
	
	submit.addClickHandler(new ClickHandler() {
		@Override
		public void onClick(ClickEvent event){
			
			String ID = "";
			String X = "";
			String Y = "";
			String rotation = "";
			String dam = "";
			SoundController soundController = new SoundController();
			Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_BASIC,
			    "http://www.d.umn.edu/~ronni111/war/module.mp3");
			sound.setVolume(1);

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
				
				
				//if(mini.isMinimum(myStorage.getModuleList())) Window.alert("Minimum Configuration Available");
				
				
				moduleGrid.addModule(newModule);
				ModuleMap.updateMap(moduleGrid);
				GUIHelper.updatePanel(myStorage.getModuleList(), myStorage);
				modList.add(GUIHelper.getScrollPanel());
				mapPanel.add(ModuleMap.getGrid());
//				ModuleMap.getGrid().setVisible(true);
				sound.play();
				
				

			}
			else{ }
			
		}
	});
	
	submit.setStylePrimaryName("buttonMargin");
	attribPanel.add(idNum);
	attribPanel.add(x);
	attribPanel.add(y);
	attribPanel.add(rotate);
	attribPanel.add(damage);
	attribPanel.add(submit);
	attribPanel.setStylePrimaryName("panelMargin");
	modList.setStylePrimaryName("panelMargin");
	
	modPanel.add(attribPanel);
	modPanel.add(modList);
	modPanel.add(mapPanel);
}
	
	
	public static FlowPanel getAttributes() {
		return attribPanel;
	}
	
	public static ScrollPanel getModList(){
		return modList;
	}
	
	public static FlowPanel getModPanel() {
		
		return modPanel;
	}
	
	public final static ScrollPanel getMapPanel() {
		return mapPanel;
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
			
		} 

} 
}
	

