package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.SmartValue;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

public class Turn extends EncoderDriveCommand {
	private double angle;
	private SmartValue smartTime, smartAngle;
	
	public Turn(double angle, DriveTrain driveTrain) {
		super(driveTrain);
		this.angle = angle;
	}
	
	public Turn(SmartValue smartAngle, DriveTrain driveTrain) {
		super(driveTrain);
		this.smartAngle = smartAngle;
	}
	
	public Turn(SmartValue smartAngle, DriveTrain driveTrain, SmartValue smartTime) {
		super(driveTrain);
		this.smartAngle = smartAngle;
		this.smartTime = smartTime;
	}
	
	protected void initialize() {
		super.initialize();
		if (smartAngle != null) angle = smartAngle.getDouble();
		revolutions = toRevolutionsFromDegrees(angle);
		System.out.println("STARTING COMMAND TURN ANGLE: " + angle + " REVS: "+ revolutions + " TIMEOUT: " + getTimeOut());
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
	
	protected double getTimeOut() {
		if (smartTime != null) return smartTime.getDouble(); 
		else return super.getTimeOut();
	}
} 