/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.net.InetSocketAddress;
import java.net.URI;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.server.WebSocketServer;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.PneumaticSubsystem;


// import com.ctre

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private boolean teleopOn = false;

  // AnalogInput ai;

  PowerDistributionPanel pdp = new PowerDistributionPanel();

  DriveSubsystem drive =  DriveSubsystem.getInstance();

  

  OI oi = new OI();

  PneumaticSubsystem pneumaticsSubsystem = PneumaticSubsystem.getInstance();

 


  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  WebSocketClient mWs;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    
    // this.ai = new AnalogInput(0);
    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void teleopInit() {
    // drive.
    drive.setMotorsCoast();
    teleopOn = true;
    
    pneumaticsSubsystem.c.setClosedLoopControl(true);
    super.teleopInit();
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    pdp.clearStickyFaults();
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // System.out.println("Teleop Periodic!");

    // run this command for actual driving
     drive.drive();

    // run this comamand to drive with joystick
    // if(controller.getRawButtonPressed(11)) {
    //   if(RobotMap.HALF_SPEED) {
    //     RobotMap.HALF_SPEED = false;
    //   } else {
    //     RobotMap.HALF_SPEED = true;
    //   }
    // }

    // if(RobotMap.HALF_SPEED) {
    //   drive.joystickDriveHalfSpeed();
    // } else {
    //   drive.joystickDrive();
    // }
     
    
     
     if(OI.controller.getRawButtonPressed(6)) {
       
      pneumaticsSubsystem.solenoid.set(DoubleSolenoid.Value.kForward);
     } else if(OI.controller.getRawButtonPressed(5)) {
      pneumaticsSubsystem.solenoid.set(DoubleSolenoid.Value.kReverse);
      // solenoid.
      System.out.println(pneumaticsSubsystem.solenoid.get());
    }

    if(OI.controller.getRawButtonPressed(2)) {
     
      pneumaticsSubsystem.solenoid2.set(DoubleSolenoid.Value.kForward);
    } else if(OI.controller.getRawButtonPressed(3)){
      pneumaticsSubsystem.solenoid2.set(DoubleSolenoid.Value.kReverse);
    }

    if(OI.controller.getRawButtonPressed(1)) {
       // move window motor
      drive.testWindowMotor();
    } else if(OI.controller.getRawButtonPressed(4)){
      drive.windowMotorBack();
    } else {
       // stop window motor
      drive.stopWindowMotor();
    }

    // if(controller.getRawButtonPressed(13)) {
    //     drive.testLinearActuator();
    //     // System.out.println(this.ai.getVoltn(12)) {
    //     drive.linearActuatorBackwords();
    //   } else {age());
    //   } else if(controller.getRawButto
    //     drive.stopLinearActuator();
    //   }

   
    
    //  System.out.println(c.getCompressorCurrent());
     
     
    // this command is for testing limelight tracking
    //drive.followTarget();

    // controller.setRumble(RumbleType.kRightRumble, 1);
    // controller.setRumble(type, value);
    // below command is for that 1 test motor on beta bot
    // drive.testMotors();  
  }

  @Override
  public void disabledInit() {
    pneumaticsSubsystem.solenoid.set(DoubleSolenoid.Value.kOff);
    pneumaticsSubsystem.c.setClosedLoopControl(false);
    super.disabledInit();
  }



  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
