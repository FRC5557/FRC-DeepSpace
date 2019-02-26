/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.CloseHatchSolenoid;
import frc.robot.commands.IntakeForward;
import frc.robot.commands.IntakeReverse;
import frc.robot.commands.OpenHatchSolenoid;


/**
 * Pretty much OI is meant to handle certain cases with the controller (joystick or whatever youre using)
 */
public class OI {

    public static final Joystick controller = new Joystick(RobotMap.JOYSTICK_DRIVE_ONE);

    private final Button closeHatchButton = new JoystickButton(controller, 2);
    private final Button openHatchButton = new JoystickButton(controller, 3);

    private final Button intakeForwardButton = new JoystickButton(controller, 6);
    private final Button intakeReverseButton = new JoystickButton(controller, 5);
    
    public OI() {
        closeHatchButton.whenPressed(new CloseHatchSolenoid());
        openHatchButton.whenPressed(new OpenHatchSolenoid());
        
        intakeForwardButton.whenPressed(new IntakeForward());
        intakeReverseButton.whenPressed(new IntakeReverse());
    }

}
