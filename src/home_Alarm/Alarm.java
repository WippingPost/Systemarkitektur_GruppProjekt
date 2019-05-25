package home_Alarm;

import java.util.ArrayList;

public class Alarm {

	private ArrayList<AlarmTrigger> triggers = new ArrayList<>();
	private ControlPanel controlPanel;

	public Alarm() {
		controlPanel = new ControlPanel();
		triggers.add(controlPanel.getTrigger());
	}

	public void addTrigger(AlarmTrigger alarmTrigger) {
		triggers.add(alarmTrigger);
	}

	public void updateState() {
		for (AlarmTrigger trigger: triggers) {
			if (trigger.getState()) {
				soundAlarm();
			}
		}
	}

	private void soundAlarm() {
		// TODO: Starta larmsignal
	}
}
