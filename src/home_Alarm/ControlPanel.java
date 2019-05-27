package home_Alarm;

public class ControlPanel {

	private AlarmTrigger trigger = new AlarmTrigger();
	private String state = "";

	public void activateTrigger() {
		trigger.activate();
		state = "unlocked";
	}

	public void deActivateTrigger() {
		trigger.deActivate();
		state = "locked";
	}

	public AlarmTrigger getTrigger() {
		return trigger;
	}

	public String getState() {
		return state;
	}

}
