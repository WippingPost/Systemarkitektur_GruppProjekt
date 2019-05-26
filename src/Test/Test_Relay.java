package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import rPiController.Relay;

public class Test_Relay {

	Relay relayNO;
	Relay relayNC;

	@Before
	public void init() {
		relayNO = new Relay(false);
		relayNC = new Relay(true);
	}

	@Test
	public void Activation_Test_RelayNO() {
		relayNO.activate();
		boolean actual = relayNO.isClosed();
		assertEquals(true, actual);
	}

	@Test
	public void Activation_Test_RelayNC() {
		relayNC.activate();
		boolean actual = relayNO.isClosed();
		assertEquals(false, actual);
	}
}
