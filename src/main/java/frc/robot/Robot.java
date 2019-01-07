
package frc.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.drive.RobotDriveBase.MotorType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import res.RightAutoLineCubeMotionProfile;
import frc.lib.RightAutoLineCubeMotionProfile;
// replace frc.robot -> frc.robot
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import frc.robot.commands.autogroups.ExampleAutonomous;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DriveSubSystem;
import frc.robot.subsystems.MotionProfileSubsystem;
import frc.robot.subsystems.SensorSubsystem;

// import frc.li.ctre.phoenix.motion.SetValueMotionProfile;
// import edu.ctre

import com.ctre.phoenix.motion.*;
import com.ctre.phoenix.wpiapi_java.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.*;
// import com.ct
// import com.ctre
// import com.ct
// import pho
import com.ctre.phoenix.motorcontrol.NeutralMode;
// import edu.wpi.first.wpilibj


import frc.lib.ADIS16448_IMU;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends TimedRobot {
	
	private static final DriveSubSystem drive = DriveSubSystem.getInstance();
	private static final SensorSubsystem sensors = SensorSubsystem.getInstance();
	private static final ArmSubsystem arm = ArmSubsystem.getInstance();
	
	// private WPI_T
	public static final MotionProfileSubsystem mp = new MotionProfileSubsystem(new RightAutoLineCubeMotionProfile());
	
	public static OI oi;
	public static Preferences prefs = Preferences.getInstance();

	public static final ADIS16448_IMU imu = new ADIS16448_IMU();

	Command autonomousCommand;
	
	SendableChooser<Integer> autonObjectiveChooser = new SendableChooser<Integer>();
	
	SendableChooser<Integer> autonPositionChooser = new SendableChooser<Integer>();
	
	int aPerFlag = 0;
	
	int startingPosition = 0;
	  


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();

		autonPositionChooser.addDefault("Right", new Integer(0));
		autonPositionChooser.addObject("Middle", new Integer(1));
		autonPositionChooser.addObject("Left", new Integer(2));
		SmartDashboard.putData(autonPositionChooser);
		
		autonObjectiveChooser.addDefault("Autoline", new Integer(0));
		autonObjectiveChooser.addObject("Switch", new Integer(1));
		autonObjectiveChooser.addObject("Scale", new Integer(2));
		SmartDashboard.putData(autonObjectiveChooser);
		
		sensors.resetEncoders();
		 new Thread(() -> {
             UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
             camera.setResolution(352, 240);
             camera.setFPS(30);
             camera.setExposureAuto();
             
             CvSink cvSink = CameraServer.getInstance().getVideo();
             CvSource outputStream = CameraServer.getInstance().putVideo("Main Camera", 352, 288);
             
             Mat source = new Mat();
             Mat output = new Mat();
;             
             while(!Thread.interrupted()) {
                 cvSink.grabFrame(source);
                 Imgproc.cvtColor(source, output, Imgproc.COLOR_RGB2GRAY);
                 outputStream.setFPS(30);
                 outputStream.putFrame(output);
             }
         }).start();
		
	}
	
	
	//Put println() here
	@Override
	public void robotPeriodic() {
		super.robotPeriodic();
		SmartDashboard.putNumber("Ultra (mm): ", sensors.getUltraWithVoltage());
		SmartDashboard.putNumber("Encoder Right (cm): ", sensors.getDis(MotorType.kFrontRight));
		SmartDashboard.putNumber("Encoder Left (cm): ", sensors.getDis(MotorType.kRearLeft));
		// SmartDashboard.putNumber("Left encoder ticks: ", drive.getTalonSensorC(MotorType.kRearLeft).getQuadraturePosition());
		// SmartDashboard.putNumber("Right encoder ticks: ", drive.getTalonSensorC(MotorType.kFrontRight).getQuadraturePosition());
		prefs.getDouble("ArmUpVoltage", 0);
		
		
	    SmartDashboard.putNumber("Gyro-X", imu.getAngleX());
	    
	    SmartDashboard.putNumber("Temperature: ", imu.getTemperature()); 
		
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		mp.clearMPState();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		//TODO: ADD INPUT / OTHER WAY TO DETERMINE OR ENTER STARTING POSITION ON startingPosition
		
		imu.reset();
		//autonomousCommand = autonObjectiveChooser.getSelected();
		//drive.autonTalonInit(NeutralMode.Brake)
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		int startPos = (int) SmartDashboard.getNumber("Position", 0);
		if(startPos == 0){
			if(gameData != null){
				if(autonObjectiveChooser.getSelected().intValue() == 0){ //autoline
					if(gameData.charAt(0) == 'R'){
						System.out.println("Caught FMS data Right");
						autonomousCommand = new ExampleAutonomous();
					} else if(gameData.charAt(0) == 'L'){
						autonomousCommand = new ExampleAutonomous();
					}
				}else if(autonObjectiveChooser.getSelected().intValue() == 1){ //switch
					if(gameData.charAt(0) == 'R'){
						System.out.println("Caught FMS data Right");
						autonomousCommand = new ExampleAutonomous();
					} else if(gameData.charAt(0) == 'L'){
						autonomousCommand = new ExampleAutonomous();
					}
				}else if(autonObjectiveChooser.getSelected().intValue() == 2){ //scale
					if(gameData.charAt(0) == 'R'){
						System.out.println("Caught FMS data Right");
						autonomousCommand = new ExampleAutonomous();
					} else if(gameData.charAt(0) == 'L'){
						autonomousCommand = new ExampleAutonomous();
					}
				}
			}
		}else if(startPos == 1){
			if(gameData != null){
				if(autonObjectiveChooser.getSelected().intValue() == 0){ //autoline
					if(gameData.charAt(0) == 'R'){
						System.out.println("Caught FMS data Right");
						autonomousCommand = new ExampleAutonomous();
					} else if(gameData.charAt(0) == 'L'){
						autonomousCommand = new ExampleAutonomous();
					}
				}else if(autonObjectiveChooser.getSelected().intValue() == 1){ //switch
					if(gameData.charAt(0) == 'R'){
						System.out.println("Caught FMS data Right");
						autonomousCommand = new ExampleAutonomous();
					} else if(gameData.charAt(0) == 'L'){
						autonomousCommand = new ExampleAutonomous();
					}
				}else if(autonObjectiveChooser.getSelected().intValue() == 2){ //scale
					if(gameData.charAt(0) == 'R'){
						System.out.println("Caught FMS data Right");
						autonomousCommand = new ExampleAutonomous();
					} else if(gameData.charAt(0) == 'L'){
						autonomousCommand = new ExampleAutonomous();
					}
				}
			}
		}else if(startPos == 2){
				if(gameData != null){
					if(autonObjectiveChooser.getSelected().intValue() == 0){ //autoline
						if(gameData.charAt(0) == 'R'){
							System.out.println("Caught FMS data Right");
							autonomousCommand = new ExampleAutonomous();
						} else if(gameData.charAt(0) == 'L'){
							autonomousCommand = new ExampleAutonomous();
						}
					}else if(autonObjectiveChooser.getSelected().intValue() == 1){ //switch
						if(gameData.charAt(0) == 'R'){
							System.out.println("Caught FMS data Right");
							autonomousCommand = new ExampleAutonomous();
						} else if(gameData.charAt(0) == 'L'){
							autonomousCommand = new ExampleAutonomous();
						}
					}else if(autonObjectiveChooser.getSelected().intValue() == 2){ //scale
						if(gameData.charAt(0) == 'R'){
							System.out.println("Caught FMS data Right");
							autonomousCommand = new ExampleAutonomous();
						} else if(gameData.charAt(0) == 'L'){
							autonomousCommand = new ExampleAutonomous();
						}
					}
				}
		}
		
		if(autonomousCommand != null){
				autonomousCommand.start();
		}
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SetValueMotionProfile setOutput = mp.getSetValue();

		mp._talon.set(ControlMode.MotionProfile, setOutput.value);
		mp._talon2.set(ControlMode.MotionProfile, setOutput.value);
		mp.control();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		drive.autonTalonInit(NeutralMode.Coast);
		arm.clearIntakeBreakMode(NeutralMode.Coast);
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during teleop
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		arm.raise(OI.driveStickZero.getZ());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
