package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.subsystems.GearDelivery;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class TeleOpControlGearDelivery extends Command {
	private XboxController driveController;
	private Servo leftServo, rightServo;
	
	public TeleOpControlGearDelivery(XboxController driveController, GearDelivery gearDelivery) {
		this.driveController = driveController;
		leftServo = gearDelivery.getLeftServo();
		rightServo = gearDelivery.getRightServo();
		requires(gearDelivery);
	}
	
	protected void initialize() {
		leftServo.set(0.5);
		rightServo.set(0.5);
	}
	
	protected void execute() {
		if (driveController.getTriggerAxis(Hand.kLeft) > 0.5) {
			leftServo.set(0);
			rightServo.set(1);
		}
		else {
			leftServo.set(0.2);
			rightServo.set(0.8);
		}
	}

	protected boolean isFinished() {
		return false;
	}

}
