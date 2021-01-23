package team.gif.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.RobotMap;

public class Indexer extends SubsystemBase {
    private static Indexer instance = null;

    public static Indexer getInstance() {
        if (instance == null) {
            instance = new Indexer();
        }
        return instance;
    }
    private static final VictorSPX motorStage2  = new VictorSPX(RobotMap.STAGE_TWO);
    private static final VictorSPX motorStage3 = new VictorSPX(RobotMap.STAGE_THREE);
    private static final VictorSPX motorStage4  = new VictorSPX(RobotMap.STAGE_FOUR);
    private static final VictorSPX motorStage5  = new VictorSPX(RobotMap.STAGE_FIVE);

    private static final DigitalInput sensorStage1 = new DigitalInput(RobotMap.SENSOR_ONE);
    private static final DigitalInput sensorStage2 = new DigitalInput(RobotMap.SENSOR_TWO);
    private static final DigitalInput sensorStage3 = new DigitalInput(RobotMap.SENSOR_THREE);
    private static final DigitalInput sensorStage4 = new DigitalInput(RobotMap.SENSOR_FOUR);
    private static final DigitalInput sensorStage5 = new DigitalInput(RobotMap.SENSOR_FIVE);

    public boolean[] getState() {
        boolean[] sensorStates = {false, sensorStage1.get(), sensorStage2.get(), sensorStage3.get(), sensorStage4.get(), sensorStage5.get()};
        return sensorStates;
    }

    public void setSpeedTwo(double speed) {
    motorStage2.set(ControlMode.PercentOutput, speed);
    }
    public void setSpeedThree(double speed) {
        motorStage3.set(ControlMode.PercentOutput, speed);
    }
    public void setSpeedFour(double speed) {
        motorStage4.set(ControlMode.PercentOutput, speed);
    }
    public void setSpeedFive(double speed) {
        motorStage5.set(ControlMode.PercentOutput, speed);
    }

    private Indexer() {
        super();
        motorStage4.setInverted(true);
        motorStage4.
    }
}
