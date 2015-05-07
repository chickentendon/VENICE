package com.venice.mhcs.data.GUI;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.venice.mhcs.data.client.MHCS;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class homeTab  {
	
	//Declare panels 
	private static VerticalPanel tommyPC = new VerticalPanel();
	static VerticalPanel vp = new VerticalPanel();
	static FlowPanel space = new FlowPanel();
	public static AbsolutePanel homePanel = new AbsolutePanel();
	
	//Declare logos and background image
	static Image nasa = new Image("images/nasa.png");
	static Image esa = new Image("images/esa.jpg");
	static Image mars = new Image("images/mars1");

	//Declare labels
	static Label header = new Label("Mars Habitat Control System");
	static Label version = new Label("Version 1.5");
	static int count = 120;
	static Label countDown = new Label();
    static Button b = new Button("Reset Calibration Timer");
	
    //Timer declaration and handling
	static Timer tenDay = new Timer() {
		  @Override
		public void run() {
		        countDown.setText("Time remaining: " + Integer.toString(count) + " seconds");
		        count--;
		        if(count==0) {
		            Window.alert("Calibrate Rover!");
		            b.setEnabled(true);
		            MHCS.getP().selectTab(0);
		            this.cancel(); 
		        }
		  }
	};
		  
	public static void start(){
		startFeed();
	}
	
	public static VerticalPanel startFeed() {
	
	String url =
				"http://api.wunderground.com/api/9d52d4b7aab6ed63/astronomy/conditions/q/55812.json";
				 url = URL.encode(url);
			// Send request to server and catch any errors.
			RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
			try {
			 Request request = builder.sendRequest(null, new RequestCallback() {
		
			 @Override
			public void onError(Request request, Throwable exception) {
				 Window.alert("onError: Couldn't retrieve JSON");
				 }
			
				 @Override
				public void onResponseReceived(Request request, Response response) {
					 if (200 == response.getStatusCode()) {
					 String rt = response.getText();
					 vp = update(rt); //METHOD CALL TO DO SOMETHING WITH RESPONSE TEXT
					 } else {
					 Window.alert("Couldn't retrieve JSON (" + response.getStatusCode()
					 + ")");
					 }
				 }
			 });
			} 
			catch (RequestException e) {
				Window.alert("RequestException: Couldn't retrieve JSON");
			}
		
			return vp;
	}

	/**
	 * Pulls weather and sunset info from Wunderground API
	 * @param rt
	 * @return vp
	 */
	public static VerticalPanel update(String rt) {
		
		 String sAll = rt;
		 String sunsetHour;
		 String sunsetMin;
		 String sunset;
		 
		 //create objects and get conditions info
		 JSONObject jA = (JSONObject)JSONParser.parseLenient(sAll);
		 JSONValue jSun = jA.get("sun_phase");
		 JSONValue jTry = jA.get("current_observation");
		 JSONObject astro = (JSONObject)JSONParser.parseLenient(jSun.toString());
		 JSONObject jB = (JSONObject)JSONParser.parseLenient(jTry.toString());
		 
		 //Pull weather and sunset info
		 JSONValue temp = jB.get("temp_c");
		 JSONValue visibility = jB.get("visibility_km");
		 JSONValue sunsetH = astro.get("sunset");
		 
		 //Store info to string
		 String sTemp = "Temp : " + temp.toString() + " °C";
		 String sVisibility = "Visibility : " + visibility.toString() + " km";
		 sVisibility = sVisibility.replace("\"", "");
		 
		 //Parse extra text to format info to  (hours:minutes)
		 sunsetHour = sunsetH.toString();
		 sunsetHour = sunsetHour.subSequence(9, 11).toString();
		 sunsetMin = sunsetH.toString();
		 sunsetMin = sunsetMin.subSequence(24, 26).toString();
		 sunset = "Today's sunset occurs at " + sunsetHour + ":" + sunsetMin;
		 
		 //Creating labels out of API info
		 Label tempLabel = new Label(sTemp);
		 Label visLabel = new Label(sVisibility);
		 Label sunsetLabel = new Label (sunset);
		 
		 //set CSS defined styles to labels
		 tempLabel.setStylePrimaryName("labelText");
		 visLabel.setStylePrimaryName("labelText");
		 sunsetLabel.setStylePrimaryName("labelText");
		 
		 //include Wunderground logo per their guidelines
		 Image logo = new Image("images/weather.png");
		 vp.add(logo);
		 vp.add(tempLabel); //TO VIEW
		 vp.add(visLabel); //TO VIEW
		 vp.add(sunsetLabel);

		 return vp;
	}
	
	/**
	 * creates main panel widgets
	 * @return homePanel
	 */
	public static AbsolutePanel getMain() {
		//timer characteristics
		start();
		tenDay.scheduleRepeating(1000);
		
		//set CSS defined style of countdown text
		countDown.getElement().getStyle().setMarginTop(50, Style.Unit.PX);
		countDown.setStylePrimaryName("labelText");
		
		//Disable reset button while timer is active 
		b.setEnabled(false);
		
		//Add clickhandler to reset rover calibration and play sound on click
		b.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
 				//initialize and play sound on click
				SoundController soundController = new SoundController();
				Sound sound = soundController.createSound(Sound.MIME_TYPE_AUDIO_BASIC,
				    "http://www.d.umn.edu/~ronni111/war/rovercalibrated.mp3");
				sound.play();
				
			//set count to 600 and restart timer	
			count = 600;
    		tenDay.run();
    		tenDay.scheduleRepeating(1000);
    		
    		//disable button while timer is activeß
    		b.setEnabled(false);
		}
		});
		
		//set CSS defined styles to homePanel and header
		homePanel.setStylePrimaryName("homeBackground");
		header.setStylePrimaryName("h1");
		version.setStylePrimaryName("versionLabel");
		
		//set dimensions of space panel and make it invisible
		space.setHeight("18px");
		space.setWidth("1");
		space.getElement().getStyle().setOpacity(100);
		
		//center align elements in vp and tommyPCß
		vp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		tommyPC.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		//add widgets to tommyPC VerticalPanel
		tommyPC.add(header);
		tommyPC.add(version);
		tommyPC.add(vp);
		tommyPC.add(countDown);
		tommyPC.add(b);
		tommyPC.add(space);
		tommyPC.add(nasa);
		tommyPC.add(esa);
		
		//Add tommyPC to Homepanel
		homePanel.add(tommyPC);
		
		//center align HomePanel 
		homePanel.getElement().setAttribute("align", "center");
		
		return homePanel;
	}
}