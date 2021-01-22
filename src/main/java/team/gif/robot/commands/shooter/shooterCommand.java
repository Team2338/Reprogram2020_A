/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.gif.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.Robot;
import team.gif.robot.subsystems.ExampleSubsystem;
import team.gif.robot.subsystems.Shooter;

/**
 * An example command that uses an example subsystem.
 */
public class shooterCommand extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})


    /**
     * Creates a new ExampleCommand.
     *
     * @param subsystem The subsystem used by this command.
     */
    public shooterCommand() {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(Shooter.getInstance());
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Shooter.getInstance().setRPM(4000);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Shooter.getInstance().setRPM(0);
    }
}