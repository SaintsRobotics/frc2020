/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.common;

import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * Add your docs here.
 */
public class JoystickController {
    private static double _leftX;
    private static double _leftY;
    private static double _rightX;
    private static double _rightY;
    private static double _rightAxis;
    private static double _leftAxis;

    public double getX(Hand hand) {
        return hand == Hand.kLeft ? _leftX : _rightX;
    }

    public double getY(Hand hand) {
        return hand == Hand.kLeft ? _leftY : _rightY;
    }

    public double getAxis(Hand hand) {
        return hand == Hand.kLeft ? _leftAxis : _rightAxis;
    }

    public double getX() {
        return this.getX(Hand.kRight);
    }

    public double getY() {
        return this.getY(Hand.kRight);
    }

    public static void update(Hand hand, double x, double y, double axis) {
        if (hand == Hand.kLeft) {
            _leftX = x;
            _leftY = y;
            _leftAxis = axis;
        } else {
            _rightX = x;
            _rightY = y;
            _rightAxis = axis;
        }
    }
}
