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
	
	//Wheel values
	private final int DIAMETER = 6;
	private final int REQUIRED_NUMBER_WITHIN_ERROR = 20;
	
	public DriveStraight(double inches, DriveTrain driveTrain) {
		this.driveTrain = driveTrain;
		leftMaster = driveTrain.getLeftMaster();
		rightMaster = driveTrain.getRightMaster();
		revolutions = toRevolutions(inches);
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
		
		leftMaster.set(revolutions);
		rightMaster.set(revolutions);
		countWithinError = 0;
		previousError = 1000000;
		System.out.println("INIT " + this);
	}
	
	protected void execute() {
		SmartDashboard.putNumber("Left Revolutions", leftMaster.getPosition());
		SmartDashboard.putNumber("Right Revolutions", rightMaster.getPosition());
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
