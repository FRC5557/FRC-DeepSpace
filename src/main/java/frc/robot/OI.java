/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.PneumaticsTestCommand;
import frc.robot.commands.SolenoidOffCommand;

/**
 * Pretty much OI is meant to handle certain cases with the controller (joystick or whatever youre using)
 */
public class OI {

    public static final Joystick controller = new Joystick(RobotMap.JOYSTICK_DRIVE_ONE);
    // public static final I2C colorSensor = new I2C(I2C.Port.kOnboard, 0x39);

    public final Button solenoidOnButton = new JoystickButton(controller, RobotMap.HATCH_FORWARD_BUTTON);
    public final Button solenoidOffButton = new JoystickButton(controller, RobotMap.HATCH_REVERSE_BUTTON);
    // add buttons here when we know what our hatch system is doing

    // Solenoid solenoid = new Solenoid(0);

    public OI() {
        // when we make some arm buttons or such, modify code here
        // hatchForwardButton.whenPressed(new PneumaticsTestCommand(1, 2));
        //TODO: HAVE COMMAND FOR EACH PNEUMATICS THINGY AND MAP TO BUTTONS
        // solenoidOnButton.whileHeld(new PneumaticsTestCommand(solenoid));
        // solenoidOffButton.whileHeld(new SolenoidOffCommand(solenoid));
        
        //
    }




}
