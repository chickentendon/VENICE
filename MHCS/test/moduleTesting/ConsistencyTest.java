package moduleTesting;

import static org.junit.Assert.*;

import org.junit.Test;

import com.venice.mhcs.data.client.Module;

import com.venice.mhcs.data.client.ModuleType;

public class ConsistencyTest {

	@Test
	public void legalModule() {
		Module m1 = new Module();
		
		m1.setID("40");
		m1.setDamage("Good");
		m1.setModuleType(ModuleType.PLAIN);
		m1.setRotation("2");
		m1.setX("31");
		m1.setY("13");

		assertTrue(m1.isConsistent());
	}
	
	@Test
	public void illegalIdModule(){
		Module m2 = new Module();
		
		m2.setID("41");
		m2.setDamage("Good");
		m2.setModuleType(ModuleType.PLAIN);
		m2.setRotation("2");
		m2.setX("31");
		m2.setY("13");

		assertFalse(m2.isConsistent());
	}
	
	@Test
	public void illegalDamageModule(){
		Module m3 = new Module();
		
		m3.setID("40");
		m3.setDamage("asdf");
		m3.setModuleType(ModuleType.PLAIN);
		m3.setRotation("2");
		m3.setX("31");
		m3.setY("13");

		assertFalse(m3.isConsistent());
	}

}
