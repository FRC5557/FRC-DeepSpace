package frc.robot.org.usfirst.frc.team5557.robot.commands.autogroups;

import frc.robot.org.usfirst.frc.team5557.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class SwitchFromRight extends CommandGroup {

	public SwitchFromRight() {
		
		addSequential(new DriveForDistanceCommand(1000));
		
		addSequential(new TurnForAngleCommand(-90));

		addSequential(new DriveForDistanceCommand(1000));
		
		addSequential(new IntakeCommand());
		
		addSequential(new RaiseArmCommand(1800));
		
		addSequential(new ExpellCommand());
	}
	
}
