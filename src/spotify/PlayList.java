package spotify;

import java.awt.List;

public class PlayList {

	private String name;
	private List tracks = new List();

	public PlayList(String name) {
		this.name = name;
	}

	public void addTrack(String title) {
		tracks.add(title);
	}

	public void removeTrack(String title) {
		tracks.remove(title);
	}

	public List getPlayList() {
		return tracks;
	}
}
