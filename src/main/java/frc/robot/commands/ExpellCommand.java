package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ExpellCommand extends Command {

	private ArmSubsystem arm = ArmSubsystem.getInstance();
	
	int motor = RobotMap.INTAKE_MOTOR_LEFT;
	
	public ExpellCommand() {
	}
	
	@Override
	protected void initialize() {
		//requires(Robot.arm);
	}

	@Override
	protected void execute() {
		arm.expell(motor);
		
	
	} 
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {
		arm.stop();
	}
}
