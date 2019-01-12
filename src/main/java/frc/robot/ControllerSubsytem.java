/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ControllerSubsytem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  double[] restingTriggerVals = new double[2];


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setController(int layout){
		System.out.println("PS4 Controller!");
		OI.controller.setXChannel(0);
		OI.controller.setTwistChannel(3);
		OI.controller.setThrottleChannel(4);
		OI.controller.setZChannel(2);
		restingTriggerVals = triggerCalibrate();
	}
	
	public double getTrigerThrottle(double leftT, double rightT){
		double fThrottle = 0;
		if(rightT > restingTriggerVals[0]+.1){ //forward input will always take priority over reverse input
			fThrottle = (rightT*(1/1.6)+.30);
		}else if(leftT > restingTriggerVals[1]){
			fThrottle = -(leftT*(1/1.6)+.45); //backward has slightly higher offset because motors are slower backwards
		}
		return fThrottle;
  }
  
  public double[] triggerCalibrate(){
		double [] callVal = new double[2];
		callVal[0] = OI.controller.getTwist(); //left trigger
		callVal[1] = OI.controller.getThrottle(); //right trigger
		System.out.println("Left Trigger: " + callVal[0]);
		System.out.println("Right Trigger: " + callVal[1]);
		return callVal;
	}
	
}
