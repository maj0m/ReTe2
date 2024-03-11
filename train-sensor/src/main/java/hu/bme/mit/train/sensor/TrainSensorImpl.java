package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private Table<Long, String, Integer> table = HashBasedTable.create();

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	public void tableNextVal() {
		Long sysTime = System.currentTimeMillis();
		table.put(sysTime, "joyStick pos", user.getJoystickPosition());
		table.put(sysTime, "reference speed", controller.getReferenceSpeed());
	}

	public int getTableSize() {
		return table.size(); 
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}

}
