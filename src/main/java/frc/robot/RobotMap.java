package frc.robot;

public class RobotMap {
    	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	
/*	
 * 
	PS4 CONTROLLER BUTTONS
	1:	SQUARE
	
	2:	X
	
	3:	CIRCLE
	
	4:	TRIANGLE
	
	5:	L1
	
	6:	R1
	
	7:	L2
	
	8:	R2
	
*/	
	
	// Motor Controllers are mapped here
	
		//	DRIVE TRAIN

		// BETA BOT MAPS
	// public static final int LEFT_REAR_MOTOR = 4; //Encoder on this one, counts down when driving forward
	//  public static final int LEFT_FRONT_MOTOR = 6; 
	//  public static final int RIGHT_FRONT_MOTOR = 1;//Encoder on this one, counts up when driving forward
	//  public static final int RIGHT_REAR_MOTOR = 2;
	
		// beta bot inverse maps // 6
	// public static final int LEFT_REAR_MOTOR = 4; //Encoder on this one, counts down when driving forward
	// public static final int LEFT_FRONT_MOTOR = 6; 
	// public static final int RIGHT_FRONT_MOTOR = 2;//Encoder on this one, counts up when driving forward
	// public static final int RIGHT_REAR_MOTOR = 1; // 6
		
	// OMEGA BOT
	 public static final int LEFT_REAR_MOTOR = 4; //Encoder on this one, counts down when driving forward
	 public static final int LEFT_FRONT_MOTOR = 3; 
	 public static final int RIGHT_FRONT_MOTOR = 1;//Encoder on this one, counts up when driving forward
	 public static final int RIGHT_REAR_MOTOR = 2; 
	 public static final int WINDOW_MOTOR = 5;


	// some controller ports
	public static int JOYSTICK_DRIVE_ONE = 0;
	public static int JOYSTICK_DRIVE_TWO = 1;

	// pneumatic solenoid ports
	public static int HATCH_SOLENOID_ONE = 0;
	public static int HATCH_SOLENOID_TWO = 1;

	//

	//TODO: MAP THESE TO ACTUAL BUTTONS
	public static int HATCH_FORWARD_BUTTON = 6;
	public static int HATCH_REVERSE_BUTTON =5;
	public static int TEST_RUMBLE_BUTTON = 6;

	public static boolean HALF_SPEED = false;

	
}