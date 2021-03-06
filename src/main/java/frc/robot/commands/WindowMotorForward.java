/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.DriveSubsystem;

/**
 * Add your docs here.
 */
public class WindowMotorForward extends InstantCommand {
  /**
   * Add your docs here.
   */
  DriveSubsystem drive = DriveSubsystem.getInstance();
  public WindowMotorForward() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(drive);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    drive.windowMotorForward();
    drive.setLedValue(0.31);
  }

}
