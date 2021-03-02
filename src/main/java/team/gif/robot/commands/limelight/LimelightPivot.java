/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.gif.robot.commands.limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.subsystems.Drivetrain;
import static team.gif.robot.Robot.limelight;

/**
 * An example command that uses an example subsystem.
 */
public class LimelightPivot extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    boolean targetLocked;
    double power = 3.5;
    boolean isOnTarget;

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        limelight.setLEDMode(3);
        targetLocked = false;
        isOnTarget = false;
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double tx;
        targetLocked = limelight.hasTarget();
        if (targetLocked == true) {
            tx = limelight.getXOffset();
            System.out.println(tx);
            if (tx <= 1 && tx >= -1) {
                Drivetrain.getInstance().setSpeed(0, 0);
                isOnTarget = true;
            } else {
                if (tx < 0) {
                    Drivetrain.getInstance().tankDriveVolts(-power, power);
                } else {
                    Drivetrain.getInstance().tankDriveVolts(power, -power);
                }
            }

        }

    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return isOnTarget;
    }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
}
