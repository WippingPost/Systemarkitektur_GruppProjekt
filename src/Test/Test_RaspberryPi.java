package Test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import home_Alarm.ControlPanel;
import rPiController.RaspberryPi;
import rPiController.RelayBoard;

public class Test_RaspberryPi {

	RaspberryPi raspberryPi;
	RelayBoard relayBoard = new RelayBoard();
	ControlPanel controlPanel = new ControlPanel();

	@Before
	public void init() {
		raspberryPi =  new RaspberryPi(controlPanel, relayBoard);
		raspberryPi.mediaPlayer.connectToSpotify();
	}

	/*
	 * Test för att se så att rätt spellista
	 * startas beroende på vad klockan är.
	 */

	@Test
	public void PlayList_According_To_Time_Test1() {

		String actual = "";

		// Spellistan 'UpTempo' ska spelas mellan kl. 12:00 och 15:59
		raspberryPi.startPlayListAccordingToTime(12);
		actual = raspberryPi.mediaPlayer.getCurrentPlaylist();
		assertEquals("uptempo", actual.toLowerCase());
	}

	@Test
	public void PlayList_According_To_Time_Test2() {

		String actual = "";

		// Spellistan 'Classic' ska spelas mellan kl. 16:00 och 18:59
		raspberryPi.startPlayListAccordingToTime(16);
		actual = raspberryPi.mediaPlayer.getCurrentPlaylist();
		assertEquals("classic", actual.toLowerCase());
	}

	@Test
	public void PlayList_According_To_Time_Test3() {

		String actual = "";

		// Spellistan 'Ballad' ska spelas mellan kl. 19:00 och 20:59
		raspberryPi.startPlayListAccordingToTime(19);
		actual = raspberryPi.mediaPlayer.getCurrentPlaylist();
		assertEquals("ballad", actual.toLowerCase());
	}

	@Test
	public void PlayList_According_To_Time_Test4() {

		String actual;

		// Efter kl 21 ska det vara tyst i huset. Ingen spellista spelas
		raspberryPi.startPlayListAccordingToTime(21);
		actual = raspberryPi.mediaPlayer.getCurrentPlaylist();
		assertEquals("", actual.toLowerCase());
	}

	@Test
	public void PlayList_According_To_Time_Test5() {

		boolean actual;

		// Efter kl 21 ska det vara tyst i huset. Mediaspelaren ska vara avstängd.
		raspberryPi.startPlayListAccordingToTime(21);
		actual = raspberryPi.mediaPlayer.isRunning();
		assertEquals(false, actual);
	}

}
