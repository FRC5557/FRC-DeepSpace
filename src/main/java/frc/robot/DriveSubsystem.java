/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
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
  // private WPI_VictorSPX leftFrontVictor = new WPI_VictorSPX(RobotMap.LEFT_REAR_MOTOR);
  // private WPI_VictorSPX rightFrontVictor = new WPI_VictorSPX(RobotMap.RIGHT_FRONT_MOTOR);
  // private WPI_VictorSPX leftRearVictor = new WPI_VictorSPX(RobotMap.LEFT_REAR_MOTOR);
  // private WPI_VictorSPX rightRearVictor = new WPI_VictorSPX(RobotMap.RIGHT_REAR_MOTOR);

  private WPI_TalonSRX leftFrontTalon = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
  private WPI_TalonSRX rightFrontTalon = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
  private WPI_TalonSRX leftRearTalon = new WPI_TalonSRX(RobotMap.LEFT_REAR_MOTOR);
  private WPI_TalonSRX rightRearTalon = new WPI_TalonSRX(RobotMap.RIGHT_REAR_MOTOR);

  // private WPI_VictorSPX testMotor = new WPI_VictorSPX(0);

  ControllerSubsytem control = new ControllerSubsytem();

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");


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

    difDrive.setSafetyEnabled(false);
  }

  public void websocketDrive(double turn, double throttle) {
    difDrive.arcadeDrive(throttle, turn);
  }


	public void drive() {
    OI.controller.setRumble(RumbleType.kRightRumble, 1);
		double turn = 0;
		double throttle = 0;
		//Controller Drive
		turn = OI.controller.getX() > 0 ? OI.controller.getX()*1 : OI.controller.getX()*1;
		throttle = control.getTrigerThrottle(OI.controller.getTwist(), OI.controller.getThrottle());
		difDrive.arcadeDrive(throttle,turn);
  }
  
  public void followTarget() {
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double hasTargets = tv.getDouble(0.0);
    if(hasTargets == 1.0) {
      
      // System.out.println("X: " + x);
      // go left / right until x <= 1 or x >= -1
      // if x > 1 turn left else turn right slowly
      // then go forward while y > 1 (to be safe)
      if(x <= 2 && x >= -2) {
        System.out.println("LESS THAN 2 AND GREATER THAN 2");
        if(y >= 11) {
          difDrive.arcadeDrive(0, 0);
        } else {
          difDrive.arcadeDrive(0.5, 0);
        }
      } else {
        
        if (x > 1) {
          // turn left
          System.out.println("LEFT");

          difDrive.arcadeDrive(0.2, 0.5);
          
        } else {
          // turn right
          System.out.println("RIGHT");

          difDrive.arcadeDrive(0.2, -0.5);
        }
      }
      

    } else {
      double turn = 0.5;
      double throttle = 0.3;
      difDrive.arcadeDrive(throttle,turn);
    }
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
    // testMotor.set(control.getTrigerThrottle(OI.controller.getTwist(), OI.controller.getThrottle()));
  }

    public void stop() {
    	// computerDrive(0,0);
		
	}


}
