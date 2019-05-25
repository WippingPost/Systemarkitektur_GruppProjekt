package mediaCenter;

public class Connection {

	private boolean connected = false;
	private String name;

	public void connect(String name) {
		// Skapa en anslutning till name
		connected = true;
	}

	public void disconnect(String name) {
		// Avsluta anslutning till name
		connected = false;
	}

	public String getStatus() {
		if (connected) return "Connected";
		else return "Disconnected";
	}
}
