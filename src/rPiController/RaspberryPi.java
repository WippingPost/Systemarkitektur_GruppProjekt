package rPiController;


public class RaspberryPi extends RelayBoard {

	public RaspberryPi() {
		// TODO ......
	}


	public void turnOnLights() {	// 5V signal till rel�
		relayLights.activate();
	}

	public void turnOnCoffeeMaker() {	// 5V signal till rel�
		relayCoffeeMaker.activate();
	}

	public void turnOnMediaPlayer() {	// 5V signal till rel�
		relayMediaPlayer.activate();
	}

	public void turnOnWelcomeScreen() {	// 5V signal till rel�
		relayWelcomeScreen.activate();
	}
}
