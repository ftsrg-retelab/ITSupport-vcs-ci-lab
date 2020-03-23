package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    private TrainController mockController;
    private TrainUser user;
    private TrainSensorImpl sensor;

    @Before
    public void before() {
        mockController = mock(TrainController.class);
        user = mock(TrainUser.class);
        sensor = new TrainSensorImpl(mockController);
    }

    @Test
    public void TestChangeSpeedBelowZero() {
        //Act
        sensor.overrideSpeedLimit(-1);

        //Assert
        Assert.assertTrue(sensor.getAlarmState());
        verify(mockController, times(1)).setSpeedLimit(-1);
    }

    @Test
    public void TestChangeSpeedAt0() {
        //Act
        sensor.overrideSpeedLimit(0);

        //Assert
        Assert.assertFalse(sensor.getAlarmState());
        verify(mockController, times(1)).setSpeedLimit(0);
    }

    @Test
    public void TestChangeSpeedAt1() {
        //Act
        sensor.overrideSpeedLimit(1);

        //Assert
        Assert.assertFalse(sensor.getAlarmState());
        verify(mockController, times(1)).setSpeedLimit(1);
    }

    @Test
    public void TestChangeSpeedAbove500() {
        //Act
        sensor.overrideSpeedLimit(501);

        //Assert
        Assert.assertTrue(sensor.getAlarmState());
        verify(mockController, times(1)).setSpeedLimit(501);
    }

    @Test
    public void TestChangeSpeedAt500() {
        //Act
        sensor.overrideSpeedLimit(500);

        //Assert
        Assert.assertFalse(sensor.getAlarmState());

        verify(mockController, times(1)).setSpeedLimit(500);
    }

    @Test
    public void TestChangeSpeedBelow500() {
        //Act
        sensor.overrideSpeedLimit(499);

        //Assert
        Assert.assertFalse(sensor.getAlarmState());

        verify(mockController, times(1)).setSpeedLimit(499);
    }

    @Test
    public void TestSlowingDownTrainBigChange() {
        //Arrange
        when(mockController.getReferenceSpeed()).thenReturn(500);

        //Act
        sensor.overrideSpeedLimit(100);

        //Assert
        Assert.assertTrue(sensor.getAlarmState());
        verify(mockController, times(1)).setSpeedLimit(100);
    }
}
