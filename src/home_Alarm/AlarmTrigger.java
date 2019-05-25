package home_Alarm;

public class AlarmTrigger {

	boolean isTrigged;

	public AlarmTrigger() {
		isTrigged = false;
	}

	public boolean getState() {
		return isTrigged;
	}

	public void setState(boolean state) {
		isTrigged = state;
	}
}
