package mediaCenter;

import spotify.PlayList;

public class MediaPlayer extends Connection {

	boolean isPlaying;

	public MediaPlayer() {
		isPlaying = false;
	}

	public void connectToSpotify() {
		connect("Spotify");
	}

	public void diconnectSpotify() {
		disconnect("Spotify");
	}

	public void play(PlayList playList) {
		isPlaying = true;
	}

	public void stop() {
		isPlaying = false;
	}

	public boolean getState() {
		return isPlaying;
	}
}
