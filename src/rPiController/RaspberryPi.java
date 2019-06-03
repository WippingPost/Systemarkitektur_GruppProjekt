package rPiController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import home_Alarm.ControlPanel;
import mediaCenter.MediaPlayer;

public class RaspberryPi implements Runnable {

	private ControlPanel controlPanel;
	private RelayBoard relayBoard;
	public MediaPlayer mediaPlayer;
	private boolean userIsHome = false;
	private static String TIMEPATTERN = "HH"; // Pattern av "yyyy-MM-dd HH:mm:ss.SSS", "HH" returnerar endast timmar

	public RaspberryPi(ControlPanel controlPanel, RelayBoard relayBoard) {		// Kopplar upp kodpanel och reläkort till Raspberry Pi
		this.controlPanel = controlPanel;
		this.relayBoard = relayBoard;
		mediaPlayer = new MediaPlayer();
	}


	@Override
	public void run() {
		while (true) {

			// Användaren kommer hem och larmar av via larmdosan
			if (!userIsHome && controlPanel.getState().equalsIgnoreCase("unlocked")) {
				pauseThread();
				turnOnLights();
				pauseThread();
				turnOnCoffeeMaker();
				pauseThread();
				turnOnStereo();
				pauseThread();
				startMediaPlayer();
				turnOnWelcomeScreen();
				userIsHome = true;
			}

			// Användaren lämnar hemmet samt larmar på via larmdosan
			if (userIsHome && controlPanel.getState().equalsIgnoreCase("locked")) {
				turnOffLights();
				turnOffStereo();
				if (mediaPlayer.isRunning()) mediaPlayer.stop();
				userIsHome = false;
			}


			if (userIsHome && mediaPlayer.getConnectionStatus().equalsIgnoreCase("Connected")) {
				startPlayListAccordingToTime(getCurrentTime(TIMEPATTERN));
			}

			pauseThread();
		}
	}



	private void turnOnLights() {	// 5V signal till relä
		relayBoard.relayLights.activate();
		System.out.println("Tänder lampor!");
	}


	private void turnOffLights() {	// 5V signal till relä
		relayBoard.relayLights.deActivate();
		System.out.println("Släcker lampor!");
	}


	private void turnOnCoffeeMaker() {	// 5V signal till relä
		relayBoard.relayCoffeeMaker.activate();
		System.out.println("Startar kaffebryggare!");
	}


	private void turnOnStereo() {	// 5V signal till relä
		relayBoard.relayStereo.activate();
		System.out.println("Startar ljudanläggning!");
	}


	private void turnOffStereo() {
		relayBoard.relayStereo.deActivate();
		System.out.println("Stänger av ljudanläggning!");
	}


	private void turnOnWelcomeScreen() {		// 5V signal till relä
		relayBoard.relayWelcomeScreen.activate();
		System.out.println("Skriver 'Willkommen im Schlagerland' på skärmen.");
	}


	private void startMediaPlayer() {
		mediaPlayer.connectToSpotify();
		System.out.println("Startar mediaspelare!");
	}



	private void pauseThread() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	// Hämtar aktuell tid som heltal efter val av pattern
	private int getCurrentTime(String pattern) {
	    return Integer.parseInt(LocalDateTime.now()
	       .format(DateTimeFormatter.ofPattern(pattern)));  //"yyyy-MM-dd HH:mm:ss.SSS"
	}


	public void startPlayListAccordingToTime(int time) {
		if (time >= 12 && time <= 15 && !mediaPlayer.getCurrentPlaylist().equalsIgnoreCase("UpTempo")) {
			mediaPlayer.play("UpTempo");
		}
		else if (time >= 16 && time <= 18 && !mediaPlayer.getCurrentPlaylist().equalsIgnoreCase("Classic")) {
			mediaPlayer.play("Classic");
		}
		else if (time >= 19 && time <= 20 && !mediaPlayer.getCurrentPlaylist().equalsIgnoreCase("Ballad")) {
			mediaPlayer.play("Ballad");
		}
		else if ((time < 12 || time >= 21) && mediaPlayer.isRunning()) {
			mediaPlayer.stop();
			turnOffStereo();
			System.out.println("Klockan är mycket!\nDet är snart läggdax!");
		}
	}
}
