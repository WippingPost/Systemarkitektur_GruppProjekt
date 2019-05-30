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

		System.out.println("Användaren kom precis hem och står vid sin larmpanel.\n"
				+ "Larmet är påslaget! Pinkoden är '1234'.\nSkriv 'exit' för att avsluta.\n");

		while(!input.equalsIgnoreCase("exit")) {
			System.out.println("Ange pinkod för att larma av/på:");
			input = scanner.nextLine();
			if (!input.equals("exit")) controlPanel.enterPinCode(input);

		}
		System.out.println("Hej då!");
		System.exit(0);
	}
}
