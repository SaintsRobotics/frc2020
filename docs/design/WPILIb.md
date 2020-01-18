# Classes that are testable

ChassisSpeeds
SwerveDriveOdometry (SwerveDriveKinematics, Rotation2d, Pose2d)
SwerveDriveKinematics(Translation2d) - uses HAL
Rotation2d
Pose2d
SwerveDriveKinematics(Translation2d, ChassisSpeeds, SwerveModuleState) - uses HAL
GyroBase(SendableBuilder)
(interface)SendableBuilder
DifferentialDrive(RobotDriveBase) uses HAL

# Classes that use native or are not testable
HAL

# Classes needed to be Mocked
Gyro ie ADXRS450_Gyro use GyroBase or Gyro (interface)
SpeedController (interface- has JNI code to override!?) ie PWMVictorSPX - this seems to tell us the speed of the robot
Timer.getFPGATimestamp()
HAL?
Encoder

** Going to be hard to Mock the trajectory classes as it relies too much on JNI classes **