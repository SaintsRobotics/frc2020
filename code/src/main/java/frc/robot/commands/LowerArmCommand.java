/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.google.inject.Inject;

import frc.robot.common.*;
import frc.robot.subsystems.Intake;

/**
 * An example command that uses an example subsystem.
 */
public class LowerArmCommand extends TraceableCommand {
    //@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
    private final Intake m_subsystem;

    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    @Inject
    public LowerArmCommand(ILogger logger, Intake subsystem) {
        super(logger);
        m_subsystem = subsystem;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(subsystem);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_subsystem.lowerArm();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return m_subsystem.armIsAtBottom();
    }
}
