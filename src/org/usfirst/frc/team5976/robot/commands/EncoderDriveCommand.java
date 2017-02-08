package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class EncoderDriveCommand extends Command{
	
	protected DriveTrain driveTrain;
	protected CANTalon leftMaster, rightMaster;
	protected double revolutions;
	protected int stableCount;
	protected double previousError;
	
	//Wheel values
	protected final int STABLE_COUNT_STOP = 20;
	private final int DIAMETER = 6;
	
	public EncoderDriveCommand(DriveTrain driveTrain){
		this.driveTrain = driveTrain;
		leftMaster = driveTrain.getLeftMaster();
		rightMaster = driveTrain.getRightMaster();
		revolutions = 0;
		stableCount = 0;
		previousError = 9999999;
		requires(driveTrain);
	}
	
	protected void reportExecute(CANTalon talon, String side, int pdp) {
		System.out.println(side + " Set: " + talon.getSetpoint() + 
				//Gets the current status of the Talon (usually a sensor value).
				" Pos: " + talon.get() +
				//Get the current difference between the setpoint and the sensor value.
				" CLErr: " + talon.getClosedLoopError() +
				//Get the current encoder position, regardless of whether it is the current feedback device.
				" Enc Pos: " + talon.getEncPosition() + 
				//Returns the difference between the setpoint and the current position.
				" Err: " + talon.getError() +
				//When using analog sensors, 0 units corresponds to 0V, 1023 units corresponds to 3.3V When using an analog encoder 
				//(wrapping around 1023 to 0 is possible) the units are still 3.3V per 1023 units.
				" Pos(in revs): " + talon.getPosition() + 
				" Count: " + stableCount);
		SmartDashboard.putNumber(side + " Current", driveTrain.getPDP().getCurrent(pdp));
	}
	
	protected void report(CANTalon talon) {
		System.out.println("INIT " + this);
		System.out.println("Control Mode: " + talon.getControlMode());
		System.out.println("Device ID" + talon.getDeviceID());
		System.out.println("Closed Loop Ramp Rate" + talon.getCloseLoopRampRate());
		System.out.println("Source Type " + talon.getPIDSourceType());
		System.out.println("Inverted: " + talon.getInverted());
		System.out.println("PID Values: " + talon.getP() + " " + talon.getI() + " " + talon.getD());
		System.out.println("Position: " + talon.getPosition());
		System.out.println();
	}
	
	@Override
	protected void initialize() {
		leftMaster.reset();
		leftMaster.enable();
		leftMaster.setPosition(0);
		leftMaster.setEncPosition(0);
		driveTrain.getLeftSlave().setPosition(0);
		rightMaster.reset();
		rightMaster.enable();
		rightMaster.setPosition(0);
		rightMaster.setEncPosition(0);
		rightMaster.reverseSensor(true);
		driveTrain.getRightSlave().setPosition(0);
		
		stableCount = 0;
		previousError = 1000000;
		
		report(driveTrain.getLeftMaster());
		report(driveTrain.getLeftSlave());
		report(driveTrain.getRightMaster());
		report(driveTrain.getRightSlave());
	}
	
	protected double toRevolutions(double inches){
		return inches / (Math.PI * DIAMETER);
	}
	
	@Override
	protected boolean isFinished() {
		double currentError = (leftMaster.getError() + rightMaster.getError()) / 2;
		if (Math.abs(currentError) < RobotMap.ALLOWABLE_ERROR && previousError == currentError) {
			stableCount++;
			if (stableCount >= STABLE_COUNT_STOP) return true;
		}
		else {
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
}
