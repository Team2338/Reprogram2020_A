package team.gif.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.RobotMap;

public class Collector extends SubsystemBase {
    private static Collector instance = null;


    public static Collector getInstance() {
        if (instance == null) {
            instance = new Collector();
        }
        return instance;
    }
    //defines motor object
    private static final VictorSPX collectorMotor= new VictorSPX(RobotMap.INTAKE);
    private static final Solenoid solenoid0 = new Solenoid(RobotMap.INTAKE_SOLENOID_ZERO);
    private static final Solenoid solenoid1 = new Solenoid(RobotMap.INTAKE_SOLENOID_ONE);
    private static final Solenoid solenoid2 = new Solenoid(RobotMap.INTAKE_SOLENOID_TWO);
    //Collector constructor
    private Collector() {
        super();
        collectorMotor.setInverted(true);

    }
    // sets the speed of the motor using setSpeed Method
    public void setSpeed (double speed) {
        collectorMotor.set(ControlMode.PercentOutput, speed);
        }

    public void setSolenoids(boolean zero, boolean one, boolean two) {
        solenoid0.set(zero);
        solenoid1.set(one);
        solenoid2.set(two);
    }
    /*
     * Data Table for Solenoid States
     *      Down    Mid     Up
     * 0    on      off      off
     * 1    on      on       off
     * 2    off     off      on
     */
}
