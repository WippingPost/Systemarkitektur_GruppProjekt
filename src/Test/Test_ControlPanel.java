package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import home_Alarm.ControlPanel;

public class Test_ControlPanel {

	ControlPanel controlPanel;
	String pinCode;

	@Before
	public void init() {
		controlPanel = new ControlPanel();
		controlPanel.setPinCode("1234");
	}

	@Test
	public void ControlPanel_CheckIfLocked_Test() {
		String actual = controlPanel.getState();
		assertEquals("locked", actual);
	}

	@Test
	public void ControlPanel_Unlock_Test() {
		controlPanel.enterPinCode("1234");
		String actual = controlPanel.getState();
		assertEquals("unlocked", actual);
	}

	@Test
	public void ControlPanel_WrongPinCode_Unlock_Test() {
		controlPanel.enterPinCode("4321");
		String actual = controlPanel.getState();
		assertNotEquals("unlocked", actual);
	}
}
