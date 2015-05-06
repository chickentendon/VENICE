package configTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.venice.mhcs.data.client.Configuration;
import com.venice.mhcs.data.client.Module;

public class Full1Test {

	@Test
	public void test() {
		
		Configuration full = new Configuration();
		ArrayList<Module> fConfig = new ArrayList<Module>();
		
		Module m1 = new Module("131","Undamaged","1","1","0");
		Module m2 = new Module("132","Undamaged","2","2","0");
		Module m3 = new Module("133","Undamaged","3","3","0");
		Module m4 = new Module("134","Undamaged","4","4","0");
		
		Module m5 = new Module("141","Undamaged","5","5","0");
		Module m6 = new Module("142","Undamaged","6","6","0");
		Module m7 = new Module("143","Undamaged","7","7","0");
		Module m8 = new Module("144","Undamaged","8","8","0");
		
		Module m9 = new Module("151","Undamaged","9","9","0");
		Module m10= new Module("152","Undamaged","10","10","0");
		Module m11= new Module("153","Undamaged","11","11","0");
		Module m12= new Module("154","Undamaged","12","12","0");
		
		Module m13= new Module("161","Undamaged","13","13","0");
		Module m14= new Module("162","Undamaged","14","14","0");
		Module m15= new Module("163","Undamaged","15","15","0");
		Module m16= new Module("164","Undamaged","16","16","0");
		
		Module m17= new Module("171","Undamaged","17","17","0");
		Module m18= new Module("172","Undamaged","18","18","0");
		Module m19= new Module("173","Undamaged","19","19","0");
		Module m20= new Module("174","Undamaged","20","20","0");
		
		Module m21= new Module("181","Undamaged","21","21","0");
		Module m22= new Module("182","Undamaged","22","22","0");
		Module m23= new Module("183","Undamaged","23","23","0");
		Module m24= new Module("184","Undamaged","24","24","0");
		
		
		
		ArrayList<Module> plains = new ArrayList<Module>();
		for(int i = 1; i < 41; i++){
			plains.add(new Module(""+i,"Undamaged", ""+(100 - i), ""+(50-i),"0"));
		}
		ArrayList<Module> dorms = new ArrayList<Module>();
		for(int i = 61; i < 81; i++){
			dorms.add(new Module(""+i,"Undamaged",""+(100-i), ""+(50-(i/2)),"0"));
		}
		ArrayList<Module> sanitations = new ArrayList<Module>();
		for(int i = 91; i < 101; i++){
			sanitations.add(new Module(""+i, "Undamaged", ""+(80-i),""+(90-i),"0"));
		}
		ArrayList<Module> foodwater = new ArrayList<Module>();
		for(int i = 111; i < 121; i++){
			foodwater.add(new Module(""+i, "Undamaged", ""+(90-i),""+(100-i),"0"));
		}
		
		fConfig.add(m1);
		fConfig.add(m2);
		fConfig.add(m3);
		fConfig.add(m4);
		fConfig.add(m5);
		fConfig.add(m6);
		fConfig.add(m7);
		fConfig.add(m8);
		fConfig.add(m9);
		fConfig.add(m10);
		fConfig.add(m11);
		fConfig.add(m12);
		fConfig.add(m13);
		fConfig.add(m14);
		fConfig.add(m15);
		fConfig.add(m16);
		fConfig.add(m17);
		fConfig.add(m18);
		fConfig.add(m19);
		fConfig.add(m20);
		fConfig.add(m21);
		fConfig.add(m22);
		fConfig.add(m23);
		fConfig.add(m24);
		fConfig.addAll(foodwater);
		fConfig.addAll(sanitations);
		fConfig.addAll(dorms);
		fConfig.addAll(plains);
		
		full.setFull1(fConfig);
		full.buildFull1(full);
		
		for(int i = 0; i < 104; i++){
			System.out.println(full.getFull1().get(i).getModuleType() + " - " + full.getFull1().get(i).getX() +", "+ full.getFull1().get(i).getY());
		}
		
	}

}
