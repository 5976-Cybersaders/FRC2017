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
		leftMaster.setControlMode(CANTalon.TalonControlMode.Position.value);
		leftMaster.set(0);
		leftMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		leftMaster.configEncoderCodesPerRev(360);
		leftMaster.setPosition(0);
		// lefMaster.setVoltageRampRate(RAMP_RATE);
		// Profile 0
		leftMaster.setProfile(0);
		leftMaster.configNominalOutputVoltage(+0.0f, -0.0f);
		leftMaster.configPeakOutputVoltage(+RobotMap.PEAK_VOLTAGE, -RobotMap.PEAK_VOLTAGE);
		leftMaster.setAllowableClosedLoopErr(RobotMap.ALLOWABLE_ERROR);
		leftMaster.setPID(RobotMap.kP, RobotMap.kI, RobotMap.kD);
		leftMaster.enableControl();
		System.out.println("END INIT left master");

		leftSlave.setControlMode(CANTalon.TalonControlMode.Follower.value);
		leftSlave.set(leftMaster.getDeviceID()); // Set the deviceID to follow
													// drive outputs
		System.out.println("END INIT left slave");

		rightMaster.setControlMode(CANTalon.TalonControlMode.Position.value);
		rightMaster.set(0);
		rightMaster.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightMaster.configEncoderCodesPerRev(360);
		rightMaster.setPosition(0);
		// lefMaster.setVoltageRampRate(RAMP_RATE);
		// Profile 0
		rightMaster.setProfile(0);
		rightMaster.configNominalOutputVoltage(+0.0f, -0.0f);
		rightMaster.configPeakOutputVoltage(+RobotMap.PEAK_VOLTAGE, -RobotMap.PEAK_VOLTAGE);
		rightMaster.setAllowableClosedLoopErr(RobotMap.ALLOWABLE_ERROR);
		rightMaster.setPID(RobotMap.kP, RobotMap.kI, RobotMap.kD);
		rightMaster.enableControl();
		System.out.println("END INIT right master");

		rightSlave.setControlMode(CANTalon.TalonControlMode.Follower.value);
		rightSlave.set(leftMaster.getDeviceID());
		System.out.println("END INIT right slave");
	}

	
}
