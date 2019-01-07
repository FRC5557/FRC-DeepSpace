package frc.robot.commands;

import frc.robot.subsystems.ControllerSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class SwapDriveComand extends Command {

	private ControllerSubsystem control = ControllerSubsystem.getInstance();
	
	String layout;
	int layoutInt;
	int eee=0;
	
	public SwapDriveComand(String layout){
		super("SwapDrive");
		this.layout = layout;
	}
	
	public void initialize(){
	
	}
	
	public void execute() {
		switch(layout) {
		case "STICKS":
			layoutInt = 0;
			break;
		case "CONTROLLER":
			layoutInt = 1;
		}
		System.out.println(layout + "fired");
		
		control.setLayoutInt(layoutInt);
		control.setController(layoutInt);
		eee = 1;
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		if(eee==1){
			return true;
		}
		return false;
	}

}
