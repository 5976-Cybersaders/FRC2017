package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.SmartValue;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Turn extends EncoderDriveCommand {
	private double angle;
	
	public Turn(double angle, DriveTrain driveTrain) {
		super(driveTrain);
		this.angle = angle;
	}
	
	public Turn(SmartValue angle, DriveTrain driveTrain) {
		super(driveTrain);
		this.angle = angle.getValue();
	}
	
	protected void initialize() {
		super.initialize();
		revolutions = toRevolutionsFromDegrees(angle);
		System.out.println("STARTING COMMAND TURN ANGLE: " + angle + " REVS: "+ revolutions);
	}
	
	protected void execute() {
		leftMaster.set(revolutions);
		leftSlave.set(leftMaster.getDeviceID());
		rightMaster.set(revolutions);
		rightSlave.set(rightMaster.getDeviceID());
		
		if (printCounter >= printInterval) {
			reportExecute(leftMaster, "Left Master", RobotMap.LEFT_MASTER_PDP);
			//reportExecute(leftSlave, "Left Slave", RobotMap.LEFT_SLAVE_PDP);
			reportExecute(rightMaster, "Right Master", RobotMap.RIGHT_MASTER_PDP);
			//reportExecute(rightSlave, "Right Slave", RobotMap.RIGHT_SLAVE_PDP);
			printCounter = 0;
		} else printCounter++;
	}
	
	public double toRevolutionsFromDegrees(double angle) {
		double circumferenceFraction = angle / 360.0;
		double circumference = Math.PI * 23.5;
		return toRevolutions(circumferenceFraction * circumference);
	}
} 