package mediaCenter;

import spotify.Spotify;

public class MediaPlayer {

	boolean isPlaying;
	Spotify spotify;
	Connection connection;

	public MediaPlayer() {
		connection = new Connection();
		spotify = new Spotify();
		isPlaying = false;
	}

	public void connectToSpotify() {
		connection.connect("Spotify");
		System.out.println(connection.getStatus());
	}

	public void disconnectSpotify() {
		connection.disconnect("Spotify");
		System.out.println(connection.getStatus());
	}

	public void play(String nameOfPlayList) {
		if (connection.getStatus().equalsIgnoreCase("Connected")) {
			spotify.play(nameOfPlayList);
			isPlaying = true;
		}
	}

	public void stop() {
		spotify.stop();
		isPlaying = false;
	}

	public boolean isRunning() {
		return isPlaying;
	}
}
