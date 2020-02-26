/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.unit;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

/**
 * Add your docs here.
 */
public class Pose2dEx extends Pose2d {

    public Pose2dEx(double x, double y, double degrees) {
        super(x, y, new Rotation2d(degrees));
    }

    public Pose2dEx(double x, double y) {
        this(x, y, 0);
    }

    public double getX() {
        return this.getTranslation().getX();
    }

    public double getY() {
        return this.getTranslation().getY();
    }

}
