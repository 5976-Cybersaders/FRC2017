package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraight extends Command{
	private DriveTrain driveTrain;
	private CANTalon leftMaster, rightMaster;
	private double revolutions;
	private double previousError;
	private int countWithinError;
	private int printCount;
	
	//Wheel values
	private final int DIAMETER = 6;
	private final int REQUIRED_NUMBER_WITHIN_ERROR = 20;
	
	public DriveStraight(double inches, DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
		leftMaster = driveTrain.getLeftMaster();
		rightMaster = driveTrain.getRightMaster();
		revolutions = toRevolutions(inches);
		printCount = 0;
		requires(driveTrain);
	}
	
	protected double toRevolutions(double inches){
		return inches / (Math.PI * DIAMETER);
	}
	
	protected void initialize() {
		leftMaster.reset();
		leftMaster.enable();
		leftMaster.setPosition(0);
		rightMaster.reset();
		rightMaster.enable();
		rightMaster.setPosition(0);
		
		leftMaster.set(-revolutions);
		rightMaster.set(revolutions);
		countWithinError = 0;
		previousError = 1000000;
		
		report(driveTrain.getLeftMaster());
		report(driveTrain.getLeftSlave());
		report(driveTrain.getRightMaster());
		report(driveTrain.getRightSlave());
	}
	
	public void report(CANTalon talon) {
		System.out.println("INIT " + this);
		System.out.println("Control Mode: " + talon.getControlMode());
		System.out.println("Device ID" + talon.getDeviceID());
		System.out.println("Closed Loop Ramp Rate" + talon.getCloseLoopRampRate());
		System.out.println("Source Type " + talon.getPIDSourceType());
		System.out.println("Inverted: " + talon.getInverted());
		System.out.println("PID Values: " + talon.getP() + " " + talon.getI() + " " + talon.getD());
		System.out.println();
	}
	
	protected void execute() {
		SmartDashboard.putNumber("Left Revolutions", leftMaster.getPosition());
		SmartDashboard.putNumber("Right Revolutions", rightMaster.getPosition());
		if (printCount == 5) {
			reportExecute(leftMaster, "Left Master", RobotMap.LEFT_MASTER_PDP);
			reportExecute(driveTrain.getLeftSlave(), "Left Slave", RobotMap.LEFT_SLAVE_PDP);
			reportExecute(rightMaster, "Right Master", RobotMap.RIGHT_MASTER_PDP);
			reportExecute(driveTrain.getRightSlave(), "Right Slave", RobotMap.RIGHT_SLAVE_PDP);
			System.out.println("Inversion: " + leftMaster.getInverted() + " " + driveTrain.getLeftSlave().getInverted() + " " + rightMaster.getInverted() + " " + driveTrain.getRightSlave().getInverted());
			System.out.println();
			printCount = 0;
		}
		else {
			printCount++;
		}
	}
	
	public void reportExecute(CANTalon talon, String side, int pdp) {
		System.out.println(side + " Set: " + talon.getSetpoint() + 
				" CE: " + talon.getError() +
				" Enc Pos: " + talon.getEncPosition() + 
				" Revs: " + talon.getPosition() + 
				" Count: " + countWithinError);
		SmartDashboard.putNumber(side + " Current", driveTrain.getPDP().getCurrent(pdp));
	}
	
	@Override
	protected boolean isFinished() {
		double currentError = (leftMaster.getError() + rightMaster.getError()) / 2;
		if (Math.abs(currentError) < RobotMap.ALLOWABLE_ERROR && previousError == currentError) {
			countWithinError++;
			if (countWithinError >= REQUIRED_NUMBER_WITHIN_ERROR) return true;
		}
		else {
			countWithinError = 0;
			previousError = currentError;
		}
		return false;
	}
	
	protected void end() {
		System.out.println("END " + this);
	}
	
	protected void interrupted() {
        end();
    }
}
