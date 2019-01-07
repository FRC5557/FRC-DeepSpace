package frc.robot.commands;

import frc.robot.RobotMap;
import frc.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeCommand extends Command{

	private ArmSubsystem arm = ArmSubsystem.getInstance();
	
	int motor = RobotMap.INTAKE_MOTOR_LEFT;
	
	public IntakeCommand() {
	}
	
	@Override
	protected void initialize() {
		//requires(Robot.arm);
	}

	@Override
	protected void execute() {
		arm.intake(motor);
	} 
	
	@Override
	protected boolean isFinished() {
		if(!(arm.getLimSwitchStatus(RobotMap.INTAKE_LIMIT_SWITCH))){
			return true;
		}
		return false;
	}
	
	@Override
	protected void end() {
		arm.stop();
	}
}
