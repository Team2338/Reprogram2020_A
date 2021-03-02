
package team.gif.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import team.gif.lib.autoMode;
import team.gif.robot.commands.autos.*;
import team.gif.robot.commands.drivetrain.Drive;
import team.gif.robot.commands.indexer.IndexIn;
import team.gif.robot.commands.indexer.ToggleIndexer;
import team.gif.robot.subsystems.Drivetrain;
import team.gif.robot.subsystems.Indexer;
import team.gif.robot.subsystems.Shooter;
import team.gif.robot.subsystems.drivers.Limelight;

import static team.gif.robot.Globals.indexerEnabled;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  private Command m_autonomousCommand = null;

  private Command driveCommand = null;

  private Command indexCommand = null;

  private SendableChooser<autoMode> autoModeChooser = new SendableChooser<>();

  private autoMode chosenAuto;

  private Timer _elapsedTime = new Timer();

  public static Limelight limelight;
  private final Compressor compressor = new Compressor();
  public static ShuffleboardTab autoTab = Shuffleboard.getTab("PreMatch");
  private NetworkTableEntry allianceEntry = autoTab.add("Alliance","Startup")
                                                    .withPosition(3,0)
                                                    .withSize(1,1)
                                                    .getEntry();

  public static OI oi;
  private Drivetrain drivetrain = null;


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    System.out.println("robot init");
    tabsetup();
    // autonomous chooser on the dashboard.

    driveCommand = new Drive(Drivetrain.getInstance());
    drivetrain = Drivetrain.getInstance();
    indexCommand = new IndexIn();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    chosenAuto = autoModeChooser.getSelected();

    //System.out.println("robot periodic");

    SmartDashboard.putBoolean("One",  Indexer.getInstance(). getState()[1]);
    SmartDashboard.putBoolean("Two",  Indexer.getInstance(). getState()[2]);
    SmartDashboard.putBoolean("Three",Indexer.getInstance().getState()[3]);
    SmartDashboard.putBoolean("Four", Indexer.getInstance().getState()[4]);
    SmartDashboard.putBoolean("Five", Indexer.getInstance().getState()[5]);

    SmartDashboard.putBoolean("Indexer enabled", Globals.indexerEnabled);

//    SmartDashboard.putNumber("tx",limelight.getXOffset());
//    SmartDashboard.putNumber("ty",limelight.getYOffset());


//    SmartDashboard.putBoolean("hastarget",limelight.hasTarget());
    CommandScheduler.getInstance().run();



  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    System.out.println("autonomous init start");

    // used for delaying the start of autonomous
    _elapsedTime.reset();
    _elapsedTime.start();
    System.out.println("Auto: Timers Reset");

    drivetrain.resetEncoders();
    drivetrain.resetPose();
    System.out.println("Auto: Sensors Reset");

    updateauto();
    System.out.println("Auto: auto selection updated");
    compressor.stop();
    System.out.println("Auto: Compressor stopped");
    m_autonomousCommand.schedule();
    indexCommand.schedule();

    System.out.println("autonomous init end");

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    System.out.println("teleop init");

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    oi = new OI();
    compressor.start();
    driveCommand.schedule();
    indexCommand.schedule();
  }

  @Override
  public void teleopPeriodic() {

  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic(){
  }

  public void tabsetup(){

    autoTab = Shuffleboard.getTab("PreMatch");

    autoModeChooser.setDefaultOption("Mobility", autoMode.MOBILITY);

    autoTab.add("Auto Select",autoModeChooser)
            .withWidget(BuiltInWidgets.kComboBoxChooser)
            .withPosition(1,0)
            .withSize(2,1);

  }

  public void updateauto(){

    if(chosenAuto == autoMode.MOBILITY){
        m_autonomousCommand = new Mobility();
    } else if(chosenAuto ==null) {
        System.out.println("Autonomous selection is null. Robot will do nothing in auto :(");
    }
  }

}
