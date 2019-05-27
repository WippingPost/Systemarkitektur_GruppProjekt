package spotify;

import java.util.ArrayList;

public class PlayList {

	private ArrayList<String> tracks = new ArrayList<>();
	private String name;

	public PlayList(String name) {
		this.name = name;
	}

	public void addTrack(String title) {
		tracks.add(title);
	}

	public void removeTrack(String title) {
		tracks.remove(title);
	}

	public ArrayList<String> getTracks() {
		return tracks;
	}

	public String getName() {
		return name;
	}
}
