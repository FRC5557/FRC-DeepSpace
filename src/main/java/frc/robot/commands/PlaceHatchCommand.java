/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.command.Command;

public class PlaceHatchCommand extends Command {
  public static final I2C colorSensor = new I2C(I2C.Port.kOnboard, 0x39);

  public PlaceHatchCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // first -> align yourself with white line
    // https://ftc-tricks.com/overview-color-sensor/
    // second -> go straight towards it
    // encoder stuff. pretty standard
    // third -> get limelight data and how far away from hatch you are
    // tx and tr stuff
    // fourth -> when close enough, place hatch (manual move back)
    // run the peumatics command
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
