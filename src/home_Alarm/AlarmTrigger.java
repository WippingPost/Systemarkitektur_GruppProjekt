package home_Alarm;

public class AlarmTrigger {

	boolean isTrigged;

	public AlarmTrigger() {
		isTrigged = false;
	}

	public void activate() {
		isTrigged =true;
	}

	public void deActivate() {
		isTrigged = false;
	}

	public boolean isActivated() {
		return isTrigged;
	}
}
