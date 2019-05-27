package userInteraction;

import home_Alarm.ControlPanel;
import rPiController.RaspberryPi;
import rPiController.RelayBoard;

public class Main {

	public static void main (String[] args) {

		ControlPanel controlPanel = new ControlPanel();
		RelayBoard relayBoard = new RelayBoard();
		RaspberryPi raspberryPi = new RaspberryPi(controlPanel, relayBoard);
		new Thread(raspberryPi).start();

	}
}
