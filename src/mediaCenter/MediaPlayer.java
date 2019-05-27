package mediaCenter;

import spotify.Spotify;

public class MediaPlayer extends Connection {

	boolean isPlaying;
	Spotify spotify;

	public MediaPlayer() {
		spotify = new Spotify();
		isPlaying = false;
	}

	public void connectToSpotify() {
		connect("Spotify");
	}

	public void disconnectSpotify() {
		disconnect("Spotify");
	}

	public void play(String nameOfPlayList) {
		if (getStatus().equalsIgnoreCase("Connected")) {
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
