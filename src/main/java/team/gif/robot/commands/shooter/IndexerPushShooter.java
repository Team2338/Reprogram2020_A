/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.gif.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.subsystems.Shooter;
import team.gif.robot.subsystems.Indexer;

/**
 * An example command that uses an example subsystem.
 */
public class IndexerPushShooter extends CommandBase {
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Shooter.getInstance().getShooterVelocity() >= 3950 && Shooter.getInstance().getShooterVelocity() <= 4050 && Indexer.getInstance().getState()[5] == true) {
      Indexer.getInstance().setSpeedFive(0.5);
    }
    else{
      Indexer.getInstance().setSpeedFive(0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Indexer.getInstance().setSpeedFive(0);
  }
}
