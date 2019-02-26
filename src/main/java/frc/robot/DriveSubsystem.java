/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.hal.sim.mockdata.AnalogInDataJNI;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.CloseHatchSolenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

  private static DriveSubsystem instance = null;

  private WPI_TalonSRX leftFrontTalon = new WPI_TalonSRX(RobotMap.LEFT_FRONT_MOTOR);
  private WPI_TalonSRX rightFrontTalon = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_MOTOR);
  private WPI_TalonSRX leftRearTalon = new WPI_TalonSRX(RobotMap.LEFT_REAR_MOTOR);
  private WPI_TalonSRX rightRearTalon = new WPI_TalonSRX(RobotMap.RIGHT_REAR_MOTOR);

  private WPI_TalonSRX windowMotor = new WPI_TalonSRX(RobotMap.WINDOW_MOTOR);

  private WPI_TalonSRX betaBotActuator = new WPI_TalonSRX(2);
  // private WPI_VictorSPX testMotor = new WPI_VictorSPX(0);

  ControllerSubsytem control = new ControllerSubsytem();

  // AnalogInput ai;
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

  public static DriveSubsystem getInstance() {
		if(instance == null) {
			instance = new DriveSubsystem();
		}
		return instance;
	}
  
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    control.setController(RobotMap.JOYSTICK_DRIVE_ONE);
    // this.ai = new AnalogInput(0);
    difDrive.setSafetyEnabled(false);
  }

  public void websocketDrive(double turn, double throttle) {
    difDrive.arcadeDrive(throttle, turn);
  }

  public void testLinearActuator() {
    betaBotActuator.set(0.7);
  }

  public void stopLinearActuator() {
    betaBotActuator.set(0);
  }

  public void linearActuatorBackwords() {
    betaBotActuator.set(-0.7);
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

  public void joystickDrive() {
    double X = OI.controller.getX();
		double Y = OI.controller.getY();
		double rotation = OI.controller.getZ();
		difDrive.arcadeDrive(Y,rotation);
  }
 
  public void joystickDriveHalfSpeed() {
    double X = OI.controller.getX() / 2;
		double Y = OI.controller.getY() / 2;
		double rotation = OI.controller.getZ() / 2;
		difDrive.arcadeDrive(Y,rotation);
  }

  public void alignAndPlaceHatch() {
    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double hasTargets = tv.getDouble(0.0);
    if(hasTargets == 1.0) {
      if(x <= 2 && x >= -2) {
        // in alignment range
        // TODO: Adjust this to right Y values / area values
        if(y >= 11) {
          difDrive.arcadeDrive(0, 0);
          // its in position! place hatch
          new CloseHatchSolenoid();
        } else {
          // approaching hatch
          difDrive.arcadeDrive(0.5, 0);
          
        }
      } else {
        
        if (x > 1) {
          // Turn left to align
          difDrive.arcadeDrive(0.2, 0.5);
          
        } else {
          // Turn right to align
          difDrive.arcadeDrive(0.2, -0.5);
        }
        

      }
      

    } else {
      return;
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

  public void windowMotorForward() {
    windowMotor.set(100);
  }

  public void stopWindowMotor() {
    windowMotor.stopMotor();
  }

  public void windowMotorBack() {
    windowMotor.set(-100);
  }


  public void stop() {
  
	}


}
