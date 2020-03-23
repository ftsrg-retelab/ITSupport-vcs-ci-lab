package hu.bme.mit.train.system;

import hu.bme.mit.train.user.UserChangeRefSpeed;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.system.TrainSystem;
import org.junit.rules.Timeout;

public class TrainSystemTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;
	
	@Before
	public void before() {
		TrainSystem system = new TrainSystem();
		controller = system.getController();
		sensor = system.getSensor();
		user = system.getUser();

		sensor.overrideSpeedLimit(50);
	}
	
	@Test
	public void OverridingJoystickPosition_IncreasesReferenceSpeed() {
		sensor.overrideSpeedLimit(20);
		Assert.assertEquals(0, controller.getReferenceSpeed());
		user.overrideJoystickPosition(5);
		Assert.assertEquals(5, controller.getReferenceSpeed());
	}

	@Test
	public void OverridingJoystickPosition_Followsspeed_Automatically() {
		sensor.overrideSpeedLimit(20);

		Assert.assertEquals(0, controller.getReferenceSpeed());

		user.overrideJoystickPosition(5);
		Assert.assertEquals(5, controller.getReferenceSpeed());
	}

	@Test
	public void OverridingJoystickPositionToNegative_SetsReferenceSpeedToZero() {
		user.overrideJoystickPosition(4);
		user.overrideJoystickPosition(-5);
		Assert.assertEquals(0, controller.getReferenceSpeed());
	}

	@Test
	public void SetJoystickPositionByUser(){
		UserChangeRefSpeed userChangeRefSpeed = new UserChangeRefSpeed(user,controller);
		userChangeRefSpeed.setNewSpeedByChangingTheJoyStickPosition(3);

		Assert.assertEquals(3,controller.getReferenceSpeed());
	}
	
}
