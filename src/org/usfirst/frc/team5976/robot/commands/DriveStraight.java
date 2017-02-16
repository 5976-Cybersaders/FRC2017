package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends EncoderDriveCommand {
	private int printCount;
	private String dashboardKey;
	private double defaultInches;
	private double inches;
	
	public DriveStraight(double inches, DriveTrain driveTrain) {
		super(driveTrain);
		this.inches = inches;
		revolutions = toRevolutions(inches);
		printCount = 5;
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
		report(driveTrain.getLeftSlave());
		report(rightMaster);
		report(driveTrain.getRightSlave());
	}
	
	protected void execute() {
		SmartDashboard.putNumber("Left Revolutions", leftMaster.getPosition());
		SmartDashboard.putNumber("Right Revolutions", rightMaster.getPosition());
		leftMaster.set(-revolutions);
		rightMaster.set(revolutions);
		if (printCount == 5) {
			reportExecute(leftMaster, "Left Master", RobotMap.LEFT_MASTER_PDP);
			//reportExecute(driveTrain.getLeftSlave(), "Left Slave", RobotMap.LEFT_SLAVE_PDP);
			reportExecute(rightMaster, "Right Master", RobotMap.RIGHT_MASTER_PDP);
			//reportExecute(driveTrain.getRightSlave(), "Right Slave", RobotMap.RIGHT_SLAVE_PDP);
			//System.out.println("Inversion: " + leftMaster.getInverted() + " " + driveTrain.getLeftSlave().getInverted() + " " + rightMaster.getInverted() + " " + driveTrain.getRightSlave().getInverted());
			System.out.println();
			printCount = 0;
		}
		else {
			printCount++;
		}
	}
}
