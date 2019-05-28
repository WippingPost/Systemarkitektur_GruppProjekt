package rPiController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import home_Alarm.ControlPanel;
import mediaCenter.MediaPlayer;

public class RaspberryPi implements Runnable {

	private ControlPanel controlPanel;
	private RelayBoard relayBoard;
	private MediaPlayer mediaPlayer;
	private boolean userIsHome = false;

	public RaspberryPi(ControlPanel controlPanel, RelayBoard relayBoard) {		// Kopplar upp kodpanelen till Raspberry Pi
		this.controlPanel = controlPanel;
		this.relayBoard = relayBoard;
		mediaPlayer = new MediaPlayer();
	}


	@Override
	public void run() {
		while (true) {

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
			if (userIsHome && controlPanel.getState().equalsIgnoreCase("locked")) {
				turnOffLights();
				userIsHome = false;
			}



			if (mediaPlayer.getConnectionStatus().equalsIgnoreCase("Connected") && !mediaPlayer.isRunning()) {
				startPlayListAccordingToTime();
			}

			if (mediaPlayer.isRunning()) {
				stopMediaPlayerIfTimeIsLate();
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


	private void turnOnWelcomeScreen() {		// 5V signal till relä
		relayBoard.relayWelcomeScreen.activate();
		System.out.println("Skriver Välkommen Hem på skärm!");
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



	private int getCurrentTime() {
	    return Integer.parseInt(LocalDateTime.now()
	       .format(DateTimeFormatter.ofPattern("HH")));  //"yyyy-MM-dd HH:mm:ss.SSS"
	}


	private void startPlayListAccordingToTime() {
		int time = getCurrentTime(); 	// Hämtar aktuell tid som hel timma

		if (time >= 12 && time <= 15 && !mediaPlayer.getCurrentPlaylist().equalsIgnoreCase("UpTempo")) {
			mediaPlayer.play("UpTempo");
		}
		if (time >= 16 && time <= 18 && !mediaPlayer.getCurrentPlaylist().equalsIgnoreCase("Classic")) {
			mediaPlayer.play("Classic");
		}
		if (time >= 19 && time <= 20 && !mediaPlayer.getCurrentPlaylist().equalsIgnoreCase("Ballad")) {
			mediaPlayer.play("Ballad");
		}
	}


	private void stopMediaPlayerIfTimeIsLate() {
		int time = getCurrentTime(); 	// Hämtar aktuell tid som hel timma

		if (time < 12 && time >= 21) {
			mediaPlayer.stop();
			System.out.println("Klockan är mycket!\nLäggdax!");
		}
	}

}
