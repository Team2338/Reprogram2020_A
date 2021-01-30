package team.gif.robot.subsystems;

import com.revrobotics.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import team.gif.robot.RobotMap;
import edu.wpi.first.wpilibj.controller.PIDController;


public class Shooter extends SubsystemBase {
    double velocityThreshold = 50;
    double exactVelocity = 4000;

    private static Shooter instance = null;

    public static Shooter getInstance() {
        if (instance == null) {
            instance = new Shooter();
        }
        return instance;
    }

    private static final CANSparkMax flywheelShooter = new CANSparkMax(RobotMap.FLYWHEEL, CANSparkMaxLowLevel.MotorType.kBrushless);
    private static final CANPIDController flywheelMotorPIDController = flywheelShooter.getPIDController();
    private static final CANEncoder flywheelShooterEncoder = flywheelShooter.getEncoder();

    int stallMaxAmps = 40;

    private Shooter() {
        flywheelShooter.restoreFactoryDefaults();
        flywheelShooter.enableVoltageCompensation(12);
        flywheelShooter.setInverted(false);
        flywheelShooter.setIdleMode(CANSparkMax.IdleMode.kCoast);

        flywheelMotorPIDController.setP(0.0003);
        flywheelMotorPIDController.setFF(0.000175);
        flywheelMotorPIDController.setOutputRange(0,1);

        flywheelShooter.setSmartCurrentLimit(stallMaxAmps, stallMaxAmps);
    }
    public void setVoltage(double voltage) {
        flywheelShooter.setVoltage(voltage);
    }

    public void setRPM(double velocity) {
        flywheelMotorPIDController.setReference(velocity, ControlType.kVelocity);
    }

    public double getShooterVelocity() {
        return flywheelShooterEncoder.getVelocity();
    }
}
