package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.OI;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.commands.TeleOpClimb;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	private Talon talon;
	private OI oi;
	
	public Climber(OI oi) {
		this.oi = oi;
		talon = new Talon(RobotMap.CLIMB_MOTOR);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOpClimb(oi.getClimbController(), this));
	}
	
	public Talon getTalon() {
		return talon;
	}
}
