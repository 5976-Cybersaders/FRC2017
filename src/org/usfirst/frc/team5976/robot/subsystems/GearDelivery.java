package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.OI;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.commands.TeleOpClimb;
import org.usfirst.frc.team5976.robot.commands.TeleOpControlGearDelivery;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GearDelivery extends Subsystem {
	private Servo leftServo, rightServo;
	private PowerDistributionPanel pdp;
	private OI oi;
	
	public GearDelivery(OI oi) {
		this.oi = oi;
		leftServo = new Servo(RobotMap.LEFT_SERVO);
		rightServo = new Servo(RobotMap.RIGHT_SERVO);
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOpControlGearDelivery(oi.getDriveController(), this));
	}
	
	public Servo getLeftServo() {
		return leftServo;
	}
	
	public Servo getRightServo() {
		return rightServo;
	}

}
