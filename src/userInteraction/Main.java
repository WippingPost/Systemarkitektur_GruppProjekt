package userInteraction;

import java.util.Scanner;

import home_Alarm.ControlPanel;
import rPiController.RaspberryPi;
import rPiController.RelayBoard;

public class Main {

	public static void main (String[] args) {

		ControlPanel controlPanel = new ControlPanel();
		RelayBoard relayBoard = new RelayBoard();
		RaspberryPi raspberryPi = new RaspberryPi(controlPanel, relayBoard);
		Scanner scanner = new Scanner(System.in);
		new Thread(raspberryPi).start();
		String input = "";
		while(!input.equalsIgnoreCase("end")) {
			input = scanner.nextLine();
			if (input.equals("1")) {
				controlPanel.activateTrigger();
			}
			if (input.equals("2")) {
				controlPanel.deActivateTrigger();
			}
		}
		System.exit(0);
	}
}
