package moduleTesting;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.venice.mhcs.data.client.Module;

public class ConsistencyTest {

	@Test
	public void legalModule() {
		Module m1 = new Module();
		
		m1.setID("40");
		m1.setDamage("Undamaged");
		m1.setModuleType("40");
		m1.setRotation("2");
		m1.setX("31");
		m1.setY("13");

		assertTrue(m1.isConsistent());
	}
	
	@Ignore
	public void illegalIdModule(){
		Module m2 = new Module();
		
		m2.setID("0");
		m2.setDamage("Good");
		m2.setModuleType("0");
		m2.setRotation("2");
		m2.setX("31");
		m2.setY("13");

		assertFalse(m2.isConsistent());
	}
	
	@Ignore
	public void illegalDamageModule(){
		Module m3 = new Module();
		
		m3.setID("40");
		m3.setDamage("asdf");
		m3.setModuleType("40");
		m3.setRotation("2");
		m3.setX("31");
		m3.setY("13");

		assertFalse(m3.isConsistent());
	}
	
	@Ignore
	public void illegalxCoordModule(){
		Module m4 = new Module();
		
		m4.setID("40");
		m4.setDamage("Good");
		m4.setModuleType("40");
		m4.setRotation("2");
		m4.setX("0");
		m4.setY("13");
		
		assertFalse(m4.isConsistent());
	}
	
	@Ignore
	public void illegalyCoordModule(){
		Module m5 = new Module();
		
		m5.setID("40");
		m5.setDamage("Good");
		m5.setModuleType("40");
		m5.setRotation("2");
		m5.setX("31");
		m5.setY("0");
		
		assertFalse(m5.isConsistent());
	}
	
	@Ignore
	public void illegalRotationsModule(){
		Module m6 = new Module();
		
		m6.setID("40");
		m6.setDamage("Good");
		m6.setModuleType("40");
		m6.setRotation("3");
		m6.setX("31");
		m6.setY("13");
		
		assertFalse(m6.isConsistent());
	}
	
	@Test
	public void setterTest(){
		Module m7 = new Module();
		assertTrue(m7.setID("40"));
		assertFalse(m7.setID("41"));
		assertFalse(m7.setID("0"));
		assertFalse(m7.setID("190"));
		
		assertTrue(m7.setDamage("Undamaged"));
		assertTrue(m7.setDamage("Damaged"));
		assertTrue(m7.setDamage("Uncertain"));
		assertFalse(m7.setDamage("adsf"));
		assertFalse(m7.setDamage("good"));
		
		assertTrue(m7.setX("1"));
		assertTrue(m7.setX("50"));
		assertFalse(m7.setX("0"));
		assertFalse(m7.setX("101"));
		
		assertTrue(m7.setY("1"));
		assertTrue(m7.setY("50"));
		assertFalse(m7.setY("0"));
		assertFalse(m7.setY("51"));
		
		assertTrue(m7.setModuleType("40"));
		assertTrue(m7.setModuleType("65"));
		
		assertTrue(m7.setRotation("0"));
		assertTrue(m7.setRotation("1"));
		assertTrue(m7.setRotation("2"));
		assertFalse(m7.setRotation("50"));		
	}
}
