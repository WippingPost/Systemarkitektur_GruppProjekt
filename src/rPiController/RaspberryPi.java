package rPiController;

import home_Alarm.ControlPanel;

public class RaspberryPi extends RelayBoard implements Runnable {

	private ControlPanel controlPanel = new ControlPanel();
	private boolean userIsHome = false;

	public RaspberryPi(ControlPanel controlPanel) {		// Kopplar upp kodpanelen till Raspberry Pi
		this.controlPanel = controlPanel;

	}


	@Override
	public void run() {
		while (true) {

			if (controlPanel.getState().equalsIgnoreCase("unlocked") && !userIsHome) {
				turnOnLights();
				turnOnCoffeeMaker();
				turnOnMediaPlayer();
				turnOnWelcomeScreen();
				userIsHome = true;
			}
			if (controlPanel.getState().equalsIgnoreCase("locked") && userIsHome) {
				turnOffLights();
				userIsHome = false;
			}

		}

	}


	public void turnOnLights() {	// 5V signal till relä
		relayLights.activate();
	}

	public void turnOffLights() {	// 5V signal till relä
		relayLights.activate();
	}

	public void turnOnCoffeeMaker() {	// 5V signal till relä
		relayCoffeeMaker.activate();
	}

	public void turnOnMediaPlayer() {	// 5V signal till relä
		relayMediaPlayer.activate();
	}

	public void turnOnWelcomeScreen() {	// 5V signal till relä
		relayWelcomeScreen.activate();
	}




}
