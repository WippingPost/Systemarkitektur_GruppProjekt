package Test;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mediaCenter.MediaPlayer;

public class Test_MediaPlayer {

	MediaPlayer mediaPlayer;

	@Before
	public void init() {
		mediaPlayer = new MediaPlayer();
		mediaPlayer.connectToSpotify();
	}

	@Test
	public void MediaPlayer_Running_Test() {
		mediaPlayer.play("UpTempo");
		boolean actual = mediaPlayer.isRunning();
		assertEquals(true, actual);
	}

	@Test
	public void MediaPlayer_NOT_Running_Test() {
		mediaPlayer.play("Classic");
		mediaPlayer.stop();
		boolean actual = mediaPlayer.isRunning();
		assertEquals(false, actual);
	}

	@Test
	public void MediaPlayer_getCurrentPlaylist_Test() {
		String actual = "UpTempo";
		mediaPlayer.play(actual);
		assertEquals(mediaPlayer.getCurrentPlaylist().toLowerCase(), actual.toLowerCase());
	}
}
