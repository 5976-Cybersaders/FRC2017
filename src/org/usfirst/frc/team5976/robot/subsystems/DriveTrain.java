package org.usfirst.frc.team5976.robot.subsystems;

import org.usfirst.frc.team5976.robot.OI;
import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.commands.DoNothingCommand;
import org.usfirst.frc.team5976.robot.commands.EnterTeleOpCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	private RobotDrive robotDrive;
	private CANTalon leftMaster, leftSlave, rightMaster, rightSlave;
	private PowerDistributionPanel pdp;
	private OI oi;
	
	public DriveTrain(OI oi) {
		super();
		System.out.println("START INIT DriveTrain");
		leftMaster = new CANTalon(RobotMap.LEFT_MASTER);
		leftSlave = new CANTalon(RobotMap.LEFT_SLAVE);
		rightMaster = new CANTalon(RobotMap.RIGHT_MASTER);
		rightSlave = new CANTalon(RobotMap.RIGHT_SLAVE);
		pdp = new PowerDistributionPanel();
		this.oi = oi;
		
		//Master motors should always be the motor in front
		robotDrive = new RobotDrive(leftMaster, leftSlave, rightMaster, rightSlave);
		System.out.println("END INIT DriveTrain");
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
	
	public void updateDefaultCommandForNonTeleOp() {
		//If you see an error like "Output not updated often" change this value
		//robotDrive.setExpiration(MotorSafety.DEFAULT_SAFETY_EXPIRATION);
		setDefaultCommand(null);
	}
	
	public void updateDefaultCommandForTeleOp() {
		//If you see an error like "Output not updated often" change this value
		//robotDrive.setExpiration(MotorSafety.DEFAULT_SAFETY_EXPIRATION);
		setDefaultCommand(new EnterTeleOpCommand(this));
	}
		
	public RobotDrive getRobotDrive() {
		return robotDrive;
	}

	public CANTalon getLeftMaster() {
		return leftMaster;
	}

	public CANTalon getRightMaster() {
		return rightMaster;
	}
	
	public CANTalon getLeftSlave() {
		return leftSlave;
	}

	public CANTalon getRightSlave() {
		return rightSlave;
	}
	
	public PowerDistributionPanel getPDP() {
		return pdp;
	}
	
	public OI getOI() {
		return oi;
	}
}
