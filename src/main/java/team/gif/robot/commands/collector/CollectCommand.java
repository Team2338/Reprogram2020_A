package team.gif.robot.commands.collector;

import edu.wpi.first.wpilibj2.command.CommandBase;
import team.gif.robot.subsystems.Collector;
import team.gif.robot.subsystems.Indexer;

/**
 * An example command that uses an example subsystem.
 */
public class CollectCommand extends CommandBase {
    //@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    //private final Collector m_collector;
    Indexer index = Indexer.getInstance();
    /**
     * Creates a new ExampleCommand.
     *
     //* @param subsystem The subsystem used by this command.
     */
    public CollectCommand() {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(Collector.getInstance());
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        if(index.getState()[1] == false){
            Collector.getInstance().setSpeed(.5);
        }
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return index.getState()[1];
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Collector.getInstance().setSpeed(0);
    }
}

