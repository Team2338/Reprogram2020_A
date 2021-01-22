package team.gif.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.RobotMap;
import edu.wpi.first.wpilibj.controller.PIDController;


public class Shooter extends SubsystemBase {

    private static Shooter instance = null;

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    private static final CANSparkMax flywheelShooter = new CANSparkMax(RobotMap.FLYWHEEL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private static final CANPIDController flywheelMotorPIDController = flywheelShooter.getPIDController();

    private Shooter() {
        //need to adjust to remain safe
        flywheelShooter.restoreFactoryDefaults();
        flywheelShooter.enableVoltageCompensation(11);

        flywheelMotorPIDController.setP(0.0003);
        flywheelMotorPIDController.setFF(0.000175);
        flywheelMotorPIDController.setOutputRange(0,1);
    }

    public void setRPM(double velocity) {
        flywheelMotorPIDController.setReference(velocity, ControlType.kVelocity);
    }
}
