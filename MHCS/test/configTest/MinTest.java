package configTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.venice.mhcs.data.client.Configuration;
import com.venice.mhcs.data.client.Module;

public class MinTest {

	@Test
	public void testMin1() {
		
		Configuration min = new Configuration();
		ArrayList<Module> minConfig = new ArrayList<Module>();
		
		Module m1 = new Module("1","Undamaged","1","1","0");
		Module m2 = new Module("2","Undamaged","1","2","0");
		Module m3 = new Module("3","Undamaged","1","3","0");
		Module m4 = new Module("171","Undamaged","1","4","0");
		Module m5 = new Module("141","Undamaged","1","5","0");
		Module m6 = new Module("161","Undamaged","1","6","0");
		Module m7 = new Module("151","Undamaged","1","7","0");
		Module m8 = new Module("111","Undamaged","1","8","0");
		Module m9 = new Module("61","Undamaged","1","9","0");
		
		minConfig.add(m1);
		minConfig.add(m2);
		minConfig.add(m3);
		minConfig.add(m4);
		minConfig.add(m5);
		minConfig.add(m6);
		minConfig.add(m7);
		minConfig.add(m8);
		minConfig.add(m9);
		
		assertTrue(min.setMinimum1(minConfig));
		
		assertTrue(min.isMinimum(min.getMinimum1()));
	}
	
	@Test
	public void testMin2(){
		Configuration min = new Configuration();
		ArrayList<Module> minConfig = new ArrayList<Module>();
		
		Module m1 = new Module("1","Undamaged","1","1","0");
		Module m2 = new Module("2","Undamaged","1","2","0");
		Module m3 = new Module("3","Undamaged","1","3","0");
		Module m4 = new Module("171","Undamaged","1","4","0");
		Module m5 = new Module("141","Undamaged","1","5","0");
		Module m6 = new Module("161","Undamaged","1","6","0");
		Module m7 = new Module("151","Undamaged","1","7","0");
		Module m8 = new Module("111","Undamaged","1","8","0");
		Module m9 = new Module("4","Undamaged","1","9","0");
		
		minConfig.add(m1);
		minConfig.add(m2);
		minConfig.add(m3);
		minConfig.add(m4);
		minConfig.add(m5);
		minConfig.add(m6);
		minConfig.add(m7);
		minConfig.add(m8);
		minConfig.add(m9);
		
		assertFalse(min.setMinimum2(minConfig));
		
		assertFalse(min.isMinimum(min.getMinimum2()));
	}

}
