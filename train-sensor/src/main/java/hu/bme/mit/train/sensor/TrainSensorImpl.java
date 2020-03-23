package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private boolean alarmState;
	private TrainController controller;
	private int speedLimit = 5;

	public TrainSensorImpl(TrainController controller) {
		this.controller = controller;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.alarmState = checkForAlarm(speedLimit);
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);


	}

	private boolean checkForAlarm(int speedLimit){
		return speedLimit < 0 || speedLimit > 500 || speedLimit < this.controller.getReferenceSpeed() * 0.5;
	}

	public boolean getAlarmState() {
		return alarmState;
	}

	public void setAlarmState(boolean alarmState) {
		this.alarmState = alarmState;
	}


}
