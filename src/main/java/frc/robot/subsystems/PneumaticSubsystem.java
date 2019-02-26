/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class PneumaticSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static PneumaticSubsystem instance = null;

  public DoubleSolenoid solenoid;
  public DoubleSolenoid solenoid2;
  public Compressor c;

  public static PneumaticSubsystem getInstance() {
		if(instance == null) {
			instance = new PneumaticSubsystem();
		}
		return instance;
	}

  @Override
  public void initDefaultCommand() {
    this.solenoid = new DoubleSolenoid(0, 1);
    this.solenoid2 = new DoubleSolenoid(2, 3);
    this.c = new Compressor(0);
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
