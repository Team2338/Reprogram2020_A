/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.gif.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.subsystems.Indexer;
import team.gif.robot.subsystems.Shooter;
import team.gif.robot.subsystems.Collector;
import team.gif.robot.Globals;

/**
 * An example command that uses an example subsystem.
 */
public class IndexerPushShooterContinuous extends CommandBase {
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      Globals.isIndexerEnabled = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Shooter.getInstance().getShooterVelocity() >= 3800) {
      Collector.getInstance().setSpeed(0.4);
      Indexer.getInstance().setSpeedTwo(0.4);
      Indexer.getInstance().setSpeedThree(0.4);
      Indexer.getInstance().setSpeedFour(0.5);
      Indexer.getInstance().setSpeedFive(0.6);
    }
    else{
      Collector.getInstance().setSpeed(0);
      Indexer.getInstance().setSpeedFive(0);
      Indexer.getInstance().setSpeedFour(0);
      Indexer.getInstance().setSpeedThree(0);
      Indexer.getInstance().setSpeedTwo(0);
      System.out.println(Shooter.getInstance().getShooterVelocity());
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
    Indexer.getInstance().setSpeedFour(0);
    Indexer.getInstance().setSpeedThree(0);
    Indexer.getInstance().setSpeedTwo(0);
    Collector.getInstance().setSpeed(0);
    Globals.isIndexerEnabled = true;
  }
}
