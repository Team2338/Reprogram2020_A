package team.gif.robot.commands.autos;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.*;
import team.gif.lib.RobotTrajectory;
import team.gif.robot.commands.collector.CollectCommand;
import team.gif.robot.commands.collector.CollectorDown;
import team.gif.robot.commands.collector.CollectorUp;
import team.gif.robot.subsystems.Drivetrain;
import java.util.List;

public class threeBallTrenchAuto extends SequentialCommandGroup {

    public Command reverse () {
        Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
                List.of(
                        new Pose2d(Units.feetToMeters(0.0), 0, new Rotation2d(0)),
                        new Pose2d(Units.feetToMeters(-17.0), 0, new Rotation2d(0))
                ),
                RobotTrajectory.getInstance().configReverseSlow
        );
        // create the command using the trajectory
        RamseteCommand rc = RobotTrajectory.getInstance().createRamseteCommand(trajectory);
        // Run path following command, then stop at the end.
        return rc.andThen(() -> Drivetrain.getInstance().tankDriveVolts(0, 0));
    }


    public threeBallTrenchAuto() {
        System.out.println("Auto: threeBallTrenchAuto Selected");

        addCommands(
            new PrintCommand("Auto: threeBallTrenchAuto Started"),
            new CollectorDown(),
            new ParallelDeadlineGroup(
                reverse(),
                new CollectCommand()),

                //Shoot three balls.

                new PrintCommand("Auto: threeBallTrenchAuto Ended")
        );
    }
}