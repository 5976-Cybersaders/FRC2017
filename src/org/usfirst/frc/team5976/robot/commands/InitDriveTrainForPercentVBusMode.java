package org.usfirst.frc.team5976.robot.commands;

import org.usfirst.frc.team5976.robot.RobotMap;
import org.usfirst.frc.team5976.robot.subsystems.DriveTrain;

import com.ctre.CANTalon;

public class InitDriveTrainForPercentVBusMode extends InitDriveTrain {

	public InitDriveTrainForPercentVBusMode(DriveTrain driveTrain) {
		super(driveTrain);
	}

	@Override
	protected void initTalons() {
		/*System.out.println("Ramp Rate: " + leftMaster.getCloseLoopRampRate() + 
				" " + leftSlave.getCloseLoopRampRate() + 
				" " + rightMaster.getCloseLoopRampRate() + 
				" " + rightSlave.getCloseLoopRampRate() + 
				" " + leftMaster.getControlMode());
	    */
		initMaster(leftMaster);
		initSlave(leftSlave, leftMaster.getDeviceID());
		initMaster(rightMaster);
		initSlave(rightSlave, rightMaster.getDeviceID());
		System.out.println(leftMaster.getControlMode());
		
		/*System.out.println("Ramp Rate: " + leftMaster.getCloseLoopRampRate() + 
				" " + leftSlave.getCloseLoopRampRate() + 
				" " + rightMaster.getCloseLoopRampRate() + 
				" " + rightSlave.getCloseLoopRampRate() + 
				" " + leftMaster.getControlMode());
		*/
	}
	
	protected void initCommon(CANTalon talon) {
		//super.initCommon(talon);
		talon.setPID(0, 0, 0);
		talon.configPeakOutputVoltage(+6.0, -6.0);
	}
	
	protected void initMaster(CANTalon talon) {
		//super.initMaster(talon);
		talon.setControlMode(CANTalon.TalonControlMode.PercentVbus.value);
		initCommon(talon);
	}
	
	protected void initSlave(CANTalon talon, int masterID) {
		//super.initSlave(talon, masterID);
		talon.setControlMode(CANTalon.TalonControlMode.Follower.value);
		talon.set(masterID);
		initCommon(talon);
	}
	
	protected double getMaxVoltage() {
		return RobotMap.PEAK_VOLTAGE_TELEOP;
	}
}
