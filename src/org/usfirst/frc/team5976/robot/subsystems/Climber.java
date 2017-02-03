package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.OI;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.commands.TeleOpClimb;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	private CANTalon talon;
	private PowerDistributionPanel pdp;
	private OI oi;
	
	public Climber(OI oi) {
		this.oi = oi;
		talon = new CANTalon(RobotMap.CLIMB_MOTOR);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new TeleOpClimb(oi.getDriveController(), this));
	}
	
	public CANTalon getTalon() {
		return talon;
	}
}
