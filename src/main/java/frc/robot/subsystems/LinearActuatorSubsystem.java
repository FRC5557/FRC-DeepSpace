package frc.robot.subsystems;

import frc.robot.RobotMap;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LinearActuatorSubsystem extends Subsystem {
	
	private static LinearActuatorSubsystem instance = null;

	
	private ControllerSubsystem control = ControllerSubsystem.getInstance();
	
	// private WPI_TalonSRX linearActuator = new WPI_TalonSRX(RobotMap.LINEAR_ACTUATOR);
	private WPI_TalonSRX linearActuator = new WPI_TalonSRX(RobotMap.LINEAR_ACTUATOR);

	public static double motorSpeed = 1;


	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
//		setDefaultCommand(new ManualDriveCommand());
		control.setController(0);
	}
	
	public void moveMotor(double speed) {
		linearActuator.set(speed);
		
	}
	
	public static LinearActuatorSubsystem getInstance() {
		if(instance == null) {
			instance = new LinearActuatorSubsystem();
		}
		return instance;
	}

}
