package frc.robot.common;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Twist2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import frc.robot.RobotConfig;

public class Odometry extends SwerveDriveOdometry {
    private Pose2d m_poseMeters;
    private double m_prevTimeSeconds = -1;
    private SwerveDriveKinematics m_kinematics;
    private Rotation2d m_gyroOffset;
    private Rotation2d m_previousAngle;
    private RobotConfig m_config;

    public Odometry(SwerveDriveKinematics kinematics, Rotation2d gyroAngle, RobotConfig config) {
        super(kinematics, gyroAngle);
        this.m_config = config;
        // TODO Auto-generated constructor stub
    }

    public Odometry(SwerveDriveKinematics kinematics, Rotation2d gyroAngle, Pose2d initialPose, RobotConfig config) {
        super(kinematics, gyroAngle, initialPose);
        this.m_config = config;
    }

    /**
     * Updates the robot's position on the field using forward kinematics and
     * integration of the pose over time. This method takes in the current time as a
     * parameter to calculate period (difference between two timestamps). The period
     * is used to calculate the change in distance from a velocity. This also takes
     * in an angle parameter which is used instead of the angular rate that is
     * calculated from forward kinematics.
     *
     * @param currentTimeSeconds The current time in seconds.
     * @param gyroAngle          The angle reported by the gyroscope.
     * @param moduleStates       The current state of all swerve modules. Please
     *                           provide the states in the same order in which you
     *                           instantiated your SwerveDriveKinematics.
     * @return The new pose of the robot.
     */

    public Pose2d updateWithTime(double currentTimeSeconds, Rotation2d gyroAngle,
            SwerveModuleState leftFrontModuleState, SwerveModuleState rightFrontModuleState,
            SwerveModuleState leftBackModuleState, SwerveModuleState rightBackModuleState) {
        double period = m_prevTimeSeconds >= 0 ? currentTimeSeconds - m_prevTimeSeconds : 0.0;
        m_prevTimeSeconds = currentTimeSeconds;

        Rotation2d angle = gyroAngle.plus(m_gyroOffset);

        ChassisSpeeds chassisState = m_kinematics.toChassisSpeeds(
                new SwerveModuleState(leftFrontModuleState.speedMetersPerSecond / m_config.Drivetrain.gearRatio,
                        leftFrontModuleState.angle),
                new SwerveModuleState(rightFrontModuleState.speedMetersPerSecond / m_config.Drivetrain.gearRatio,
                        rightFrontModuleState.angle),
                new SwerveModuleState(leftBackModuleState.speedMetersPerSecond / m_config.Drivetrain.gearRatio,
                        leftBackModuleState.angle),
                new SwerveModuleState(rightBackModuleState.speedMetersPerSecond / m_config.Drivetrain.gearRatio,
                        rightBackModuleState.angle));
        Pose2d newPose = m_poseMeters.exp(new Twist2d(chassisState.vxMetersPerSecond * period,
                chassisState.vyMetersPerSecond * period, angle.minus(m_previousAngle).getRadians()));

        m_previousAngle = angle;
        m_poseMeters = new Pose2d(newPose.getTranslation(), angle);

        return m_poseMeters;
    }

}