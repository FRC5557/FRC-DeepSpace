/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.PneumaticSubsystem;

/**
 * Add your docs here.
 */
public class IntakeReverse extends InstantCommand {
  /**
   * Add your docs here.
   */
  PneumaticSubsystem pneumaticSubsystem = PneumaticSubsystem.getInstance();
  public IntakeReverse() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(pneumaticSubsystem);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    pneumaticSubsystem.solenoid.set(Value.kReverse);
  }

}
