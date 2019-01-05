package org.usfirst.frc.team5557.robot.commands;

import org.usfirst.frc.team5557.robot.RobotMap;

import org.usfirst.frc.team5557.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShootBoxCommand extends Command{
//	WPI_TalonSRX intaketMotortLeft = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR_LEFT);
//	WPI_TalonSRX intakeMotortRight = new WPI_TalonSRX(RobotMap.INTAKE_MOTOR_RIGHT);
//	SpeedControllerGroup intake = new SpeedControllerGroup(intaketMotortLeft, intakeMotortRight);
	
	private ArmSubsystem arm = ArmSubsystem.getInstance();
	
	
	@Override
	protected void initialize() {
//		requires(arm);
	}

	@Override
	protected void execute() {
		arm.shoot(RobotMap.INTAKE_MOTOR_LEFT);
		
	} 
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		arm.wristPower = -.1;
	}
	
}
