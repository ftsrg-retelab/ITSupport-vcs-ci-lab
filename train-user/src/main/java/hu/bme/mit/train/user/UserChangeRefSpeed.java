package hu.bme.mit.train.user;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;


public class UserChangeRefSpeed {
    private TrainController controller;
    private TrainUser trainUser;

    public UserChangeRefSpeed(TrainUser trainUser, TrainController trainController){
        this.trainUser = trainUser;
        this.controller = trainController;
    }

    public void setNewSpeedByChangingTheJoyStickPosition(int joyStickPosition){
        trainUser.overrideJoystickPosition(joyStickPosition);
        controller.followSpeed();
    }

}
