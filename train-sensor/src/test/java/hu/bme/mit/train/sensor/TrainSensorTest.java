package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;


public class TrainSensorTest {

	TrainController controller;
	TrainSensor sensor;
	TrainUser user;

    @Before
    public void before() {
        controller = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void overrideSpeedLimit_ValidSpeed() {
        sensor.overrideSpeedLimit(100);
        verify(user, times(0)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_NegativeSpeed() {
        sensor.overrideSpeedLimit(-10);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_TooFastSpeed() {
        sensor.overrideSpeedLimit(501);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void overrideSpeedLimit_LargeDifference() {
        sensor.overrideSpeedLimit(120);
        sensor.overrideSpeedLimit(30);
        verify(user, times(1)).setAlarmState(true);
    }
}
