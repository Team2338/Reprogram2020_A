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
public class limelightCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  double tx;
  double txThreshold = 0.2;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.setLEDMode(3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tx = limelight.getXOffset();
    double constant = 5;
    double lPower = tx * constant;
    double rPower = -lPower;
    Drivetrain.getInstance().setSpeed(lPower, rPower);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (tx <= txThreshold && tx >= -txThreshold) {
      return true;
    }
    else{
      return false;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Drivetrain.getInstance().setSpeed(0, 0);
  }
}