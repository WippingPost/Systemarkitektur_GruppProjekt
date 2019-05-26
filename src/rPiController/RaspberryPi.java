package rPiController;


public class RaspberryPi extends RelayBoard {

	public RaspberryPi() {
		// TODO ......
	}


	public void turnOnLights() {	// 5V signal till relä
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
