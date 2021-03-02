/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package team.gif.robot.commands.limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import team.gif.robot.Robot;
import team.gif.robot.commands.shooter.RapidFire;
import team.gif.robot.subsystems.Shooter;
import team.gif.robot.commands.shooter.RevShooterFlywheel;
import team.gif.robot.subsystems.Drivetrain;
import static team.gif.robot.Robot.limelight;

/**
 * An example command that uses an example subsystem.
 */
public class AutoAim extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  double ty;
  boolean RobotIsStill = false;
  boolean targetLocked = false;
  double speedThreshold = 0.2;
  double backSpeedThreshold = -speedThreshold;
  double Distance;
  double mountAngle = 30;
  double Convert = Math.toRadians(mountAngle);
  double convertTy = Math.toRadians(ty);
  double tanTy = Math.tan(convertTy);
  double tanMountAngle = Math.tan(Convert);
  double denominator = 1 - (tanMountAngle * tanTy);
  double numerator = tanTy + tanMountAngle;
  double tanOverflowPrevent = numerator / denominator;

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.setLEDMode(3);
    Drivetrain.getInstance().setSpeed(0,0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!RobotIsStill){
      DifferentialDriveWheelSpeeds currWheelSpeeds = Drivetrain.getInstance().getWheelSpeeds();
      if(Math.abs(currWheelSpeeds.leftMetersPerSecond) < speedThreshold && Math.abs(currWheelSpeeds.leftMetersPerSecond) > backSpeedThreshold){
        RobotIsStill = true;
      }
    }
    if(RobotIsStill) {
      targetLocked = limelight.hasTarget();
      if(targetLocked) {
        ty = limelight.getYOffset();
        //Numerator assumes that the camera is mounted 1 foot from the ground; Calculated by height from carpet to tall target - carpet to camera
        Distance = 86.25 / tanOverflowPrevent;
        System.out.println(Distance);
        if (limelight.getXOffset() > -1.0 && limelight.getXOffset() < 1.0) {
          new RevShooterFlywheel();
          new RapidFire();
        }

      }
      System.out.println(Distance);
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
    RobotIsStill = false;
    Shooter.getInstance().setVoltage(0);
    limelight.setLEDMode(1);
  }
}
