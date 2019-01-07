package frc.robot.commands;


import frc.robot.subsystems.LinearActuatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class MoveLinearActuatorCommand extends Command {

	
	private LinearActuatorSubsystem linearActuator = LinearActuatorSubsystem.getInstance(); 
	
	@Override
	protected void initialize() {
		//requires(Robot.arm);
	}

	@Override
	protected void execute() {
		linearActuator.moveMotor(0.1);
	} 
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		linearActuator.motorSpeed = -.1;
	}

}
