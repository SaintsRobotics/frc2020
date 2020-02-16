/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.mocks;

import frc.robot.common.ILogger;
import frc.robot.common.IShooterSubsystem;
import frc.robot.common.TraceableMockSubsystem;

/**
 * Add your docs here.
 */
public class MockShooter extends TraceableMockSubsystem implements IShooterSubsystem {

    private boolean _isReady = false;

    public MockShooter(ILogger logger) {
        super(logger);
        // TODO Auto-generated constructor stub
    }
    /*
    * @param targetVelocity the rpm of shooter
    */
    @Override
    public void setSpeed(double targetVelocity) {
        // TODO Auto-generated method stub
        _isReady = true;
    }

    @Override
    public boolean isReady() {
        // TODO Auto-generated method stub
        return _isReady;
    }

    @Override
    public void feederForward() {
        
    }
    
    @Override
    public void disableFeeding() {
        // TODO Auto-generated method stub

    }
    @Override
    public void shootBalls(int balls){
        // TODO Auto-generated method stub
    }
    @Override
    public void stopShooter(){
       // TODO Auto-generated method stub

    }


    @Override
    public void feederBackward() {
        // TODO Auto-generated method stub

    }




}