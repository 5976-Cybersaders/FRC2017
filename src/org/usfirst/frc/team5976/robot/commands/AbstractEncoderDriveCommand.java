package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.SmartDashboardMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

public abstract class AbstractEncoderDriveCommand extends Command {
	
	protected DriveTrain driveTrain;
	protected CANTalon leftMaster, leftSlave, rightMaster, rightSlave;
	protected double revolutions;
	protected int stableCount;
	protected int printCounter = 20, printInterval = 20;
	private double previousError;
	private long t0 = 0;
	
	//Wheel Values
	private final int STABLE_COUNT_STOP = 20;
	private final int DIAMETER = 6;
	private final double revsPerSecond = 1.6;
	
	public AbstractEncoderDriveCommand(DriveTrain driveTrain){
		this.driveTrain = driveTrain;
		leftMaster = driveTrain.getLeftMaster();
		leftSlave = driveTrain.getLeftSlave();
		rightMaster = driveTrain.getRightMaster();
		rightSlave = driveTrain.getRightSlave();
		
		revolutions = 0;
		stableCount = 0;
		previousError = 9999999;
		requires(driveTrain);
	}
	
	@Override
	protected void initialize() {
		leftMaster.reset();
		leftMaster.enable();
		leftMaster.setPosition(0);
		leftMaster.setEncPosition(0);
		rightMaster.reset();
		rightMaster.enable();
		rightMaster.setPosition(0);
		rightMaster.setEncPosition(400);
		
		stableCount = 0;
		previousError = 1000000;
		
		report(driveTrain.getLeftMaster());
		report(driveTrain.getLeftSlave());
		report(driveTrain.getRightMaster());
		report(driveTrain.getRightSlave());
		
		t0 = System.currentTimeMillis();
	}
	
	protected void report(CANTalon talon) {
		ReportHelper.report(talon, this);
	}
	
	protected void reportExecute(CANTalon talon, String side, int pdpPort) {
		ReportHelper.reportExecute(talon, side, driveTrain.getPDP(), pdpPort, stableCount);
	}
	
	protected double toRevolutions(double inches){
		return inches / (Math.PI * DIAMETER);
	}
	
	@Override
	protected boolean isFinished() {
		double currentError = (leftMaster.getError() + rightMaster.getError()) / 2;
		if (Math.abs(currentError) < SmartDashboardMap.ALLOWABLE_ERROR.getDouble() && previousError == currentError) {
			stableCount++;
			if (stableCount >= STABLE_COUNT_STOP) return true;
		}
		else {
			if (System.currentTimeMillis() - t0 >= getTimeOut()){
				return true;
			}
			stableCount = 0;
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
	
	protected double getTimeOut(){
		double targetRevs = (Math.abs(leftMaster.getSetpoint()) + Math.abs(rightMaster.getSetpoint()))/2;
		return targetRevs*revsPerSecond*1000;
	}
}
