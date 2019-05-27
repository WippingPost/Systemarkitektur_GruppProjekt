package userInteraction;

import home_Alarm.ControlPanel;
import rPiController.RaspberryPi;

public class Main {

	public static void main (String[] args) {

		ControlPanel controlPanel = new ControlPanel();
		RaspberryPi raspberryPi = new RaspberryPi(controlPanel);
		new Thread(raspberryPi).start();

	}
}
