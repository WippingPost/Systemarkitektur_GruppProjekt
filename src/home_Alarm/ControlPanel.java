package home_Alarm;

public class ControlPanel {

	private AlarmTrigger trigger = new AlarmTrigger();
	private String state = "locked";
	private String secretPassword;


	public ControlPanel() {
		setPinCode("1234");
	}


	public void activateTrigger() {
		trigger.activate();
		System.out.println(" Larm AV!");
		state = "unlocked";
	}

	public void deActivateTrigger() {
		trigger.deActivate();
		System.out.println(" Larm PÅ!");
		state = "locked";
	}

	public boolean enterPinCode(String pinCode) {
		if(pinCodeIsCorrect(pinCode)) {
			System.out.print("Pinkod OK!");
			toggleState();
			return true;
		}
		else {
			System.out.println("Felaktig pinkod! Försök igen!");
			return false;
		}

	}

	private void toggleState() {
		if (state.equals("locked")) activateTrigger();
		else deActivateTrigger();
	}

	public AlarmTrigger getTrigger() {
		return trigger;
	}

	public String getState() {
		return state;
	}

	private boolean pinCodeIsCorrect(String pinCode) {
		if (pinCode.equals(secretPassword)) return true;
		else return false;
	}

	public void setPinCode(String pinCode) {
		secretPassword = pinCode;
	}

}
