package org.usfirst.frc.team5557.robot.commands.autogroups;

//import necessary commands 

import org.usfirst.frc.team5557.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

// class name describes what the file does, in this case it is an example 
public class ExampleAutonomous extends CommandGroup {

	//create constructor with same name as class
	public ExampleAutonomous(){
		
		// addSqeuntial will run the command, finish then run the next command
		addSequential(new DriveForDistanceCommand(104));
		addSequential(new DriveForDistanceCommand(104));

		//or 
		
		// addParallel will run both of these commands at the same time
		addParallel(new DriveForDistanceCommand(104));
		addParallel(new ExpellCommand());

	}
	
}
