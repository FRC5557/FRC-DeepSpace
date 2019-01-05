package org.usfirst.frc.team5557.robot.commands;


import org.usfirst.frc.team5557.robot.subsystems.LinearActuatorSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ExtendLinearActuatorCommand extends Command {

	
	private LinearActuatorSubsystem linearActuator = LinearActuatorSubsystem.getInstance(); 
	
	
	@Override
	protected void initialize() {
//		requires(linearActuator);
	}

	@Override
	protected void execute() {
		linearActuator.moveMotor(1);
	} 
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {
		linearActuator.motorSpeed = 1;
	}

}
