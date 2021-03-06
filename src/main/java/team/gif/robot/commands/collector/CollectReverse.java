package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.subsystems.Collector;
import team.gif.robot.subsystems.Indexer;

public class CollectReverse extends CommandBase {

    public CollectReverse() {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(Collector.getInstance());
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Collector.getInstance().setSpeed(-0.6);
        Indexer.getInstance().setSpeedTwo(-0.5);
        Indexer.getInstance().setSpeedThree(-0.4);
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
        Collector.getInstance().setSpeed(0);
        Indexer.getInstance().setSpeedTwo(0);
        Indexer.getInstance().setSpeedThree(0);
    }
}
