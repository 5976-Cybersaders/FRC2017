package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

public class InitDriveTrainForPositionMode extends InitDriveTrain {

	public InitDriveTrainForPositionMode(DriveTrain driveTrain) {
		super(driveTrain);
	}
	
	@Override
	protected void initTalons() {
		// Setup Talons for use
		initMaster(leftMaster);
		System.out.println("END INIT left master");
		
		initSlave(leftSlave, leftMaster.getDeviceID());
		System.out.println("END INIT left slave");

		initMaster(rightMaster);
		System.out.println("END INIT right master");
		
		initSlave(rightSlave, rightMaster.getDeviceID());
		System.out.println("END INIT right slave");
	}
	
	protected void initCommon(CANTalon talon) {
		super.initCommon(talon);
		talon.configNominalOutputVoltage(+0.0f, -0.0f);
		talon.setVoltageRampRate(RobotMap.RAMP_RATE);
		talon.enableBrakeMode(RobotMap.BRAKE_MODE);
		talon.setAllowableClosedLoopErr(RobotMap.ALLOWABLE_ERROR);
		talon.setPID(RobotMap.kP, RobotMap.kI, RobotMap.kD);
		talon.enableControl();
	}
	
	protected void initMaster(CANTalon talon) {
		super.initMaster(talon);
		talon.setControlMode(CANTalon.TalonControlMode.Position.value);
		talon.set(0);
		talon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		talon.configEncoderCodesPerRev(360);
		talon.setPosition(0);
		initCommon(talon);
	}
	
	protected void initSlave(CANTalon talon, int masterID) {
		super.initSlave(talon, masterID);
	}
	
	protected double getMaxVoltage() {
		return RobotMap.PEAK_VOLTAGE;
	}
}
