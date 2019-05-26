package home_Alarm;

public class ControlPanel {

	private AlarmTrigger trigger = new AlarmTrigger();


	public void activateTrigger() {
		trigger.activate();
	}

	public void deActivateTrigger() {
		trigger.deActivate();
	}

	public AlarmTrigger getTrigger() {
		return trigger;
	}

}
