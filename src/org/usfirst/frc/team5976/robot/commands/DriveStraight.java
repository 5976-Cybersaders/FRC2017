package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends EncoderDriveCommand {
	private String dashboardKey;
	private double defaultInches;
	private double inches;
	
	public DriveStraight(double inches, DriveTrain driveTrain) {
		super(driveTrain);
		this.inches = inches;
		revolutions = toRevolutions(inches);
	}
	
	public DriveStraight(String dashboardKey, double defaultInches, DriveTrain driveTrain) {
		super(driveTrain);
		this.dashboardKey = dashboardKey;
		this.defaultInches = defaultInches;
	}
	
	protected void initialize() {
		super.initialize();
		if (dashboardKey != null) {
			inches = SmartDashboard.getNumber(dashboardKey, defaultInches);
			revolutions = toRevolutions(inches);
		}
		System.out.println("STARTING COMMAND DRIVE STRAIGHT INCHES: " + inches + " REVS: "+ revolutions);
		report(leftMaster);
		report(leftSlave);
		report(rightMaster);
		report(rightSlave);
	}
	
	protected void execute() {
		SmartDashboard.putNumber("Left Revolutions", leftMaster.getPosition());
		SmartDashboard.putNumber("Right Revolutions", rightMaster.getPosition());
		leftMaster.set(-revolutions);
		leftSlave.set(leftMaster.getDeviceID());
		rightMaster.set(revolutions);
		rightSlave.set(rightMaster.getDeviceID());
		
		if (printCounter == printInterval) {
			reportExecute(leftMaster, "Left Master", RobotMap.LEFT_MASTER_PDP);
			//reportExecute(leftSlave, "Left Slave", RobotMap.LEFT_SLAVE_PDP);
			reportExecute(rightMaster, "Right Master", RobotMap.RIGHT_MASTER_PDP);
			//reportExecute(rightSlave, "Right Slave", RobotMap.RIGHT_SLAVE_PDP);
			System.out.println();
			printCounter = 0;
		}
		else {
			printCounter++;
		}
	}
}
