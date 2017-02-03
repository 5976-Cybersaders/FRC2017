package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.Climber;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class TeleOpClimb extends Command {
	private CANTalon talon;
	private XboxController driveController;
	
	public TeleOpClimb(XboxController driveController, Climber climber) {
		talon = climber.getTalon();
		this.driveController = driveController;
		requires(climber);
	}
	
	protected void initialize() {
		
	}
	
	protected void execute() {
		double input = driveController.getTriggerAxis(Hand.kRight);
		double output = 0;
		if (input > 0.5) {
			output = 0.75;
		}
		else if (input > 0.1) {
			output = 0.25;
		}
		talon.set(output);
	}
	
	protected boolean isFinished() {
		return false;
	}

}
