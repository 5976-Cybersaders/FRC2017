package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class InitDriveTrainForPositionMode extends InitDriveTrain {
	private boolean inversion = true;

	public InitDriveTrainForPositionMode(DriveTrain driveTrain) {
		super(driveTrain);
	}
	
	@Override
	protected void initTalons() {
		// Setup Talons for use
		initMaster(leftMaster);
		//leftMaster.reverseOutput(inversion);
		//leftMaster.reverseSensor(inversion);
		System.out.println("END INIT left master");
		
		initSlave(leftSlave, leftMaster.getDeviceID());
		//leftSlave.reverseOutput(inversion);
		//leftSlave.reverseSensor(inversion);
		System.out.println("END INIT left slave");

		initMaster(rightMaster);
		//rightMaster.reverseOutput(inversion);
		//rightMaster.reverseSensor(inversion);
		System.out.println("END INIT right master");
		
		initSlave(rightSlave, rightMaster.getDeviceID());
		//rightSlave.reverseOutput(inversion);
		//rightSlave.reverseSensor(inversion);
		System.out.println("END INIT right slave");
	}
	
	protected void initCommon(CANTalon talon) {
		super.initCommon(talon);
		talon.configNominalOutputVoltage(+0.0f, -0.0f);
		talon.setVoltageRampRate(RobotMap.RAMP_RATE);
		talon.enableBrakeMode(RobotMap.BRAKE_MODE);
		talon.setAllowableClosedLoopErr(RobotMap.ALLOWABLE_ERROR);
		
		double pValue = SmartDashboard.getNumber("P-value", 1.0);
		double iValue = SmartDashboard.getNumber("I-value", 0);
		double dValue = SmartDashboard.getNumber("D-value", 0);
		
		talon.setPID(pValue, iValue, dValue);
		talon.enableControl();
	}
	
	protected void initMaster(CANTalon talon) {
		super.initMaster(talon);
		talon.setControlMode(CANTalon.TalonControlMode.Position.value);
		talon.set(0);
		talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		talon.configEncoderCodesPerRev(360);
		talon.setPosition(0);
		talon.setEncPosition(0);
		initCommon(talon);
	}
	
	protected void initSlave(CANTalon talon, int masterID) {
		//super.initSlave(talon, masterID);
		//talon.changeControlMode(CANTalon.TalonControlMode.Follower);
		talon.setControlMode(CANTalon.TalonControlMode.Follower.value);
		talon.set(masterID);
		initCommon(talon);
	}
	
	protected double getMaxVoltage() {
		return RobotMap.PEAK_VOLTAGE;
	}
}
