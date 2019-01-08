package frc.robot.org.usfirst.frc.team5557.robot.commands.autogroups;

import frc.robot.org.usfirst.frc.team5557.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class AutoLineFromMiddle extends CommandGroup {

	public AutoLineFromMiddle() {
		
		addSequential(new DriveForDistanceCommand(1000));
		
	}
	
}
