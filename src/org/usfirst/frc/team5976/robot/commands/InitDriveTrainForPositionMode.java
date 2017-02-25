package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class InitDriveTrainForPositionMode extends InitDriveTrain {
	private boolean inversion = true;

	private double LEFT_VOLTS = 3.99;
	private double RIGHT_VOLTS = 3.5;
	private int allowableError;
	
	public  InitDriveTrainForPositionMode(DriveTrain driveTrain) {
		super(driveTrain);
	}
	
	protected void initialize() {
		super.initialize();
		allowableError = (int) SmartDashboard.getNumber("Allowable Error", 50);
	}
	
	@Override
	protected void initTalons() {
		// Setup Talons for use
		initMaster(leftMaster, 400, LEFT_VOLTS);
		leftMaster.reverseSensor(inversion);
		System.out.println("END INIT left master");
		
		//initSlave(leftSlave, leftMaster.getDeviceID());
		//System.out.println("END INIT left slave");

		initMaster(rightMaster, 360, RIGHT_VOLTS);
		//rightMaster.reverseSensor(inversion);
		System.out.println("END INIT right master");
		
		//initSlave(rightSlave, rightMaster.getDeviceID());
		//System.out.println("END INIT right slave");
	}
	
	protected void initCommon(CANTalon talon, double peakVolts) {
		//super.initCommon(talon);
		talon.setProfile(0);
		talon.configPeakOutputVoltage(+peakVolts, -peakVolts);
		
		talon.configNominalOutputVoltage(+0.0f, -0.0f);
		talon.setVoltageRampRate(RobotMap.RAMP_RATE);
		talon.enableBrakeMode(RobotMap.BRAKE_MODE);
		talon.setAllowableClosedLoopErr(allowableError);
		
		double pValue = SmartDashboard.getNumber("P-value", 1.0);
		double iValue = SmartDashboard.getNumber("I-value", 0);
		double dValue = SmartDashboard.getNumber("D-value", 0);
		
		talon.setPID(pValue, iValue, dValue);
		talon.enableControl();
	}
	
	protected void initMaster(CANTalon talon, int ticksPerRev, double peakVoltage) {
		talon.setControlMode(CANTalon.TalonControlMode.Position.value);
		talon.set(0);
		talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		talon.configEncoderCodesPerRev(ticksPerRev);
		talon.setPosition(0);
		talon.setEncPosition(0);
		initCommon(talon, peakVoltage);
	}
	
	protected void initSlaves() {
		initSlave(leftSlave, leftMaster.getDeviceID(), LEFT_VOLTS);
		initSlave(rightSlave, rightMaster.getDeviceID(), RIGHT_VOLTS);
	}
	
	protected void initSlave(CANTalon talon, int masterID, double peakVolts) {
		//initCommon(talon);
		talon.configPeakOutputVoltage(peakVolts, -peakVolts);
		talon.changeControlMode(CANTalon.TalonControlMode.Follower);
		//talon.setControlMode(CANTalon.TalonControlMode.Follower.value);
		talon.set(masterID);
		talon.enable();
	}
	
	protected double getMaxVoltage() {
		return RobotMap.PEAK_VOLTAGE;
	}
}
