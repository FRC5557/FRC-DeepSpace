/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_VictorSPX leftFrontVictor = new WPI_VictorSPX(RobotMap.LEFT_REAR_MOTOR);
  private WPI_VictorSPX rightFrontVictor = new WPI_VictorSPX(RobotMap.RIGHT_FRONT_MOTOR);
  private WPI_VictorSPX leftRearVictor = new WPI_VictorSPX(RobotMap.LEFT_REAR_MOTOR);
  private WPI_VictorSPX rightRearVictor = new WPI_VictorSPX(RobotMap.RIGHT_REAR_MOTOR);

  private WPI_TalonSRX leftFrontTalon = new WPI_TalonSRX(RobotMap.LEFT_REAR_MOTOR);
  private WPI_TalonSRX rightFrontTalon = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
  private WPI_TalonSRX leftRearTalon = new WPI_TalonSRX(RobotMap.LEFT_REAR_MOTOR);
  private WPI_TalonSRX rightRearTalon = new WPI_TalonSRX(RobotMap.RIGHT_REAR_MOTOR);

  private WPI_VictorSPX testMotor = new WPI_VictorSPX(0);

  ControllerSubsytem control = new ControllerSubsytem();

  // SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftFrontVictor, leftRearVictor);
	// SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightFrontVictor, rightRearVictor);
  // DifferentialDrive difDrive = new DifferentialDrive(leftGroup, rightGroup);
  
  SpeedControllerGroup leftGroup = new SpeedControllerGroup(leftFrontTalon, leftRearTalon);
	SpeedControllerGroup rightGroup = new SpeedControllerGroup(rightFrontTalon, rightRearTalon);
	DifferentialDrive difDrive = new DifferentialDrive(leftGroup, rightGroup);


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    control.setController(RobotMap.JOYSTICK_DRIVE_ONE);
  }


	public void drive() {
		double turn = 0;
		double throttle = 0;
		//Controller Drive
		turn = OI.controller.getX() > 0 ? OI.controller.getX()*1 : OI.controller.getX()*1;
		throttle = control.getTrigerThrottle(OI.controller.getTwist(), OI.controller.getThrottle());
		difDrive.arcadeDrive(throttle,turn);
	}

  public void setMotorsCoast() {
    // leftFrontVictor.setNeutralMode(NeutralMode.Coast);
    // rightFrontVictor.setNeutralMode(NeutralMode.Coast);
    // leftRearVictor.setNeutralMode(NeutralMode.Coast);
    // rightRearVictor.setNeutralMode(NeutralMode.Coast);


    leftFrontTalon.setNeutralMode(NeutralMode.Coast);
    rightFrontTalon.setNeutralMode(NeutralMode.Coast);
    leftRearTalon.setNeutralMode(NeutralMode.Coast);
    rightRearTalon.setNeutralMode(NeutralMode.Coast);
    
  }

  public void computerDrive(double speed, double rotate) {
		difDrive.arcadeDrive(speed, rotate);
  }
  
  public void testMotors() {
    testMotor.set(control.getTrigerThrottle(OI.controller.getTwist(), OI.controller.getThrottle()));
  }

    public void stop() {
    	computerDrive(0,0);
		
	}


}
