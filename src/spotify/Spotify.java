package spotify;

import java.util.ArrayList;

public class Spotify {

	ArrayList<PlayList> playList = new ArrayList<>();
	private boolean isPlaying;

	public Spotify() {
		addNewPlayList("UpTempo");
		addNewPlayList("Classic");
		addNewPlayList("Ballad");

	}

	public void play(String nameOfPlayList) {
		getPlayList(nameOfPlayList);
		// TODO: Spela upp spellistan efter att den h�mtats
		isPlaying = true;
		System.out.println("Spelar spellistan '" + nameOfPlayList);
	}

	public void stop() {
		isPlaying = false;
	}

	public boolean isRunning() {
		return isPlaying;
	}

	public void addNewPlayList(String name) {
		playList.add(new PlayList(name));
	}

	private PlayList getPlayList(String name) {
		for (PlayList pList : playList) {
			if (pList.getName().equalsIgnoreCase("name")) return pList;
		}
		return null;
	}
}
