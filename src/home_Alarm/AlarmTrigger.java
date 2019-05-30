package home_Alarm;

public class AlarmTrigger {

	boolean isTriggered;

	public AlarmTrigger() {
		isTriggered = false;
	}

	public void activate() {
		isTriggered =true;
	}

	public void deActivate() {
		isTriggered = false;
	}

	public boolean isActivated() {
		return isTriggered;
	}
}
