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

		System.out.println("Anv�ndaren kom precis hem och st�r vid sin larmpanel.\n"
				+ "Larmet �r p�slaget! Pinkoden �r '1234'.\nSkriv 'exit' f�r att avsluta.\n");

		while(!input.equalsIgnoreCase("exit")) {
			System.out.println("Ange pinkod f�r att larma av/p�:");
			input = scanner.nextLine();
			if (!input.equals("exit")) controlPanel.enterPinCode(input);

		}
		System.out.println("Hej d�!");
		System.exit(0);
	}
}
