package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class TeleOpClimb extends Command {
	private Talon talon;
	private XboxController climbController;
	private boolean hasClimbed = false;
	
	public TeleOpClimb(XboxController climbController, Climber climber) {
		talon = climber.getTalon();
		this.climbController = climbController;
		requires(climber);
	}
	
	protected void initialize() {
		talon.setInverted(true);
	}
	
	protected void execute() {
		double input = climbController.getY(Hand.kLeft);
		double output = 0;
		if (input > 0.5) output = 1;
		else if (input > 0.1) output = 0.4;
		if (climbController.getXButton()) hasClimbed = true;
		if (hasClimbed) output = 1;
		talon.set(output);
	}
	
	protected boolean isFinished() {
		return false;
	}

}
