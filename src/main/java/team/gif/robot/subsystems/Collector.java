package team.gif.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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
    //Collector constructor
    private Collector() {
        super();
        collectorMotor.setInverted(true);

    }
    // sets the speed of the motor using setSpeed Method
    public void setSpeed (double speed) {
        collectorMotor.set(ControlMode.PercentOutput, speed);
        }

}
