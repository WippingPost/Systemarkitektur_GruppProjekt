package rPiController;


public class Relay {

	private enum TYPE {
		NO,
		NC
	}

	private boolean closed;
	private TYPE relayType;


	public Relay(boolean normallyClosed) {
		if (normallyClosed) {
			relayType = TYPE.NC;
			closed = true;
		}
		else {
			relayType = TYPE.NO;
			closed = false;
		}
	}


	public void activate() {
		switch (relayType) {
		case NO:
			closed = true;
			break;
		case NC:
			closed = false;
			break;
		}
	}


	public void deActivate() {
		switch (relayType) {
		case NO:
			closed = false;
			break;
		case NC:
			closed = true;
			break;
		}
	}


	public boolean isClosed() {
		return closed;
	}
}
