package rPiController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import home_Alarm.ControlPanel;
import mediaCenter.MediaPlayer;

public class RaspberryPi implements Runnable {

	private ControlPanel controlPanel;
	private RelayBoard relayBoard;
	public MediaPlayer mediaPlayer;
	private boolean userIsHome = false;
	private static String TIMEPATTERN = "HH"; // Pattern av "yyyy-MM-dd HH:mm:ss.SSS", "HH" returnerar endast timmar

	public RaspberryPi(ControlPanel controlPanel, RelayBoard relayBoard) {		// Kopplar upp kodpanel och rel�kort till Raspberry Pi
		this.controlPanel = controlPanel;
		this.relayBoard = relayBoard;
		mediaPlayer = new MediaPlayer();
	}


	@Override
	public void run() {
		while (true) {

			// Anv�ndaren kommer hem och larmar av via larmdosan
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

			// Anv�ndaren l�mnar hemmet samt larmar p� via larmdosan
			if (userIsHome && controlPanel.getState().equalsIgnoreCase("locked")) {
				turnOffLights();
				turnOffStereo();
				if (mediaPlayer.isRunning()) mediaPlayer.stop();
				userIsHome = false;
			}


			if (userIsHome && mediaPlayer.getConnectionStatus().equalsIgnoreCase("Connected")) {
				startPlayListAccordingToTime(getCurrentTime(TIMEPATTERN));
			}

			pauseThread();
		}
	}



	private void turnOnLights() {	// 5V signal till rel�
		relayBoard.relayLights.activate();
		System.out.println("T�nder lampor!");
	}


	private void turnOffLights() {	// 5V signal till rel�
		relayBoard.relayLights.deActivate();
		System.out.println("Sl�cker lampor!");
	}


	private void turnOnCoffeeMaker() {	// 5V signal till rel�
		relayBoard.relayCoffeeMaker.activate();
		System.out.println("Startar kaffebryggare!");
	}


	private void turnOnStereo() {	// 5V signal till rel�
		relayBoard.relayStereo.activate();
		System.out.println("Startar ljudanl�ggning!");
	}


	private void turnOffStereo() {
		relayBoard.relayStereo.deActivate();
		System.out.println("St�nger av ljudanl�ggning!");
	}


	private void turnOnWelcomeScreen() {		// 5V signal till rel�
		relayBoard.relayWelcomeScreen.activate();
		System.out.println("Skriver 'Willkommen im Schlagerland' p� sk�rmen.");
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


	// H�mtar aktuell tid som heltal efter val av pattern
	private int getCurrentTime(String pattern) {
	    return Integer.parseInt(LocalDateTime.now()
	       .format(DateTimeFormatter.ofPattern(pattern)));  //"yyyy-MM-dd HH:mm:ss.SSS"
	}


	public void startPlayListAccordingToTime(int time) {
		if (time >= 12 && time <= 15 && !mediaPlayer.getCurrentPlaylist().equalsIgnoreCase("UpTempo")) {
			mediaPlayer.play("UpTempo");
		}
		else if (time >= 16 && time <= 18 && !mediaPlayer.getCurrentPlaylist().equalsIgnoreCase("Classic")) {
			mediaPlayer.play("Classic");
		}
		else if (time >= 19 && time <= 20 && !mediaPlayer.getCurrentPlaylist().equalsIgnoreCase("Ballad")) {
			mediaPlayer.play("Ballad");
		}
		else if ((time < 12 || time >= 21) && mediaPlayer.isRunning()) {
			mediaPlayer.stop();
			turnOffStereo();
			System.out.println("Klockan �r mycket!\nDet �r snart l�ggdax!");
		}
	}
}
