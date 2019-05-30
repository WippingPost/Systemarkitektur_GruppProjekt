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
		if (getConnectionStatus().equalsIgnoreCase("connected")) {
			System.out.println("Mediaspelare är ansluten till Spotify!");
		}
	}

	public void disconnectSpotify() {
		connection.disconnect("Spotify");
		if (!getConnectionStatus().equalsIgnoreCase("connected")) {
			System.out.println("Mediaspelare har ingen anslutning till Spotify!");
		}
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

	public String getConnectionStatus() {
		return connection.getStatus();
	}

	public String getCurrentPlaylist() {
		return spotify.getCurrentPlayList();
	}


}
