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
				startSpotify();
				turnOnWelcomeScreen();
				userIsHome = true;
			}
			if (userIsHome && controlPanel.getState().equalsIgnoreCase("locked")) {
				turnOffLights();
				userIsHome = false;
			}


			int time = getCurrentTime();

			if (!mediaPlayer.isRunning()) {
				if (time >= 12 && time <= 15) {
					// TODO
				}
				if (time >= 16 && time <= 18) {
					// TODO
				}
				if (time >= 19 && time <= 20) {
					// TODO
				}
			}
			if (mediaPlayer.isRunning()) {
				if (time < 12 && time >= 21) {
					// TODO
				}
			}


		}

	}


	public void turnOnLights() {	// 5V signal till relä
		relayBoard.relayLights.activate();
	}

	public void turnOffLights() {	// 5V signal till relä
		relayBoard.relayLights.deActivate();
	}

	public void turnOnCoffeeMaker() {	// 5V signal till relä
		relayBoard.relayCoffeeMaker.activate();
	}

	public void turnOnStereo() {	// 5V signal till relä
		relayBoard.relayStereo.activate();
	}

	public void turnOnWelcomeScreen() {		// 5V signal till relä
		relayBoard.relayWelcomeScreen.activate();
	}


	private void startSpotify() {
		mediaPlayer.connectToSpotify();
	}

	private void pauseThread() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public int getCurrentTime() {
	    return Integer.parseInt(LocalDateTime.now()
	       .format(DateTimeFormatter.ofPattern("HH")));  //"yyyy-MM-dd HH:mm:ss.SSS"
	}

}
