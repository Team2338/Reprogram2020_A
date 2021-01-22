package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.subsystems.Collector;
import team.gif.robot.subsystems.Indexer;

public class CollectReverse extends CommandBase {
    //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    //private final Collector m_collector;

    /**
     * Creates a new ExampleCommand.
     *
     //* @param subsystem The subsystem used by this command.
     */
    public CollectReverse() {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(Collector.getInstance());
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        new CollectorUp();
        Collector.getInstance().setSpeed(-0.5);
        Indexer.getInstance().setSpeedTwo(-0.5);
        Indexer.getInstance().setSpeedThree(-0.5);
        Indexer.getInstance().setSpeedFour(-0.5);
        Indexer.getInstance().setSpeedFive(-0.5);
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
        Indexer.getInstance().setSpeedFour(0);
        Indexer.getInstance().setSpeedFive(0);
    }
}
